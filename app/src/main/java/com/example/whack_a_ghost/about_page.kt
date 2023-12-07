package com.example.whack_a_ghost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class about_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // return to start page
        val backbtn = findViewById<ImageButton>(R.id.bckbtn)
        backbtn.setOnClickListener {
            val intent = Intent(this, start_page::class.java)
            startActivity(intent)
        }

    }
}