package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.nsbmassist.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

        val btnAdminLecHallAlloc: Button =findViewById(R.id.btnAdminLecHallAlloc)
        val imBtnLogOutAdmin:ImageButton=findViewById(R.id.imBtnLogOutAdmin)

        btnAdminLecHallAlloc.setOnClickListener(){
            val intent=Intent(this, AdminFacultyActivity::class.java)
            startActivity(intent)
            finish()
        }

        imBtnLogOutAdmin.setOnClickListener(){
            Firebase.auth.signOut()
            val intent=Intent(this, LogAdminActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}