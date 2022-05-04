package com.example.nsbmassist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminFacultyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_faculty)

        val btnAdminFoc:Button=findViewById(R.id.btnAdminFoc)
        val btnAdminBkFac:Button=findViewById(R.id.btnAdminBkFac)

        btnAdminBkFac.setOnClickListener(){
            val intent=Intent(this,AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminFoc.setOnClickListener(){
            val intent=Intent(this,AdminFocActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}