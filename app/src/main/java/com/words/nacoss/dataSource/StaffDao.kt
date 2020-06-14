package com.words.nacoss.dataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface StaffDao {

     @Query("SELECT *FROM staff_profiles")
     fun getAllStaff(): LiveData<List<Staff>>

    @Query("SELECT *FROM staff_profiles WHERE :id = id")
    fun getStaff(id: Int): LiveData<Staff>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(staffs: List<Staff>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(staff:Staff)
}