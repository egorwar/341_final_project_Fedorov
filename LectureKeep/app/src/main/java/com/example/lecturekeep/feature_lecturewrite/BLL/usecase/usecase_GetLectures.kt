package com.example.lecturekeep.feature_lecturewrite.BLL.usecase

import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.repo.LectureRepo
import com.example.lecturekeep.feature_lecturewrite.BLL.util.OrderType
import com.example.lecturekeep.feature_lecturewrite.BLL.util.LectureOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class usecase_GetLectures(
    private val repo: LectureRepo
) {
    operator fun invoke(lectureOrder: LectureOrder = LectureOrder.Time(OrderType.Desc)): Flow<List<Lecture>> {
        return repo.getLectures().map { lectures ->
            when(lectureOrder.orderType) {
                is OrderType.Asc -> {
                    when(lectureOrder) {
                        is LectureOrder.Title -> lectures.sortedBy { it.title.lowercase() }
                        is LectureOrder.Time -> lectures.sortedBy { it.time }
                        is LectureOrder.Subject -> lectures.sortedBy { it.subject }
                    }
                }
                is OrderType.Desc -> {
                    when(lectureOrder) {
                        is LectureOrder.Title -> lectures.sortedByDescending { it.title.lowercase() }
                        is LectureOrder.Time -> lectures.sortedByDescending { it.time }
                        is LectureOrder.Subject -> lectures.sortedByDescending { it.subject }
                    }
                }
            }
        }
    }
}