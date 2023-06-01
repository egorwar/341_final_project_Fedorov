package com.example.lecturekeep.feature_lecturewrite.BLL.util

sealed class OrderType {
    object Asc : OrderType()
    object Desc : OrderType()
}
