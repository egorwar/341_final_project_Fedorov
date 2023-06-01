package com.example.lecturekeep.feature_lecturewrite.DAL.datasrc

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import kotlinx.coroutines.flow.Flow

@Dao
interface LectureDao {
    @Query("SELECT * FROM lecture")
    fun getLectures(): Flow<List<Lecture>>

    @Query("SELECT * FROM lecture WHERE id = :id")
    suspend fun getLectureById(id: Int): Lecture?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLecture(lecture: Lecture)

    @Delete
    suspend fun deleteLecture(lecture: Lecture)
}