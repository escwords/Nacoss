package com.words.nacoss.network

import com.google.firebase.firestore.IgnoreExtraProperties
import com.words.nacoss.dataSource.Staff

@IgnoreExtraProperties
data class NetworkContainer(
    val staffs: List<FirebaseStaff>
)

//Remote data model properties
@IgnoreExtraProperties
data class FirebaseStaff(
    val description: String? = null,
    val email: String? = null,
    val id: String? = null,
    val img_url: String? = null,
    val level: String? = null,
    val mobile: String? = null,
    val name: String? = null
)


//Converting Remote data model to Room data model
fun NetworkContainer.toRoomModel(): List<Staff>{
    var count = 11
    return staffs.map { result ->
        Staff(
            id = result.id?.toInt() ?: count++,
            name = result.name,
            mobile = result.mobile,
            level = result.level,
            email = result.email,
            description = result.description,
            img_url = result.img_url

        )
    }
}