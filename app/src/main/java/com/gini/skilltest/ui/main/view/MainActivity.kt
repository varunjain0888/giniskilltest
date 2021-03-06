package com.gini.skilltest.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.gini.skilltest.R
import com.gini.skilltest.data.api.ApiHelper
import com.gini.skilltest.data.api.RetrofitBuilder
import com.gini.skilltest.databinding.ActivityMainBinding
import com.gini.skilltest.ui.base.BaseActivity
import com.gini.skilltest.ui.base.ViewModelFactory
import com.gini.skilltest.ui.main.viewmodel.MainViewModel
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var mNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        setNavHost()
        setupViewModel()
    }

    private fun setListeners() {

    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
                .get(MainViewModel::class.java)
    }

    private fun setNavHost() {
        setSupportActionBar(binding.includeContent.includeHeader.root as Toolbar)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
                ?: return
        mNavController = host.navController
        setupActionBarWithNavController(mNavController)
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            setHeaderTitle(destination.label?.toString())
            when (destination.id) {
                R.id.homeDest -> {

                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) || super.onOptionsItemSelected(
            item
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    fun setHeaderTitle(title: String?) {
        (binding.includeContent.includeHeader.root as Toolbar).title = title
    }

    fun getNavOptions(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
    }

    fun getNavOptionsFade(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out)
            .build()
    }

    fun getNavOptionsBottomUp(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_bottom)
            .setExitAnim(R.anim.slide_out_top)
            .setPopEnterAnim(R.anim.slide_in_top)
            .setPopExitAnim(R.anim.slide_out_bottom)
            .build()
    }

    override fun onClick(v: View?) {
        when (v) {

        }
    }
}
