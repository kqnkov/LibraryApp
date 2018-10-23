package com.example.krasimiryankov.libraryapp.data.database

import android.arch.persistence.room.TypeConverter
import java.sql.Date

class DateConverter() {
    companion object {
        @TypeConverter
        fun toDate(timestamp: Long?): Date? {
            if (timestamp == null)
                return null
            else
                return Date(timestamp)
        }

        @TypeConverter
        fun toTimestamp(date: Date?): Long? {
            return date?.time
        }
    }
}