package com.yuktachauhan.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MessageActivity : AppCompatActivity() {
    lateinit var txtMessage:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        txtMessage=findViewById(R.id.txtMessage)
        if(intent!=null){
            txtMessage.text=intent.getStringExtra("msg")
        }
    }

}