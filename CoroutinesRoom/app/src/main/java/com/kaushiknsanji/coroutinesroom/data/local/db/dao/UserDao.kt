package com.kaushiknsanji.coroutinesroom.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaushiknsanji.coroutinesroom.data.local.db.entity.User

/**
 * RoomDatabase access object Interface for communicating with the "users" table.
 *
 * @author Kaushik N Sanji
 */
@Dao
interface UserDao {
    /**
     * Inserts a [user] into "users" table. In case of any conflict, existing record
     * if any will be replaced.
     *
     * @return [Long] value of the id of the [user] inserted/replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    /**
     * Retrieves [User] data identified by the given [username] from the "users" table.
     *
     * @return [User] instance of the identified user if any; `null` otherwise.
     */
    @Query("SELECT * from users WHERE username = :username")
    suspend fun getUserByName(username: String): User?

    /**
     * Deletes a user record identified by the given [id] from the "users" table.
     *
     * @return [Int] value of the number of users deleted successfully.
     */
    @Query("DELETE from users WHERE id = :id")
    suspend fun deleteUserById(id: Long): Int
}