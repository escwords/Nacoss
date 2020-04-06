package com.words.nacoss.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.words.nacoss.R
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var appConfig: AppBarConfiguration

    // if the app crashes again check out the noAction Bar set as theme parent ***********
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //observe how we declared this binding variable
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        setSupportActionBar(myToolbar)
        drawerLayout = binding.navDrawer

        val navController = findNavController(R.id.nav_graph)

        appConfig = AppBarConfiguration(navController.graph, drawerLayout)

        NavigationUI.setupWithNavController(myToolbar,navController,appConfig)
        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
        /*findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)*/
    }

    //Which menu in particular does this function inflate? it inflate the menu in the appbar not the nav. drawer
   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         val menuInflater =  menuInflater
         menuInflater.inflate(R.menu.navigation_menu,menu)
         return true
    }*/

    //this function will enables us add listener to menu items I.e menu items on the appbar
    //we can also say that it enables us tie destination to menu item
   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }*/


    //This function manages the back button when using actionBar While toolbar doesn't need this method as it implement it's own back button
    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return NavigationUI.navigateUp(navController,appConfig) || super.onSupportNavigateUp()
    }*/

   /* fun moveToDialogScreen(staff: Staff){
       val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(staff.id)
        findNavController(R.id.nav_host).navigate(direction)
    }*/
}

