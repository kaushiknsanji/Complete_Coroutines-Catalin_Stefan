package com.kaushiknsanji.coroutinesroom.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaushiknsanji.coroutinesroom.data.local.db.dao.UserDao
import com.kaushiknsanji.coroutinesroom.data.local.db.entity.User

/**
 * An abstract [RoomDatabase] class for exposing the Database access objects and managing the database.
 *
 * @author Kaushik N Sanji
 */
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    /**
     * Database access object for "users" table.
     */
    abstract fun userDao(): UserDao

    companion object {
        // Singleton instance of this Database
        @Volatile
        private var instance: UserDatabase? = null

        // Lock object for synchronization during instance retrieval/creation
        private val LOCK = Any()

        // Constant for the Name of the Database file
        private const val DATABASE_NAME = "userdatabase.db"

        /**
         * 'Invoke' Operator function to create and provide the singleton instance of [UserDatabase].
         * Instance is created only when [instance] is not initialized yet.
         *
         * @param context [Context] to create the [UserDatabase] instance for.
         */
        operator fun invoke(context: Context): UserDatabase = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                // Save the instance built
                instance = it
            }
        }

        /**
         * Constructs the [UserDatabase] for the given application [context].
         */
        private fun buildDatabase(context: Context): UserDatabase = Room.databaseBuilder(
            // Enforcing to use the application context
            context.applicationContext,
            // RoomDatabase class
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()

    }
}