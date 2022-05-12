package com.example.nsbmassist.Activity.Foe

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
import com.example.nsbmassist.Adaptor.FocAdapter
import com.example.nsbmassist.Adaptor.FoeAdapter
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.Model.FoeModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class FoeViewActivity : AppCompatActivity() {

    private lateinit var foeRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var foeList:ArrayList<FoeModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foe_view)

        foeRecyclerView=findViewById(R.id.rvFoe)
        foeRecyclerView.layoutManager= LinearLayoutManager(this)
        foeRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        foeList= arrayListOf()

        getFoeLectData()

        val btnFoeBK: Button =findViewById(R.id.btnBkFoe)

        btnFoeBK.setOnClickListener(){
            val intent= Intent(this, LecHallActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getFoeLectData(){
        foeRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("FoeLecture")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foeList.clear()
                if (snapshot.exists()){
                    for (foeSnap in snapshot.children){
                        val foeLectData=foeSnap.getValue(FoeModel::class.java)
                        foeList.add(foeLectData!!)
                    }
                    val lectAdaptor= FoeAdapter(foeList)
                    foeRecyclerView.adapter=lectAdaptor

                    lectAdaptor.setOnItemClickListener(object : FoeAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@FoeViewActivity, FoeDetailsActivity::class.java)

                            intent.putExtra("lect", foeList[position].lect)
                            intent.putExtra("lectName", foeList[position].lectName)
                            intent.putExtra("time", foeList[position].time)
                            intent.putExtra("date", foeList[position].date)
                            intent.putExtra("batch", foeList[position].batch)
                            intent.putExtra("hall", foeList[position].hall)
                            startActivity(intent)
                        }

                    })

                    foeRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}