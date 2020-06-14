package com.words.nacoss.viewModel

import androidx.lifecycle.*
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.repository.MainRepository
import kotlinx.coroutines.*

class HomeViewModel (private val repository: MainRepository) : ViewModel(){

    //return the List of staffs from database
    val staffList: LiveData<List<Staff>> = liveData {
        emitSource(repository.getStaffs())
    }

    private val uiScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init{
        uiScope.launch {
            repository.getOnlineStaffs()
        }
    }

    //factory for HomeViewModel
    companion object{
        class HomeViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(repository) as T
        }
    }
}