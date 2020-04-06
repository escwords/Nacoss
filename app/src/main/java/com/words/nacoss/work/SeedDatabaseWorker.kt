package com.words.nacoss.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.reflect.TypeToken
import com.words.nacoss.dataSource.AppDatabase
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.util.staff_profile_data
import java.lang.Exception

class SeedDatabaseWorker(context: Context, workParam: WorkerParameters): CoroutineWorker(context, workParam) {


    override suspend fun doWork(): Result {
        return try {
            applicationContext.assets.open(staff_profile_data).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->

                    val staffType = object: TypeToken<List<Staff>>() {}.type

                    val staffList: List<Staff> = Gson().fromJson(jsonReader,staffType)

                    val database = AppDatabase.getDatabase(applicationContext)

                    //Timber.i("$staffList")

                    database.staffDao.insertAll(staffList)

                    Result.success()
                }

            }
        }catch (e: Exception){
            Log.e(TAG, "Error seeding database", e)
            Result.failure()
        }
    }
    companion object{
         val TAG = SeedDatabaseWorker::class.java.simpleName
    }
} //end of seedDataBaseWorker Class