package com.words.nacoss.repository


import com.words.nacoss.dataSource.Staff
import com.words.nacoss.dataSource.StaffDao
import com.words.nacoss.network.StaffService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException


class MainRepository private constructor(private val staffDao: StaffDao, private val staffWebService: StaffService) {



    suspend fun getStaffs() = staffDao.getAllStaff()

    suspend fun getStaff(staffId: Int) = staffDao.getStaff(staffId)


    //we make our http request here
    suspend  fun getOnlineStaffs(): List<Staff> {
        return withContext(Dispatchers.IO) {
            try {
                val staffs = staffWebService.getStaffsAsync()
                Timber.i("$staffs")
                staffs
            } catch (e: IOException) {
                Timber.i("Error fetch data from network")
                emptyList<Staff>()
            }
        }
    }

    companion object {

        @Volatile
        private var Instance: MainRepository? = null

        fun getInstance(staffDao: StaffDao,staffWebService: StaffService) =
             Instance ?: synchronized(this) {
                Instance ?: MainRepository(staffDao,staffWebService).also { Instance = it }
            }

        }
}