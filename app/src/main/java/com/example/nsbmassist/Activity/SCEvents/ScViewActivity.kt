package com.example.nsbmassist.Activity.SCEvents

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
import com.example.nsbmassist.Adaptor.SCAdapter
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.Model.SCModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class ScViewActivity : AppCompatActivity() {

    private lateinit var scRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var scList:ArrayList<SCModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sc_view)

        scRecyclerView=findViewById(R.id.rvSc)
        scRecyclerView.layoutManager= LinearLayoutManager(this)
        scRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        scList= arrayListOf()

        getScData()

        val btnScBK: Button =findViewById(R.id.btnBkSc)

        btnScBK.setOnClickListener(){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getScData(){
        scRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("StudentCenter")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                scList.clear()
                if (snapshot.exists()){
                    for (scSnap in snapshot.children){
                        val scData=scSnap.getValue(SCModel::class.java)
                        scList.add(scData!!)
                    }
                    val ScAdaptor= SCAdapter(scList)
                    scRecyclerView.adapter=ScAdaptor

                    ScAdaptor.setOnItemClickListener(object : SCAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@ScViewActivity, ScDetailsActivity::class.java)

                            intent.putExtra("event", scList[position].event)
                            intent.putExtra("orgBy", scList[position].orgBy)
                            intent.putExtra("time", scList[position].time)
                            intent.putExtra("date", scList[position].date)
                            startActivity(intent)
                        }

                    })

                    scRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}