package com.test.bazar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage


@Composable
fun AppsPage(viewModel: BazarViewModel, modifier: Modifier, onAppClick: (Int) -> Unit) {
//    val appList by viewModel.appList.collectAsState()
    val selectedApp by viewModel.selectedApp.collectAsState()
    val newAppList by viewModel.newAppList.collectAsState()
    LazyColumn(modifier
        .fillMaxSize()
        .background(Color.Black)) {
        item {
            TopBar()
            ShowPicture(viewModel, selectedApp)
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                LazyRow(Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    items(newAppList) { app ->
                        Items(app.imageUrl, app.name, app.rate.toString(), app.price.toString()) {
                            viewModel.updateSelectedPicture(app)
                            onAppClick(app.id)
                        }
                    }
                }
            }
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                LazyRow(Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    items(newAppList) { app ->
                        Items(app.imageUrl, app.name,app.rate.toString(), app.price.toString()) {
                            viewModel.updateSelectedPicture(app)
                            onAppClick(app.id)
                        }
                    }
                }
            }
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                LazyRow(Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    items(newAppList) { app ->
                        Items(app.imageUrl, app.name, app.rate.toString(), app.price.toString()) {
                            viewModel.updateSelectedPicture(app)
                            onAppClick(app.id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Items(imageUrl: String, appName: String, rate: String, price: String, onAppClick: () -> Unit) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(onClick = onAppClick),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
                .shadow(1.dp, RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop

        )
        Column (Modifier.padding(start = 12.dp)){
            Row (Modifier.fillMaxWidth().background(Color.Blue)){
                Text(
                    text = appName,
                    fontSize = 12.sp,
                    color = Color.White,
                )
                Spacer(Modifier.padding(horizontal = 24.dp))
                Text(
                    text = rate,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            Text(
                text = if (price == "0") "رایگان" else "$price تومان",
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }

}

@Composable
fun ShowPicture(viewModel: BazarViewModel, selectedApp: App) {
    val featuredAppList = viewModel.featuredAppList
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            AsyncImage(
                model = selectedApp.imageUrl,
                contentDescription = "Advertisement",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Row {
            featuredAppList.forEachIndexed { index, _ ->
                Box(
                    Modifier
                        .padding(vertical = 24.dp, horizontal = 4.dp)
                        .size(8.dp)
                        .background(
                            if (viewModel.selectedFeaturedIndex.value == index) Color.Cyan else Color.White,
                            CircleShape
                        )
                ) {
                    Log.i(
                        "tag",
                        "viewModel.selectedFeaturedIndex: ${viewModel.selectedFeaturedIndex} and index :${index}"
                    )
                }
            }
//            for (i in featuredAppList){
////                if (isSelect.id == i.id) {
////                    i.id=isSelect.id
////                }
//
//            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewMainScreens() {
//    MainScreen(viewModel = viewModel())
//}