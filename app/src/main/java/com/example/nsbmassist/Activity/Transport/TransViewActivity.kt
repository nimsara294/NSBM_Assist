package com.example.nsbmassist.Activity.Transport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Activity.MainActivity
import com.example.nsbmassist.Activity.SCEvents.ScDetailsActivity
import com.example.nsbmassist.Adaptor.SCAdapter
import com.example.nsbmassist.Adaptor.TransAdapter
import com.example.nsbmassist.Model.SCModel
import com.example.nsbmassist.Model.TransModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class TransViewActivity : AppCompatActivity() {

    private lateinit var transRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var transList:ArrayList<TransModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans_view)

        transRecyclerView=findViewById(R.id.rvTrans)
        transRecyclerView.layoutManager= LinearLayoutManager(this)
        transRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        transList= arrayListOf()

        getTransData()

        val btnTransBK: Button =findViewById(R.id.btnBkTrans)

        btnTransBK.setOnClickListener(){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getTransData(){
        transRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("Transportation")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                transList.clear()
                if (snapshot.exists()){
                    for (transSnap in snapshot.children){
                        val transData=transSnap.getValue(TransModel::class.java)
                        transList.add(transData!!)
                    }
                    val TransAdaptor= TransAdapter(transList)
                    transRecyclerView.adapter=TransAdaptor

                    TransAdaptor.setOnItemClickListener(object : TransAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@TransViewActivity, TransDetailsActivity::class.java)

                            intent.putExtra("start", transList[position].start)
                            intent.putExtra("dest", transList[position].dest)
                            intent.putExtra("arrTime", transList[position].arrTime)
                            intent.putExtra("depTime", transList[position].depTime)
                            startActivity(intent)
                        }

                    })

                    transRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}