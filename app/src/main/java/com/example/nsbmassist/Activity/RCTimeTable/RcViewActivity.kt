package com.example.nsbmassist.Activity.RCTimeTable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Activity.Foc.FocDetailsActivity
import com.example.nsbmassist.Activity.LecHallActivity
import com.example.nsbmassist.Activity.MainActivity
import com.example.nsbmassist.Adaptor.FocAdapter
import com.example.nsbmassist.Adaptor.RCAdapter
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.Model.RCModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class RcViewActivity : AppCompatActivity() {

    private lateinit var RcRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var RcList:ArrayList<RCModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rc_view)

        RcRecyclerView=findViewById(R.id.rvRc)
        RcRecyclerView.layoutManager= LinearLayoutManager(this)
        RcRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        RcList= arrayListOf()

        getRcData()

        val btnRcBK: Button =findViewById(R.id.btnBkRc)

        btnRcBK.setOnClickListener(){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getRcData(){
        RcRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("RecreationCenter")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                RcList.clear()
                if (snapshot.exists()){
                    for (RcSnap in snapshot.children){
                        val RcData=RcSnap.getValue(RCModel::class.java)
                        RcList.add(RcData!!)
                    }
                    val RcDataAdaptor= RCAdapter(RcList)
                    RcRecyclerView.adapter=RcDataAdaptor

                    RcDataAdaptor.setOnItemClickListener(object : RCAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@RcViewActivity, RcDetailsActivity::class.java)

                            intent.putExtra("event", RcList[position].event)
                            intent.putExtra("time", RcList[position].time)
                            intent.putExtra("date", RcList[position].date)
                            intent.putExtra("group", RcList[position].group)
                            intent.putExtra("venue", RcList[position].venue)
                            startActivity(intent)
                        }

                    })

                    RcRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}