package com.yuktachauhan.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AvengersActivity : AppCompatActivity() {
    var titleName : String?="THE AVENGERS"

    lateinit var etMessage:EditText
    lateinit var btMessage:Button
    lateinit var btnLogOut:Button
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        //bundle stores the saved instance of the activity so that when we rotate the activity the transition is seem less
        //bundles are also used to transfer content of one activity to other activity
        super.onCreate(savedInstanceState)

        sharedPref=getSharedPreferences(getString(R.string.Shared_Preference_of_this_app), Context.MODE_PRIVATE)

        //R is for resources and it is used when we want to add resources every resource is given some id
        //these ids are stored in R file
        setContentView(R.layout.avengers_activity)
        //this title will be appear on the top in app's toolbar
//        if(intent!=null){
//            titleName=intent.getStringExtra("Name")
//        }
        titleName=sharedPref.getString("Title","The Avengers")
        title =titleName

        etMessage=findViewById(R.id.etMessage)
        btMessage=findViewById(R.id.btMessage)
        btnLogOut=findViewById(R.id.btnLogOut)

        btMessage.setOnClickListener {
            var msg=etMessage.text.toString()
            var intent=Intent(this@AvengersActivity,MessageActivity::class.java)
            intent.putExtra("msg",msg)
            startActivity(intent)
        }
        btnLogOut.setOnClickListener{
            sharedPref.edit().clear().apply()
            var intent = Intent(this@AvengersActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}