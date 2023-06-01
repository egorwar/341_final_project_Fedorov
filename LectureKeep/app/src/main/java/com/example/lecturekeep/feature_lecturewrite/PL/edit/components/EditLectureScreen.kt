package com.example.lecturekeep.feature_lecturewrite.PL.edit.components

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture
import com.example.lecturekeep.feature_lecturewrite.PL.edit.EditLectureEvent
import com.example.lecturekeep.feature_lecturewrite.PL.edit.EditLectureViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditLectureScreen(
    navController: NavController,
    lectureSubject: Int,
    viewModel: EditLectureViewModel = hiltViewModel()
) {
    val titleState = viewModel.lectureTitle.value
    val textState = viewModel.lectureText.value

    val snackbarHostState = remember { SnackbarHostState() }

    val lectureBackgroundAnimatable = remember {
        Animatable(
            Color(if (lectureSubject != -1) lectureSubject else viewModel.lectureSubject.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is EditLectureViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is EditLectureViewModel.UiEvent.SaveLecture -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(EditLectureEvent.SaveLecture)
            }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Lecture")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lectureBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Lecture.subjs.forEach { subject ->
                    val subjectInt = subject.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(subject)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.lectureSubject.value == subjectInt) {
                                    Color.Black
                                } else {
                                    Color.Transparent
                                },
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    lectureBackgroundAnimatable.animateTo(
                                        targetValue = Color(subjectInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(EditLectureEvent.ChangeSubject(subjectInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(EditLectureEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditLectureEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium
                )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = textState.text,
                hint = textState.hint,
                onValueChange = {
                    viewModel.onEvent(EditLectureEvent.EnteredText(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditLectureEvent.ChangeTextFocus(it))
                },
                isHintVisible = textState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}