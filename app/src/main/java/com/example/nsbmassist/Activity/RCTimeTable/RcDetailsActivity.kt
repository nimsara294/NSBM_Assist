package com.example.nsbmassist.Activity.RCTimeTable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.nsbmassist.Activity.Foc.FocViewActivity
import com.example.nsbmassist.R

class RcDetailsActivity : AppCompatActivity() {

    private lateinit var tvEvent: TextView
    private lateinit var tvTime: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvGroup: TextView
    private lateinit var tvVenue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rc_details)

        val btnBk: Button =findViewById(R.id.btnBkRc)

        initView()
        setValuesToViews()

        btnBk.setOnClickListener(){
            val intent= Intent(this, RcViewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initView(){
        tvEvent=findViewById(R.id.tvEvent)
        tvTime=findViewById(R.id.tvTime)
        tvDate=findViewById(R.id.tvDate)
        tvGroup=findViewById(R.id.tvGroup)
        tvVenue=findViewById(R.id.tvVenue)
    }

    private fun setValuesToViews(){
        tvEvent.text=intent.getStringExtra("event")
        tvTime.text=intent.getStringExtra("time")
        tvDate.text=intent.getStringExtra("date")
        tvGroup.text=intent.getStringExtra("group")
        tvVenue.text=intent.getStringExtra("venue")
    }
}