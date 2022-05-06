package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Adaptor.FocAdapter
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class FocViewActivity : AppCompatActivity() {

    private lateinit var focRecyclerView:RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var focList:ArrayList<FocModel>
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foc_view)

        focRecyclerView=findViewById(R.id.rvFoc)
        focRecyclerView.layoutManager=LinearLayoutManager(this)
        focRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        focList= arrayListOf()

        getFocLectData()

        val imBtnFocBK:ImageButton=findViewById(R.id.imBtnBkFoc)

        imBtnFocBK.setOnClickListener(){
            val intent=Intent(this,LecHallActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getFocLectData(){
        focRecyclerView.visibility=View.GONE
        tvLoadingData.visibility=View.VISIBLE

        dbRef=FirebaseDatabase.getInstance().getReference("FocLecture")

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                focList.clear()
                if (snapshot.exists()){
                    for (focSnap in snapshot.children){
                        val focLectData=focSnap.getValue(FocModel::class.java)
                        focList.add(focLectData!!)
                    }
                    val lectAdaptor=FocAdapter(focList)
                    focRecyclerView.adapter=lectAdaptor

                    lectAdaptor.setOnItemClickListener(object :FocAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@FocViewActivity, FocDetailsActivity::class.java)

                            intent.putExtra("lect", focList[position].lect)
                            intent.putExtra("lectName", focList[position].lectName)
                            intent.putExtra("time", focList[position].time)
                            intent.putExtra("date", focList[position].date)
                            intent.putExtra("batch", focList[position].batch)
                            intent.putExtra("hall", focList[position].hall)
                            startActivity(intent)
                        }

                    })

                    focRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}