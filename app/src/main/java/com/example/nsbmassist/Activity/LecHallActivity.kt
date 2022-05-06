package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.nsbmassist.R

class LecHallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lec_hall)

        val imBtnFoc:ImageButton=findViewById(R.id.imBtnFoc)
        val imBtnBkFac:ImageButton=findViewById(R.id.imBtnBkFac)

        imBtnFoc.setOnClickListener(){
            val intent=Intent(this, FocViewActivity::class.java)
            startActivity(intent)
            finish()
        }

        imBtnBkFac.setOnClickListener(){
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}