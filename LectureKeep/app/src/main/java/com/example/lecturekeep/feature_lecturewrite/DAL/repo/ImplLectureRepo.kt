package com.example.lecturekeep.feature_lecturewrite.DAL.repo

import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.repo.LectureRepo
import com.example.lecturekeep.feature_lecturewrite.DAL.datasrc.LectureDao
import kotlinx.coroutines.flow.Flow

class ImplLectureRepo(
    private val dao: LectureDao
) : LectureRepo {
    override fun getLectures(): Flow<List<Lecture>> {
        return dao.getLectures()
    }

    override suspend fun getLectureById(id: Int): Lecture? {
        return dao.getLectureById(id)
    }

    override suspend fun insertLecture(lecture: Lecture) {
        dao.insertLecture(lecture)
    }

    override suspend fun deleteLecture(lecture: Lecture) {
        dao.deleteLecture(lecture)
    }
}