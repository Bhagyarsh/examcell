package com.example.examcell

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.examcell.auth.Login
import com.google.android.material.navigation.NavigationView


class Home:AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val navViewHeader = navView.getHeaderView(0)
        val username = navViewHeader.findViewById<TextView>(R.id.NavheaderUsername)
        val role = navViewHeader.findViewById<TextView>(R.id.Navheaderrole)
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val lastUser = sharedPref.getString("fullname",null)
        val students = sharedPref.getBoolean("students",false)
        val parents = sharedPref.getBoolean("parents",false)
        val staff = sharedPref.getBoolean("staff",false)
        if (students){
            role.setText("(Student)")
        }
        if (parents){
            role.setText("(parents)")
        }
        if (staff){
            role.setText("(College staff)")
        }
        if (lastUser != null){
            username.setText(lastUser.toString())
        }
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.homemenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.MenuLogout -> {
                Log.d("todo","Logout")
            val alertDialog= AlertDialog.Builder(this)

                .setTitle("Confirm Logout")
                alertDialog.setMessage("do you want to logout from this account")
                alertDialog.setPositiveButton("Yes"){dialogInterface, which ->
                    val sharedPref = getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("token",null)
                        commit()
                }
                    startActivity(Intent(this, Login::class.java))}
                //performing cancel action
                alertDialog.setNeutralButton("Cancel"){dialogInterface , which ->
                    Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
                }
                //performing negative action
                alertDialog.setNegativeButton("No"){dialogInterface, which ->
                    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                }
                val alertDialogView: AlertDialog = alertDialog.create()
                // Set other dialog properties
                alertDialogView.setCancelable(false)
                alertDialogView.show()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logoutMenuNav -> {
                Log.d("todo","Logout")
                val alertDialog= AlertDialog.Builder(this)

                    .setTitle("Confirm Logout")
                alertDialog.setMessage("do you want to logout from this account")

                alertDialog.setPositiveButton("Yes"){dialogInterface, which ->
                    val sharedPref = getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("token",null)
                        commit()
                    }
                    startActivity(Intent(this, Login::class.java))
                }
                //performing cancel action
                alertDialog.setNeutralButton("Cancel"){dialogInterface , which ->
                    Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
                }
                //performing negative action
                alertDialog.setNegativeButton("No"){dialogInterface, which ->
                    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                }
                val alertDialogView: AlertDialog = alertDialog.create()
                // Set other dialog properties
                alertDialogView.setCancelable(false)
                alertDialogView.show()
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }



        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
