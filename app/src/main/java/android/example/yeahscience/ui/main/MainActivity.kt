package android.example.yeahscience.ui.main

import android.example.yeahscience.R
import android.example.yeahscience.utils.network.ConnectionLiveData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var connectionLiveData: ConnectionLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, Observer {
            if (!it) {
                have_internet_connection.visibility = View.VISIBLE
            } else {
                have_internet_connection.visibility = View.GONE
            }
        })
        initNavFragment()
    }

    private fun initNavFragment() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)


        navigation_view.setupWithNavController(navController)
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}