package com.example.lecturekeep.feature_lecturewrite.PL.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lecturekeep.feature_lecturewrite.BLL.model.InvalidLectureException
import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.usecase.usecases_Lectures
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditLectureViewModel @Inject constructor(
    private val usecasesLectures: usecases_Lectures,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _lectureTitle = mutableStateOf(LectureTextFieldState(
        hint = "Enter lecture name"
    ))
    val lectureTitle: State<LectureTextFieldState> = _lectureTitle

    private val _lectureText = mutableStateOf(LectureTextFieldState(
        hint = "Write lecture here"
    ))
    val lectureText: State<LectureTextFieldState> = _lectureText

    private val _lectureSubject = mutableStateOf(Lecture.subjs.random().toArgb())
    val lectureSubject: State<Int> = _lectureSubject

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentLectureId: Int? = null

    init {
        savedStateHandle.get<Int>("lectureId")?.let { lectureId ->
            if (lectureId != -1) {
                viewModelScope.launch {
                    usecasesLectures.getLecture(lectureId)?.also { lecture ->
                        currentLectureId = lecture.id
                        _lectureTitle.value = lectureTitle.value.copy(
                            text = lecture.title,
                            isHintVisible = false
                        )
                        _lectureText.value = lectureText.value.copy(
                            text = lecture.text,
                            isHintVisible = false
                        )
                        _lectureSubject.value = lecture.subject
                    }
                }
            }
        }
    }

    fun onEvent(event: EditLectureEvent) {
        when(event) {
            is EditLectureEvent.EnteredTitle -> {
                _lectureTitle.value = lectureTitle.value.copy(text = event.value)
            }
            is EditLectureEvent.ChangeTitleFocus -> {
                _lectureTitle.value = lectureTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && lectureTitle.value.text.isBlank()
                )
            }
            is EditLectureEvent.EnteredText -> {
                _lectureText.value = lectureText.value.copy(text = event.value)
            }
            is EditLectureEvent.ChangeTextFocus -> {
                _lectureText.value = lectureText.value.copy(
                    isHintVisible = !event.focusState.isFocused && lectureText.value.text.isBlank()
                )
            }
            is EditLectureEvent.ChangeSubject -> {
                _lectureSubject.value = event.subject
            }
            is EditLectureEvent.SaveLecture -> {
                viewModelScope.launch {
                    try {
                        usecasesLectures.addLecture(
                            Lecture(
                                title = lectureTitle.value.text,
                                text = lectureText.value.text,
                                time = System.currentTimeMillis(),
                                subject = lectureSubject.value,
                                id = currentLectureId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveLecture)
                    } catch(e: InvalidLectureException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save lecture"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveLecture: UiEvent()
    }
}