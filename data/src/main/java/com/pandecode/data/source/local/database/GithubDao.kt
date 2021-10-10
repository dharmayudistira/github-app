package com.pandecode.data.source.local.database

import androidx.room.*
import com.pandecode.data.utils.DatabaseConstants.COL_USERNAME
import com.pandecode.data.utils.DatabaseConstants.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllFavoriteUser(): Flow<List<UserEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COL_USERNAME = :username")
    fun getUserByUsername(username: String): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

}