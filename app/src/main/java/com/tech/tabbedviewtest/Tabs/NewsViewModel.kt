package com.tech.tabbedviewtest.Tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech.tabbedviewtest.NewsModelReponse
import com.tech.tabbedviewtest.Repo
import kotlinx.coroutines.*

class NewsViewModel(private val repo: Repo): ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val newsData = MutableLiveData<NewsModelReponse>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getNewsData(key:String){
        job = CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val response =async {
                repo.getNewsList(key)
            }
            withContext(Dispatchers.Main) {
                if (response.await().isSuccessful) {
                    newsData.postValue(response.await().body())
                    loading.value = false
                } else {
                    onError("Error : ${response.await().message()} ")
                }
            }
        }
    }

    fun OnCancel(){
        onCleared()
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}