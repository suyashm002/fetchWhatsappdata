package com.example.waproject.ui


import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toolbar
import com.example.waproject.R

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity

@Composable
fun CustomToolbar(title: String, context: Context) {

    TopAppBar( modifier = Modifier.padding(bottom = 16.dp),
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = {
                val settingsIntent =
                    Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                startActivity(context,settingsIntent,null)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_notifications),
                    contentDescription = stringResource(id = R.string.app_name)
                )
            }
            IconButton(onClick = { Log.d("Button 2", "CustomToolbar: ") }) {
                Icon(
                    painter = painterResource(id = R.drawable.notification_not_on),
                    contentDescription = stringResource(id = R.string.app_name)
                )
            }
        }
    )
}
//@Preview
//@Composable
//fun ToolbarPreview() {
//    CustomToolbar(title = "text")
//}