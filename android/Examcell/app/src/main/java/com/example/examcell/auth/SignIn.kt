package com.example.examcell.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.examcell.Home
import com.example.examcell.MySingleton
import com.example.examcell.R
import org.json.JSONObject
import java.security.KeyStore

class SignIn:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val usernameEditText = findViewById<EditText>(R.id.regiterusername)
        val passwordEditText = findViewById<EditText>(R.id.registerPassword)
        val conpasswordEditText = findViewById<EditText>(R.id.registerConpassword)
        val FirstNameEditText = findViewById<EditText>(R.id.registerfirstname)
        val lastNameEditText = findViewById<EditText>(R.id.registerlastname)
        val singupButton = findViewById<Button>(R.id.registersummitbutton)
        val navtologinText = findViewById<TextView>(R.id.registernavtologinfromsinguptextview)
        val radioGroup= findViewById<RadioGroup>(R.id.registerradiogroup)
        var student = false
        var staff = false
        var parent = false

        radioGroup.setOnCheckedChangeListener { group, checkid ->
            if (checkid == R.id.registerparentrodiobutton){
                 student = false
                 staff = false
                parent = true
                print(student)
            }
            if (checkid == R.id.registerstudentrodiobutton){
                student = true
                staff = false
                parent = false
            }
            if (checkid==R.id.registerstaffrodiobutton){
                student = false
                staff = true
                parent = false

            }
        }
        navtologinText.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        singupButton.setOnClickListener{
            print(staff)
            print(student)
            print(parent)
            Log.d("radiobutton",student.toString()+staff.toString()+parent.toString())
            val singupJsonObject = JSONObject()
            singupJsonObject.put("Username",usernameEditText.text.toString().trim())
            singupJsonObject.put("first_name",FirstNameEditText.text.toString().trim())
            singupJsonObject.put("last_name",lastNameEditText.text.toString().trim())
            singupJsonObject.put("password",passwordEditText.text.toString().trim())
            singupJsonObject.put("dp",null)
            singupJsonObject.put("parents",parent)
            singupJsonObject.put("student",student)
            singupJsonObject.put("staff",staff)
            val url = "http://10.0.2.2:8000/api/v1/auth/jwt/register"
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, singupJsonObject,
                Response.Listener { response ->
                    Log.d("auth",response.getString("token"))
                    with (sharedPref.edit()) {
                        putString("token",response.getString(("token")))
                        putString("user",response.getString(("user")))
                        putString("expires",response.getString(("expires")))
                        putString("staff",response.getString(("staff")))
                        putString("staff",response.getString(("staff")))
                        putString("student",response.getString(("student")))
                        putString("parents",response.getString(("parents")))
                        commit()
                    }
                    startActivity(Intent(this, Home::class.java))

                },
                Response.ErrorListener { error ->
                    println(error)
                    println(error.message)
                    println(error.networkResponse)
                    Log.d("checking","5====================")
                }
            )
            println("1---------------------------------------1")
            println(jsonObjectRequest.headers)
            println(jsonObjectRequest.body)

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }
    }
}
