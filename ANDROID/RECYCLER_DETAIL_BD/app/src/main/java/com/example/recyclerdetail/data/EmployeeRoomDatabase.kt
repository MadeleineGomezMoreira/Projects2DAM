package com.example.recyclerdetail.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recyclerdetail.data.model.EmployeeEntity
import com.example.recyclerdetail.data.model.ThingEntity

@Database(entities = [EmployeeEntity::class, ThingEntity::class], version = 6, exportSchema = true)
@TypeConverters(Converters::class)
abstract class EmployeeRoomDatabase : RoomDatabase(){

    abstract fun employeeDao(): EmployeeDao
    abstract fun thingDao(): ThingDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeRoomDatabase? = null

        fun getDatabase(context: Context): EmployeeRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeRoomDatabase::class.java,
                    "item_database"
                )
                    .createFromAsset("database/bdPersonas.db")
                    .fallbackToDestructiveMigrationFrom(4)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}