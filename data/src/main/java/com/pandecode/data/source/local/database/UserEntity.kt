package com.pandecode.data.source.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pandecode.data.utils.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_NAME)
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COL_ID)
    val id: Int,

    @ColumnInfo(name = DatabaseConstants.COL_USERNAME)
    val username: String,

    @ColumnInfo(name = DatabaseConstants.COL_AVATAR_URL)
    val avatarUrl: String

)
