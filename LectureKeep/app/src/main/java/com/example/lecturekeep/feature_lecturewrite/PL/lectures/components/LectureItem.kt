package com.example.lecturekeep.feature_lecturewrite.PL.lectures.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.lecturekeep.feature_lecturewrite.BLL.model.Lecture

@Composable
fun LectureItem(
    lecture: Lecture,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutcornerSize: Dp = 30.dp,
    onDeleteClick: () -> Unit
    ) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = androidx.compose.ui.graphics.Path().apply {
                lineTo(size.width - cutcornerSize.toPx(), 0f)
                lineTo(size.width, cutcornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(0xFFD6C6A6),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(lecture.subject),
                    topLeft = Offset(size.width - cutcornerSize.toPx(), -100f),
                    size = Size(cutcornerSize.toPx() + 100f, cutcornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(text = lecture.title,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = lecture.text,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis)
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
        }
    }
}