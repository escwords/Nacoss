package com.words.nacoss.dataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface StaffDao {

    @Query("SELECT *FROM staff_profiles")
    suspend fun getAllStaff(): List<Staff>

    @Query("SELECT *FROM staff_profiles WHERE :id = id")
    suspend fun getStaff(id: Int): Staff

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(staffs: List<Staff>)
}