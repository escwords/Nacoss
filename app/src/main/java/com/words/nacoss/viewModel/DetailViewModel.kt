package com.words.nacoss.viewModel

import androidx.lifecycle.*
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.repository.MainRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MainRepository, staffId: Int) : ViewModel() {

    val staff : LiveData<Staff>
    get() = _staff

    private var _staff = MutableLiveData<Staff>()

    init {
        getStaff(staffId)
    }

    private fun getStaff(staffId: Int){
        viewModelScope.launch {
            val newStaff = repository.getStaff(staffId)
            _staff.value = newStaff
        }

    }
    companion object{
        class DetailViewModelFactory(val staffId: Int, val  repository: MainRepository)
            : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailViewModel(repository,staffId ) as T

        }
    }

}