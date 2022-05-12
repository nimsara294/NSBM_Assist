package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.nsbmassist.Activity.Fob.AdminFobEditActivity
import com.example.nsbmassist.Activity.Foc.AdminFocEditActivity
import com.example.nsbmassist.Activity.Foe.AdminFoeEditActivity
import com.example.nsbmassist.R

class AdminFacultyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_faculty)

        val btnAdminFoc:Button=findViewById(R.id.btnAdminFoc)
        val btnAdminFob:Button=findViewById(R.id.btnAdminFob)
        val btnAdminFoe:Button=findViewById(R.id.btnAdminFoe)
        val btnAdminBkFac:Button=findViewById(R.id.btnAdminBkFac)

        btnAdminBkFac.setOnClickListener(){
            val intent=Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminFoc.setOnClickListener(){
            val intent=Intent(this, AdminFocEditActivity ::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminFoe.setOnClickListener(){
            val intent=Intent(this, AdminFoeEditActivity ::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminFob.setOnClickListener(){
            val intent=Intent(this, AdminFobEditActivity ::class.java)
            startActivity(intent)
            finish()
        }
    }
}