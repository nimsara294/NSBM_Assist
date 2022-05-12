package com.example.nsbmassist.Activity.Transport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminMainActivity
import com.example.nsbmassist.Model.SCModel
import com.example.nsbmassist.Model.TransModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminTransEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_trans_edit)

        var code: EditText =findViewById(R.id.adminTransCode)
        val start: EditText =findViewById(R.id.adminTransStart)
        val dest: EditText =findViewById(R.id.adminTransDest)
        val arrTime: EditText =findViewById(R.id.adminTransArrTime)
        val depTime: EditText =findViewById(R.id.adminTransDepTime)

        val imBtnAdminTransAdd: ImageButton =findViewById(R.id.imBtnAdminTransAdd)
        val imBtnAdminTransUpdate: ImageButton =findViewById(R.id.imBtnAdminTransUpdate)
        val imBtnAdminTransDelete: ImageButton =findViewById(R.id.imBtnAdminTransDelete)
        val imBtnAdminTransRst: ImageButton =findViewById(R.id.imBtnAdminTransReset)
        val imBtnAdminTransBk: ImageButton =findViewById(R.id.imBtnAdminTransBk)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Transportation")

        fun saveTransData(){

            var transCode=code.text.toString()
            val startLoc=start.text.toString()
            val destLoc=dest.text.toString()
            val transArrTime=arrTime.text.toString()
            val transDepTime=depTime.text.toString()

            if (transCode.isEmpty()){
                code.error="Please Enter Code"
            }
            if (startLoc.isEmpty()){
                start.error="Please Enter Event Name"
            }
            if (destLoc.isEmpty()){
                dest.error="Please Enter Event Time"
            }
            if (transArrTime.isEmpty()){
                arrTime.error="Please Enter Event Date"
            }
            if (transDepTime.isEmpty()){
                depTime.error="Please Enter Organization parties"
            }

            val transTrip= TransModel(startLoc,destLoc,transArrTime,transDepTime)

            dbRef.child(transCode).setValue(transTrip)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    start.setText("")
                    dest.setText("")
                    arrTime.setText("")
                    depTime.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun updateTransData(){

            var transCode=code.text.toString()
            val startLoc=start.text.toString()
            val destLoc=dest.text.toString()
            val transArrTime=arrTime.text.toString()
            val transDepTime=depTime.text.toString()


            val transTripUpdate= TransModel(startLoc,destLoc,transArrTime,transDepTime)

            dbRef.child(transCode).setValue(transTripUpdate)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Updated Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    start.setText("")
                    dest.setText("")
                    arrTime.setText("")
                    depTime.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun deleteTransData(){
            var transCode=code.text.toString()

            dbRef.child(transCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminTransUpdate.setOnClickListener(){
            updateTransData()
        }

        imBtnAdminTransAdd.setOnClickListener(){
            saveTransData()
        }

        imBtnAdminTransDelete.setOnClickListener(){
            deleteTransData()
        }

        imBtnAdminTransRst.setOnClickListener(){
            code.setText("")
            start.setText("")
            dest.setText("")
            arrTime.setText("")
            depTime.setText("")
        }

        imBtnAdminTransBk.setOnClickListener(){
            val intent= Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}