package com.words.nacoss.repository


import com.words.nacoss.dataSource.Staff
import com.words.nacoss.dataSource.StaffDao
import com.words.nacoss.network.StaffService
import com.words.nacoss.network.toRoomModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException


class MainRepository private constructor(private val staffDao: StaffDao, private val staffWebService: StaffService) {



    fun getStaffs() = staffDao.getAllStaff()

    fun getStaff(staffId: Int) = staffDao.getStaff(staffId)

    //we make our http request here
    suspend  fun getOnlineStaffs(){
        withContext(Dispatchers.IO) {
            try {
                val staffs = staffWebService.getStaffsAsync()
                Timber.i("$staffs")
                val result = staffs.toRoomModel()
                staffDao.insertAll(result)
            } catch (e: IOException) {
                Timber.i("Error fetching data from network")
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