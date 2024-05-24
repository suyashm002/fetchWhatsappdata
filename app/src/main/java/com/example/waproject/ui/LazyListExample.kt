package com.example.waproject.ui

import android.widget.Space
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.waproject.R
import com.example.waproject.models.SaveCustomeMessage

@Composable
fun LazyListExample(items: List<SaveCustomeMessage>) {
    LazyColumn {
        items(items) { item ->
            // Composable function to render each item
            ListItem(item)
        }
    }
}

@Composable
fun ListItem(item: SaveCustomeMessage) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {

        Icon(
            painter = painterResource(id = R.drawable.whatsapp_icon),
            contentDescription = "wa",
            Modifier.size(8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Composable function to render each item in the list
        Text(text = item.expectedMessage.toString(), modifier = Modifier.padding(8.dp))
       
    }
}