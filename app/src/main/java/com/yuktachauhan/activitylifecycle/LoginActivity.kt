package com.yuktachauhan.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    //lateinit var means variable will be initialized later, lateinit can be only used with var not val
    lateinit var etUserNumber: EditText
    lateinit var etUserPassword: EditText
    lateinit var btnLogin: Button
    lateinit var txtForgetPassword: TextView
    lateinit var txtRegisterYourself: TextView
    var validNumber = "1234567890"
    var validPassword = arrayOf("123", "456", "789")

    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //to store login credentials of the user
        sharedPref=getSharedPreferences(getString(R.string.Shared_Preference_of_this_app), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn",false)

        setContentView(R.layout.activity_login)

        if(isLoggedIn){
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            startActivity(intent)
            //we finish this login activity so the on pressing back button from avengers activity app does not crash
            finish()
        }
        title = "Log In"

        etUserNumber = findViewById(R.id.etUserNumber)
        etUserPassword = findViewById(R.id.etUserPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtForgetPassword = findViewById(R.id.txtForgetPassword)
        txtRegisterYourself = findViewById(R.id.txtRegisterYourself)

        btnLogin.setOnClickListener {
            val mobile = etUserNumber.text.toString()
            val pwd = etUserPassword.text.toString()
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            var name = "Avengers"
            if (mobile == validNumber) {

                when (pwd) {
                    validPassword[0] -> {
                        //saving tilt of next activity in shared preferences
                        name = "Tony Stark"
                        savePreference(name)
                        //intent.putExtra("Name", name)
                        startActivity(intent)
                    }
                    validPassword[1] -> {
                        name = "Hulk"
                        savePreference(name)
                        //intent.putExtra("Name", name)
                        startActivity(intent)
                    }
                    validPassword[2] -> {
                        name = "Captain America"
                        savePreference(name)
                        //intent.putExtra("Name", name)
                        startActivity(intent)
                    }
                }

            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "Invalid Credentials",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    override fun onPause() {
        super.onPause()
        //once we login we don't want to come back to this activity again
        finish()
    }
    fun savePreference(title : String){
        //at the time of first login we save the preference to our app as a boolean variable true
        sharedPref.edit().putBoolean("isLoggedIn",true).apply()
        sharedPref.edit().putString("Title",title).apply()
    }
}