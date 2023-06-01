package com.example.lecturekeep.feature_lecturewrite.PL.lectures

import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.util.LectureOrder

sealed class LecturesEvent {
    data class Order(val lectureOrder: LectureOrder): LecturesEvent()
    data class DelLecture(var lecture: Lecture): LecturesEvent()
    object RestoreLecture: LecturesEvent()
    object ToggleOrderSection: LecturesEvent()
}
