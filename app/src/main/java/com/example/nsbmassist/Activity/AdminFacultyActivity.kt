package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.nsbmassist.R

class AdminFacultyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_faculty)

        val btnAdminFoc:Button=findViewById(R.id.btnAdminFoc)
        val btnAdminBkFac:Button=findViewById(R.id.btnAdminBkFac)

        btnAdminBkFac.setOnClickListener(){
            val intent=Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminFoc.setOnClickListener(){
            val intent=Intent(this, AdminFocAddDataActivity ::class.java)
            startActivity(intent)
            finish()
        }
    }
}