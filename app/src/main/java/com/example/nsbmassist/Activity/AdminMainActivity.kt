package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.nsbmassist.Activity.Cafe.AdminCafeEditActivity
import com.example.nsbmassist.Activity.EveCal.AdminEveCalEditActivity
import com.example.nsbmassist.Activity.RCTimeTable.AdminRCEditActivity
import com.example.nsbmassist.Activity.SCEvents.AdminSCEditActivity
import com.example.nsbmassist.Activity.Transport.AdminTransEditActivity
import com.example.nsbmassist.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

        val btnAdminLecHallAlloc: Button =findViewById(R.id.btnAdminLecHallAlloc)
        val btnAdminRC: Button =findViewById(R.id.btnAdminRecTt)
        val btnAdminSc:Button=findViewById(R.id.btnAdminStudCent)
        val btnAdminEc:Button=findViewById(R.id.btnAdminEveCal)
        val btnAdminTrans: Button=findViewById(R.id.btnAdminTrans)
        val btnAdminCafe:Button=findViewById(R.id.btnAdminCafe)
        val imBtnLogOutAdmin:ImageButton=findViewById(R.id.imBtnLogOutAdmin)

        btnAdminLecHallAlloc.setOnClickListener(){
            val intent=Intent(this, AdminFacultyActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminSc.setOnClickListener(){
            val intent=Intent(this, AdminSCEditActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminRC.setOnClickListener(){
            val intent=Intent(this, AdminRCEditActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminEc.setOnClickListener(){
            val intent=Intent(this, AdminEveCalEditActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminTrans.setOnClickListener(){
            val intent=Intent(this, AdminTransEditActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnAdminCafe.setOnClickListener(){
            val intent=Intent(this, AdminCafeEditActivity::class.java)
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