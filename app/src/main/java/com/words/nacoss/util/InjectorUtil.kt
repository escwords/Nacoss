package com.words.nacoss.util

import android.content.Context
import com.words.nacoss.dataSource.AppDatabase
import com.words.nacoss.network.StaffWebService
import com.words.nacoss.repository.MainRepository
import com.words.nacoss.viewModel.DetailViewModel.Companion.DetailViewModelFactory
import com.words.nacoss.viewModel.HomeViewModel.Companion.HomeViewModelFactory

object InjectorUtil{


    //Here we created an object of Repository by passing it its dependency
    private fun getRepository (context: Context): MainRepository{
        return MainRepository.getInstance(
            AppDatabase.getDatabase(context.applicationContext).staffDao,StaffWebService.staffWebService)
    }

    //Here we create homeViewModelFactory
    /*HomeViewModelFactory Create HomeViewModel object*/
    fun provideHomeViewModelFactory(context: Context): HomeViewModelFactory {
        val repository = getRepository(context)
        return HomeViewModelFactory(repository)
    }

    //Watch this function carefully we provide function for detailViewModel object here
    fun provideDetailViewModelFactory(
        staffId: Int, context: Context): DetailViewModelFactory {
        return DetailViewModelFactory(staffId, getRepository(context))
    }


}