package com.example.lecturekeep.feature_lecturewrite.DAL.datasrc

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture

@Database(
    entities = [Lecture::class],
    version = 1
)
abstract class LectureDB : RoomDatabase() {
    abstract val lectureDao: LectureDao

    companion object {
        const val DB_NAME = "lectures_DB"
    }
}