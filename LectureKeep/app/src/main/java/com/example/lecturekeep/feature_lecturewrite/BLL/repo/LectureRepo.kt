package com.example.lecturekeep.feature_lecturewrite.BLL.repo

import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import kotlinx.coroutines.flow.Flow

interface LectureRepo {
    fun getLectures(): Flow<List<Lecture>>

    suspend fun getLectureById(id: Int): Lecture?

    suspend fun insertLecture(lecture: Lecture)

    suspend fun deleteLecture(lecture: Lecture)
}