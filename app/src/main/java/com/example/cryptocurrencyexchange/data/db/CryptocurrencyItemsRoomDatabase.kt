package com.example.cryptocurrencyexchange.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptocurrencyEntity::class], version = 1)
public abstract class CryptocurrencyItemsRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): DaoCryptocurrencyItem

    companion object {

        @Volatile
        private var INSTANCE: CryptocurrencyItemsRoomDatabase? = null

        fun getDatabase(context: Context): CryptocurrencyItemsRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let { return it }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptocurrencyItemsRoomDatabase::class.java,
                    "cryptocurrency_items_database"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}