package com.example.nsbmassist.Activity.Transport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.nsbmassist.Activity.SCEvents.ScViewActivity
import com.example.nsbmassist.R

class TransDetailsActivity : AppCompatActivity() {

    private lateinit var tvStart: TextView
    private lateinit var tvDest: TextView
    private lateinit var tvArrTime: TextView
    private lateinit var tvDepTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans_details)
        val btnBk: Button =findViewById(R.id.btnBkTrans)

        initView()
        setValuesToViews()

        btnBk.setOnClickListener(){
            val intent= Intent(this, TransViewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initView(){
        tvStart=findViewById(R.id.tvStartLoc)
        tvDest=findViewById(R.id.tvDest)
        tvArrTime=findViewById(R.id.tvArrTime)
        tvDepTime=findViewById(R.id.tvDepTime)
    }

    private fun setValuesToViews(){
        tvStart.text=intent.getStringExtra("start")
        tvDest.text=intent.getStringExtra("dest")
        tvArrTime.text=intent.getStringExtra("arrTime")
        tvDepTime.text=intent.getStringExtra("depTime")
    }
}