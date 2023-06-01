package com.example.lecturekeep.feature_lecturewrite.PL.lectures

import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.util.OrderType
import com.example.lecturekeep.feature_lecturewrite.BLL.util.LectureOrder

data class LecturesState(
    val lectures: List<Lecture> = emptyList(),
    val lectureLectureOrder: LectureOrder = LectureOrder.Time(OrderType.Desc),
    val isOrderSectionVisible: Boolean = false
)
