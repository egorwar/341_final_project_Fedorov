package com.example.lecturekeep.feature_lecturewrite.PL.lectures

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.BLL.usecase.usecases_Lectures
import com.example.lecturekeep.feature_lecturewrite.BLL.util.LectureOrder
import com.example.lecturekeep.feature_lecturewrite.BLL.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturesViewModel @Inject constructor(
    private val usecasesLectures: usecases_Lectures
) : ViewModel() {

    private val _state = mutableStateOf<LecturesState>(LecturesState())
    val state: State<LecturesState> = _state

    private var recentlyDeletedLecture: Lecture? = null

    private var getLecturesJob: Job? = null

    init {
        getLectures(LectureOrder.Time(OrderType.Desc))
    }

    fun onEvent(event: LecturesEvent) {
        when(event) {
            is LecturesEvent.Order -> {
                if (state.value.lectureLectureOrder::class == event.lectureOrder::class &&
                        state.value.lectureLectureOrder.orderType == event.lectureOrder.orderType) {
                    return
                }
                getLectures(event.lectureOrder)
            }
            is LecturesEvent.DelLecture -> {
                viewModelScope.launch {
                    usecasesLectures.delLectures(event.lecture)
                    recentlyDeletedLecture = event.lecture
                }
            }
            is LecturesEvent.RestoreLecture -> {
                viewModelScope.launch {
                    usecasesLectures.addLecture(recentlyDeletedLecture ?: return@launch)
                    recentlyDeletedLecture = null
                }
            }
            is LecturesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getLectures(lectureOrder: LectureOrder) {
        getLecturesJob?.cancel()
        getLecturesJob = usecasesLectures.getLectures(lectureOrder).onEach { lectures ->
            _state.value = state.value.copy(
                lectures = lectures,
                lectureLectureOrder = lectureOrder
            )
        }.launchIn(viewModelScope)
    }
}