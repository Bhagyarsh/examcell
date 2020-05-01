package com.example.examcell.auth


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.examcell.Home
import com.example.examcell.MySingleton
import com.example.examcell.R

import org.json.JSONObject

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        Log.d("checking", "4====================")
        setContentView(R.layout.login)
        val lastUser = sharedPref.getString("user", null)
        val Username = findViewById<EditText>(R.id.UsernameLogin)
        val Password = findViewById<EditText>(R.id.LoginPassword)
        val loginButton = findViewById<Button>(R.id.Loginbutton)
        val navTectView = findViewById<TextView>(R.id.navtosingup)
        Username.setText(lastUser.toString())
        navTectView.setOnClickListener {
            Log.d("checking", "8====================")
            startActivity(Intent(this, SignIn::class.java))

        }
        loginButton.setOnClickListener {
//            startActivity(Intent(this, Home::class.java))
            Log.d("checking","6====================")
            val url = "http://10.0.2.2:8000/api/v1/auth/jwt"
            val jsonLoginObject = JSONObject()
            jsonLoginObject.put("username",Username.text.toString().trim())
            jsonLoginObject.put("password",Password.text.toString().trim())
            Log.d("auth request",jsonLoginObject.toString() )

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonLoginObject,
                Response.Listener { response ->
                    Log.d("auth",response.getString("token"))
                    Log.d("checking","4====================")
                    with (sharedPref.edit()) {
                        putString("token",response.getString(("token")))
                        putString("user",response.getString(("user")))
                        putString("fullname",response.getString(("fullname")).capitalizeWords())
                        putString("expires",response.getString(("expires")))
                        putBoolean("staff",response.getBoolean(("staff")))
                        putBoolean("students",response.getBoolean(("students")))
                        putBoolean("parents",response.getBoolean(("parents")))
                        commit()
                    }
                    startActivity(Intent(this, Home::class.java))

                },
                Response.ErrorListener { error ->
                    println(error)
                    println(error.message)
                    println(error.networkResponse)
                    if (error.networkResponse.data != null ){
                    val jsonObjectsdf = JSONObject(String(error.networkResponse.data))
                    println(jsonObjectsdf.toString())}
                    Log.d("checking","5====================")
                }
            )
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }


    }

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")
}