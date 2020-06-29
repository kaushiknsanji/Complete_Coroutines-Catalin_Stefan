package com.kaushiknsanji.coroutinesroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * RoomDatabase Entity class for "users" table.
 *
 * @constructor Creates an instance of [User] entity
 *
 * @author Kaushik N Sanji
 */
@Entity(tableName = "users")
data class User(
    val username: String,
    @ColumnInfo(name = "password_hash") val passwordHash: Int,
    val info: String
) {
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
}