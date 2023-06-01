package com.example.lecturekeep.feature_lecturewrite.BLL.usecase

import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.repo.LectureRepo

class usecase_GetLecture(
    private val repo: LectureRepo
) {
    suspend operator fun invoke(id: Int): Lecture? {
        return repo.getLectureById(id)
    }
}