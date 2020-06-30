package com.kaushiknsanji.coroutinesroom.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
    @PrimaryKey(autoGenerate = true) var id: Long,
    val username: String,
    @ColumnInfo(name = "password_hash") val passwordHash: Int,
    val info: String
) {
    @Ignore
    constructor(username: String, passwordHash: Int, info: String) : this(
        0,
        username,
        passwordHash,
        info
    )
}