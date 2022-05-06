package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.nsbmassist.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imBtnLecHallAlloc:ImageButton=findViewById(R.id.imBtnLecHallAlloc)
        /*val imBtnRecTt:ImageButton=findViewById(R.id.imBtnRecTt)
        val imBtnEveCal:ImageButton=findViewById(R.id.imBtnEveCal)
        val imBtnStdCent:ImageButton=findViewById(R.id.imBtnStdCent)
        val imBtnTrans:ImageButton=findViewById(R.id.imBtnTrans)
        val imBtnCafe:ImageButton=findViewById(R.id.imBtnCafe)*/
        val btnLogAdmin:ImageButton=findViewById(R.id.imBtnLogAdmin)

        imBtnLecHallAlloc.setOnClickListener(){
            val intent=Intent(this, LecHallActivity::class.java)
            startActivity(intent)
            finish()
        }

       btnLogAdmin.setOnClickListener(){
            val intent=Intent(this, LogAdminActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}