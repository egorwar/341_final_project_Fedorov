package com.example.lecturekeep.feature_lecturewrite.BLL.util

sealed class LectureOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : LectureOrder(orderType)
    class Time(orderType: OrderType) : LectureOrder(orderType)
    class Subject(orderType: OrderType) : LectureOrder(orderType)

    fun copy(orderType: OrderType): LectureOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Time -> Time(orderType)
            is Subject -> Subject(orderType)

        }
    }
}
