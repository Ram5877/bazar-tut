package com.test.bazar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun AppsDetailsScreen(appId: Int) {
    val app = AppRepository.appList.find { it.id == appId }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            AsyncImage(
                model = app?.imageUrl,
                contentDescription = "Advertisement",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalAlignment = Alignment.End) {
            Text(app!!.name, fontSize = 48.sp, color = Color.White)
            Text(app.description, fontSize = 38.sp, color = Color.White)
            Text(
                text = if (app?.price == 0) "رایگان" else "${app?.price} تومان",
                fontSize = 38.sp,
                color = Color.White
            )
        }
    }


}