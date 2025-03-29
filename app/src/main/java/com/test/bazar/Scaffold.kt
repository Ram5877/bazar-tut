package com.test.bazar

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.bazar.ui.theme.AppColors
import com.test.bazar.ui.theme.BazarTheme


@Composable
fun MainScreen(viewModel: BazarViewModel, sharedPreferences : SharedPreferences){
    BazarTheme {
        val navController = rememberNavController()
        var selectedAppId = sharedPreferences.getInt("APP_ID", -1)



        val context = LocalContext.current

        BackHandler {


        val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute == AppScreen.AppsDetails.name){
                sharedPreferences.edit().putInt("APP_ID", -1).commit()
                navController.navigate(AppScreen.AppsScreen.name)
                context.getActivity()?.finish()
            } else {
            }

        }


//        var selectedApp : App? = null
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = if (selectedAppId == -1) AppScreen.AppsScreen.name else AppScreen.AppsDetails.name
        ) {
            composable(route = AppScreen.AppsScreen.name) {
                AppsPage(viewModel,Modifier){
                    sharedPreferences.edit().putInt("APP_ID", it).commit()
                    selectedAppId = it
                    navController.navigate(AppScreen.AppsDetails.name)
                }
            }
            composable(route = AppScreen.AppsDetails.name) {
                AppsDetailsScreen(selectedAppId)
            }
        }

//        Scaffold(
//            topBar = { TopBar() },
//            bottomBar = { BottomBar(viewModel) }
//        ) { innerPadding ->
//
//            AppsPage(viewModel(), Modifier.fillMaxSize().padding(innerPadding))
//        }
    }
}

@Composable
fun BottomBar (viewModel: BazarViewModel) {
    val selectedPage by viewModel.selectedPage.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomBarIcons(
            R.drawable.download,
            R.drawable.download_fillde,
            "به روزرسانی",
            selectedPage == Pages.Updates
        ) {
        }
        BottomBarIcons(
            R.drawable.widgets,
            R.drawable.widgets_filled,
            "برنامه ها",
            selectedPage == Pages.Apps
        ) {
        }
        BottomBarIcons(
            R.drawable.games,
            R.drawable.games_filled,
            "بازی",
            selectedPage == Pages.Games
        ) {
        }
    }
}

@Composable
fun BottomBarIcons(icon: Int,
                   iconFilled: Int,
                   text: String,
                   isIconFilled: Boolean,
                   onPressed: () -> Unit
){
    Column(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onPressed() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Icon(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp),
            painter = painterResource(if (isIconFilled) iconFilled else icon) ,
            contentDescription = "",
            tint = Color.LightGray
        )

        Text(text, color = if (isIconFilled) Color.White else Color.LightGray)

    }
}

@Composable
fun TopBar (){
    Row (Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically){
        IconButton(onClick = {},
        ) {
            Icon(Icons.Outlined.AccountCircle, null,
                modifier = Modifier.size(78.dp),
                tint = Color.White
                )
        }
        TextBar(viewModel())
    }
}

@Composable
fun TextBar(viewModel: BazarViewModel){
    val searchValue by viewModel.searchValue.collectAsState()

    TextField(

        value = searchValue,
        onValueChange = {
            viewModel.searchValue.value = it
//            onSearchTextChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8.dp),
        textStyle = androidx.compose.ui.text.TextStyle(

            textAlign = TextAlign.Right
        ),
        placeholder = { Text(modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right,
            text = "جستجو در بازی ها و برنامه ها",
            ) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = AppColors.searchBar,
            focusedContainerColor = AppColors.searchBar,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = Color.LightGray,
            focusedTextColor = Color.LightGray,
            unfocusedPlaceholderColor = Color.LightGray,
            focusedPlaceholderColor = Color.LightGray,
            unfocusedLeadingIconColor = Color.LightGray,
            focusedLeadingIconColor = Color.LightGray,
        ),
    )
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}


//@Preview
//@Composable
//fun PreviewMainScreen(){
//    MainScreen(viewModel = viewModel())
//}