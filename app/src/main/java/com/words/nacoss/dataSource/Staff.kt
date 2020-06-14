package com.words.nacoss.dataSource

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "staff_profiles")
data class Staff (

    @PrimaryKey
    val id: Int?,

    val name: String?,

    val mobile: String?,

    val level: String?,

    val email: String?,

    val description: String?,

    val img_url: String?


)


