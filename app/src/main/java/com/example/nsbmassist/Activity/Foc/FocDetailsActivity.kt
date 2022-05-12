package com.example.nsbmassist.Activity.Foc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.nsbmassist.R

class FocDetailsActivity : AppCompatActivity() {

    private lateinit var tvLect:TextView
    private lateinit var tvLectName:TextView
    private lateinit var tvTime:TextView
    private lateinit var tvDate:TextView
    private lateinit var tvBatch:TextView
    private lateinit var tvHall:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foc_details)

        val btnBk:Button=findViewById(R.id.btnBkFoc)

        initView()
        setValuesToViews()

        btnBk.setOnClickListener(){
            val intent= Intent(this, FocViewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initView(){
        tvLect=findViewById(R.id.tvLoad)
        tvLectName=findViewById(R.id.tvLectName)
        tvTime=findViewById(R.id.tvTime)
        tvDate=findViewById(R.id.tvDate)
        tvBatch=findViewById(R.id.tvBatch)
        tvHall=findViewById(R.id.tvHall)
    }

    private fun setValuesToViews(){
        tvLect.text=intent.getStringExtra("lect")
        tvLectName.text=intent.getStringExtra("lectName")
        tvTime.text=intent.getStringExtra("time")
        tvDate.text=intent.getStringExtra("date")
        tvBatch.text=intent.getStringExtra("batch")
        tvHall.text=intent.getStringExtra("hall")
    }

}