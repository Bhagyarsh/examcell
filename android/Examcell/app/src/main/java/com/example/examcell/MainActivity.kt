package com.example.examcell


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.examcell.auth.Login
import org.json.JSONObject



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token",null)
        Log.d("checking","1====================")
        if (token!=null){
            val jsonLoginObject = JSONObject()
            val url = "http://10.0.2.2:8000:8000/api/v1/auth/jwt/verify"
            jsonLoginObject.put("token",token)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonLoginObject,
                Response.Listener { response ->
                    Log.d("auth",response.getString("token"))
                    startActivity(Intent(this,Home::class.java))
                },
                Response.ErrorListener { error ->
                    Log.d("checking","2====================")
                    startActivity(Intent(this, Login::class.java))
                }
            )
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }
        else{
            Log.d("checking","3====================")
            startActivity(Intent(this, Login::class.java))


        }
    }
}
