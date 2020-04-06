package com.words.nacoss.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel (private val repository: MainRepository) : ViewModel(){

    /**************************************************/
    //Check This Code for Safety Sake( i.e encapsulation )
    private val _staffList = MutableLiveData<List<Staff>>()
    val staffList: LiveData<List<Staff>>
            get() = _staffList

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getAllStaff()
    }

    private fun getAllStaff(){
        uiScope.launch {
            val listOfStaff : List<Staff> = repository.getStaffs()
            val onlineStaffs: List<Staff> = repository.getOnlineStaffs()
            Timber.i("staffOnLine data: $onlineStaffs")
            _staffList.postValue(listOfStaff + onlineStaffs)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object{
        class HomeViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(repository) as T
        }
    }
}