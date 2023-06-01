package com.example.lecturekeep.feature_lecturewrite.PL.util

sealed class Screen(val route: String) {
    object LecturesScreen: Screen("lectures_screen")
    object EditLectureScreen: Screen("edit_lecture_screen")
}
