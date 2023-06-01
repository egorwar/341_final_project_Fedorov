package com.example.lecturekeep.feature_lecturewrite.PL.edit

import androidx.compose.ui.focus.FocusState

sealed class EditLectureEvent {
    data class EnteredTitle(val value: String): EditLectureEvent()
    data class ChangeTitleFocus(val focusState: FocusState): EditLectureEvent()
    data class EnteredText(val value: String): EditLectureEvent()
    data class ChangeTextFocus(val focusState: FocusState): EditLectureEvent()
    data class ChangeSubject(val subject: Int): EditLectureEvent()
    object SaveLecture: EditLectureEvent()
}
