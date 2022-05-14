package com.example.nsbmassist.Activity.Cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.nsbmassist.Activity.Transport.TransViewActivity
import com.example.nsbmassist.R

class CafeDetailsActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvOpen: TextView
    private lateinit var tvClose: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_details)


        val btnBk: Button = findViewById(R.id.btnBkCafe)

        initView()
        setValuesToViews()

        btnBk.setOnClickListener() {
            val intent = Intent(this, CafeViewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun initView() {
        tvName = findViewById(R.id.tvName)
        tvOpen = findViewById(R.id.tvOpensAt)
        tvClose = findViewById(R.id.tvClosesAt)
    }

    private fun setValuesToViews() {
        tvName.text = intent.getStringExtra("name")
        tvOpen.text = intent.getStringExtra("open")
        tvClose.text = intent.getStringExtra("close")
    }
}