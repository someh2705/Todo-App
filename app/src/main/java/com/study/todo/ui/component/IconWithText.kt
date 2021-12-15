package com.study.todo.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IconWithText(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit = { }
) {
    Row {
        Icon(icon, null)
        Text(text)
    }
}

@Preview
@Composable
fun IconWithTextPreview() {
    IconWithText(Icons.Rounded.Menu, text = "메뉴")
} 