package com.example.lecturekeep.feature_lecturewrite.BLL.usecase

import com.example.lecturekeep.feature_lecturewrite.BLL.model.InvalidLectureException
import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.repo.LectureRepo

class usecase_AddLecture(
    private val repo: LectureRepo
) {
    @Throws(InvalidLectureException::class)
    suspend operator fun invoke(lecture: Lecture) {
        if (lecture.title.isBlank()) {
            throw InvalidLectureException("Title must not be empty")
        }
        if (lecture.text.isBlank()) {
            throw InvalidLectureException("Lecture must not be empty")
        }
        repo.insertLecture(lecture)
    }
}