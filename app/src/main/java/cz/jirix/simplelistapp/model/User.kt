package cz.jirix.simplelistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val global_id: String,
    val identity_provider: String,
    val alias: String,
    val picture_url: String,
    val phone_number: String,
    val is_master: Boolean
)