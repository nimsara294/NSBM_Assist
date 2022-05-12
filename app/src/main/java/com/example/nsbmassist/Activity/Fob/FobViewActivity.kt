package com.example.nsbmassist.Activity.Fob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Activity.LecHallActivity
import com.example.nsbmassist.Adaptor.FobAdapter
import com.example.nsbmassist.Model.FobModel
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class FobViewActivity : AppCompatActivity() {

    private lateinit var fobRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var foblist: ArrayList<FobModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fob_view)

        fobRecyclerView = findViewById(R.id.rvFob)
        fobRecyclerView.layoutManager = LinearLayoutManager(this)
        fobRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        foblist = arrayListOf()

        getFobLectData()

        val btnFocBK: Button = findViewById(R.id.btnBkFob)

        btnFocBK.setOnClickListener() {
            val intent = Intent(this, LecHallActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getFobLectData() {
        fobRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("FobLecture")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foblist.clear()
                if (snapshot.exists()) {
                    for (fobSnap in snapshot.children) {
                        val fobLectData = fobSnap.getValue(FobModel::class.java)
                        foblist.add(fobLectData!!)
                    }
                    val lectAdaptor = FobAdapter(foblist)
                    fobRecyclerView.adapter = lectAdaptor

                    lectAdaptor.setOnItemClickListener(object : FobAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent =
                                Intent(this@FobViewActivity, FobDetailsActivity::class.java)

                            intent.putExtra("lect", foblist[position].lect)
                            intent.putExtra("lectName", foblist[position].lectName)
                            intent.putExtra("time", foblist[position].time)
                            intent.putExtra("date", foblist[position].date)
                            intent.putExtra("batch", foblist[position].batch)
                            intent.putExtra("hall", foblist[position].hall)
                            startActivity(intent)
                        }

                    })

                    fobRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
