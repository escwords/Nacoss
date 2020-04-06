package com.words.nacoss

import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.impl.utils.LiveDataUtils
import com.words.nacoss.dataSource.AppDatabase
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.dataSource.StaffDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import com.words.nacoss.util.getValue

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class StaffDaoTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.words.nacoss", appContext.packageName)
    }

    private lateinit var database: AppDatabase
    private lateinit var plantDao: StaffDao

    private val staffA = Staff(1, "A", "", "", "", "")
    private val staffB = Staff(2, "B", "", "", "","")
    private val staffC = Staff(3, "C", "", "", "","")

    @Before
    fun createDb() = runBlocking {


        /**
        * we first define the context for the database
         *  Note this context is not the same with the original context
        * */
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        //creating the in Memory Database in the defined context
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        plantDao = database.staffDao

        // Insert plants in non-alphabetical order to test that results are sorted by name
        //the comment is necessary for my project app
        plantDao.insertAll(listOf(staffA, staffB, staffC))
    }


    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetStaffs() = runBlocking {

       val staffList = plantDao.getAllStaff()
        //val staffList = getValue()
        assertThat(staffList.size, equalTo(3))


        assertThat(staffList[0], equalTo(staffA))
        assertThat(staffList[1], equalTo(staffB))
        assertThat(staffList[2], equalTo(staffC))
    }

   /* @Test fun testGetPlantsWithGrowZoneNumber() {
        val plantList = getValue(plantDao.getPlantsWithGrowZoneNumber(1))
        assertThat(plantList.size, equalTo(2))
        assertThat(getValue(plantDao.getPlantsWithGrowZoneNumber(2)).size, equalTo(1))
        assertThat(getValue(plantDao.getPlantsWithGrowZoneNumber(3)).size, equalTo(0))

        // Ensure plant list is sorted by name
        assertThat(plantList[0], equalTo(plantA))
        assertThat(plantList[1], equalTo(plantB))
    }*/

    /*@Test fun testGetPlant() {
        assertThat(getValue(plantDao.getPlant(plantA.plantId)), equalTo(plantA))
    }*/
}