package com.example.lecturekeep.feature_lecturewrite.BLL.usecase

data class usecases_Lectures(
    val getLectures: usecase_GetLectures,
    val delLectures: usecase_DelLectures,
    val addLecture: usecase_AddLecture,
    val getLecture: usecase_GetLecture
)
