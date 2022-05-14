package com.example.nsbmassist.Activity.Cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nsbmassist.Activity.MainActivity
import com.example.nsbmassist.Activity.Transport.TransDetailsActivity
import com.example.nsbmassist.Adaptor.CafeAdapter
import com.example.nsbmassist.Adaptor.TransAdapter
import com.example.nsbmassist.Model.CafeModel
import com.example.nsbmassist.Model.TransModel
import com.example.nsbmassist.R
import com.google.firebase.database.*

class CafeViewActivity : AppCompatActivity() {

    private lateinit var cafeRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var CafeList:ArrayList<CafeModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_view)

        cafeRecyclerView=findViewById(R.id.rvCafe)
        cafeRecyclerView.layoutManager= LinearLayoutManager(this)
        cafeRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        CafeList= arrayListOf()

        getCafeData()

        val btnCafeBK: Button =findViewById(R.id.btnBkCafe)

        btnCafeBK.setOnClickListener(){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getCafeData(){
        cafeRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("Cafe")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                CafeList.clear()
                if (snapshot.exists()){
                    for (cafeSnap in snapshot.children){
                        val cafeData=cafeSnap.getValue(CafeModel::class.java)
                        CafeList.add(cafeData!!)
                    }
                    val CafeAdaptor= CafeAdapter(CafeList)
                    cafeRecyclerView.adapter=CafeAdaptor

                    CafeAdaptor.setOnItemClickListener(object : CafeAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@CafeViewActivity, CafeDetailsActivity::class.java)

                            intent.putExtra("name", CafeList[position].name)
                            intent.putExtra("open", CafeList[position].open)
                            intent.putExtra("close", CafeList[position].close)
                            startActivity(intent)
                        }

                    })

                    cafeRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}