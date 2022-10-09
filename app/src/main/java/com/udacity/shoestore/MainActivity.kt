package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.shoe_gallery.ShoeListViewModel
import timber.log.Timber

// developed by Rod Mesquita for Udacity nanodegree

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        NavigationUI.setupActionBarWithNavController(this, navController)

//        navController.addOnDestinationChangedListener { navControl: NavController, navDest: NavDestination, args: Bundle? ->
//            val fragId = navControl.currentDestination?.label
//            if (fragId == "shoeListFragment") {
//                Timber.wtf("-->> Shoe Item %s", fragId)
//            }
//        }

    }

}
