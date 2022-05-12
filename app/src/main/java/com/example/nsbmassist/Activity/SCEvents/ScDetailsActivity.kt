package com.example.nsbmassist.Activity.SCEvents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.nsbmassist.Activity.Foc.FocViewActivity
import com.example.nsbmassist.R

class ScDetailsActivity : AppCompatActivity() {

    private lateinit var tvEveName: TextView
    private lateinit var tvOrgBy: TextView
    private lateinit var tvTime: TextView
    private lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sc_details)

        val btnBk: Button =findViewById(R.id.btnBkSc)

        initView()
        setValuesToViews()

        btnBk.setOnClickListener(){
            val intent= Intent(this, ScViewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initView(){
        tvEveName=findViewById(R.id.tvEveName)
        tvOrgBy=findViewById(R.id.tvOrgBy)
        tvTime=findViewById(R.id.tvTime)
        tvDate=findViewById(R.id.tvDate)
    }

    private fun setValuesToViews(){
        tvEveName.text=intent.getStringExtra("event")
        tvOrgBy.text=intent.getStringExtra("orgBy")
        tvTime.text=intent.getStringExtra("time")
        tvDate.text=intent.getStringExtra("date")
    }
}