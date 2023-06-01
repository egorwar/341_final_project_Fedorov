package com.example.lecturekeep.feature_lecturewrite.BLL.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lecturekeep.ui.theme.Blue
import com.example.lecturekeep.ui.theme.Green
import com.example.lecturekeep.ui.theme.Orange
import com.example.lecturekeep.ui.theme.Pink
import com.example.lecturekeep.ui.theme.Purple
import java.lang.Exception

@Entity
data class Lecture(
    val title: String,
    val text: String,
    val time: Long,
    val subject: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val subjs = listOf(Orange, Green, Purple, Blue, Pink)
    }
}

class InvalidLectureException(message: String): Exception(message)
