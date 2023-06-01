package com.example.lecturekeep.feature_lecturewrite.BLL.usecase

import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.repo.LectureRepo

class usecase_DelLectures(
    private val repo: LectureRepo
) {
    suspend operator fun invoke(lecture: Lecture) {
        repo.deleteLecture(lecture)
    }
}