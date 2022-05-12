package com.example.nsbmassist.Activity.EveCal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Activity.Foc.FocDetailsActivity
import com.example.nsbmassist.Activity.MainActivity
import com.example.nsbmassist.Adaptor.EveCalAdapter
import com.example.nsbmassist.Adaptor.SCAdapter
import com.example.nsbmassist.Model.EveCalModel
import com.example.nsbmassist.Model.SCModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class EveCalViewActivity : AppCompatActivity() {

    private lateinit var ecRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var ecList:ArrayList<EveCalModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eve_cal_view)

        ecRecyclerView=findViewById(R.id.rvEc)
        ecRecyclerView.layoutManager= LinearLayoutManager(this)
        ecRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        ecList= arrayListOf()

        getEcData()

        val btnScBK: Button =findViewById(R.id.btnBkEc)

        btnScBK.setOnClickListener(){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getEcData(){
        ecRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("EventCalender")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ecList.clear()
                if (snapshot.exists()){
                    for (ecSnap in snapshot.children){
                        val ecData=ecSnap.getValue(EveCalModel::class.java)
                        ecList.add(ecData!!)
                    }
                    val EcAdapter= EveCalAdapter(ecList)
                    ecRecyclerView.adapter=EcAdapter

                    EcAdapter.setOnItemClickListener(object : EveCalAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@EveCalViewActivity, EveCalDetailsActivity::class.java)

                            intent.putExtra("event", ecList[position].event)
                            intent.putExtra("orgBy", ecList[position].orgBy)
                            intent.putExtra("time", ecList[position].time)
                            intent.putExtra("date", ecList[position].date)
                            intent.putExtra("venue", ecList[position].venue)
                            startActivity(intent)
                        }

                    })

                    ecRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}