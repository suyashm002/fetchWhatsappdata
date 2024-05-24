package com.example.waproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waproject.R


@Preview
@Composable
fun PreviewItem() {
    SavedReplyList(img = R.drawable.icon_notifications,messageReceived = "Hi", replyMessage = "hi")
}

@Composable
fun SavedReplyList(img: Int, messageReceived : String, replyMessage : String) {
    Card( elevation = 8.dp,
    modifier = Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.icon_notifications ),
                contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .padding(8.dp)
                .weight(.2f))
            Column (modifier = Modifier.weight(.8f)){
                Text(text = "Message Received",
                style = MaterialTheme.typography.h6)
                Text(text = "messageReceived",
                fontWeight = FontWeight.Thin)
                Text(text = "Reply ",
                    style = MaterialTheme.typography.h6)
                Text(text = "replyMessage",
                    fontWeight = FontWeight.Thin)
            }
        }

    }
}