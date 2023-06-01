package com.example.lecturekeep.feature_lecturewrite.PL.lectures.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lecturekeep.feature_lecturewrite.BLL.util.LectureOrder
import com.example.lecturekeep.feature_lecturewrite.BLL.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    lectureOrder: LectureOrder = LectureOrder.Time(OrderType.Desc),
    onOrderChange: (LectureOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "By Title",
                selected = lectureOrder is LectureOrder.Title,
                onSelect = { onOrderChange(LectureOrder.Title(lectureOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "By Time",
                selected = lectureOrder is LectureOrder.Time,
                onSelect = { onOrderChange(LectureOrder.Time(lectureOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "By Subject",
                selected = lectureOrder is LectureOrder.Subject,
                onSelect = { onOrderChange(LectureOrder.Subject(lectureOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = lectureOrder.orderType is OrderType.Asc,
                onSelect = { onOrderChange(lectureOrder.copy(OrderType.Asc)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = lectureOrder.orderType is OrderType.Desc,
                onSelect = { onOrderChange(lectureOrder.copy(OrderType.Desc)) }
            )
        }
    }
}