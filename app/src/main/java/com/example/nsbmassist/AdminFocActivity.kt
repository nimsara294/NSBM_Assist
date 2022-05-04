package com.example.nsbmassist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class AdminFocActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_foc)

        val imBtnAdminFocBk: ImageButton =findViewById(R.id.imBtnAdminFocBk)

        imBtnAdminFocBk.setOnClickListener(){
            val intent= Intent(this,AdminFacultyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}