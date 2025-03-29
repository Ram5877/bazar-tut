package com.test.bazar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class BazarViewModel: ViewModel() {



    val searchValue = MutableStateFlow("")
    val newAppList = MutableStateFlow(mutableListOf<App>())
    init {

            newAppList.value.clear()
            if (searchValue.value.isEmpty()){
                newAppList.value.addAll(AppRepository.appList)
            } else {
                for (app in AppRepository.appList){
                    if (app.name.contains(searchValue.value, ignoreCase = true)){
                        newAppList.value.add(app)
                    }
                }
            }



    }

     val featuredAppIdList = listOf(
        1,3,5,0
    )


    val featuredAppList : List<App>


    val selectedPage = MutableStateFlow(Pages.Apps)

    var  selectedApp :MutableStateFlow<App>

    init {
        featuredAppList = AppRepository.appList.filter { it.id in featuredAppIdList }
        selectedApp=MutableStateFlow(featuredAppList.first())
        autoChangeSelectedApp()
    }

     var selectedFeaturedIndex = MutableStateFlow(-1)
    private fun autoChangeSelectedApp(){
        Timer().schedule(object : TimerTask() {
            override fun run() {
                selectedFeaturedIndex.value++
                if (selectedFeaturedIndex.value >= featuredAppList.size){
                    selectedFeaturedIndex.value = 0
                }
                selectedApp.value = featuredAppList[selectedFeaturedIndex.value]

            }
        }, 0, 2000L)
    }


    fun updateSelectedPicture(imageUrl: App) {
        viewModelScope.launch {
            selectedApp.value = imageUrl

        }
    }
}