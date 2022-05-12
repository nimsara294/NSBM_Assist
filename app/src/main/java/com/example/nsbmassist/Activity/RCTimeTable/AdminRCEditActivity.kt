package com.example.nsbmassist.Activity.RCTimeTable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminFacultyActivity
import com.example.nsbmassist.Activity.AdminMainActivity
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.Model.RCModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminRCEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_rcedit)

        var code: EditText =findViewById(R.id.adminRcCode)
        val event: EditText =findViewById(R.id.adminRcEvent)
        val time: EditText =findViewById(R.id.adminRcTime)
        val date: EditText =findViewById(R.id.adminRcDate)
        val group: EditText =findViewById(R.id.adminRcGroup)
        val venue: EditText =findViewById(R.id.adminRcVenue)

        val imBtnAdminRcAdd: ImageButton =findViewById(R.id.imBtnAdminRcAdd)
        val imBtnAdminRcUpdate: ImageButton =findViewById(R.id.imBtnAdminRcUpdate)
        val imBtnAdminRcDelete: ImageButton =findViewById(R.id.imBtnAdminRcDelete)
        val imBtnAdminRcRst: ImageButton =findViewById(R.id.imBtnAdminRcReset)
        val imBtnAdminRcBk: ImageButton =findViewById(R.id.imBtnAdminRcBk)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("RecreationCenter")

        fun saveRcData(){

            var EveCode=code.text.toString()
            val eventName=event.text.toString()
            val eventTime=time.text.toString()
            val eventDate=date.text.toString()
            val eventGroup=group.text.toString()
            val eventVenue=venue.text.toString()

            if (EveCode.isEmpty()){
                code.error="Please Enter Code"
            }
            if (eventName.isEmpty()){
                event.error="Please Enter Event Name"
            }
            if (eventTime.isEmpty()){
                time.error="Please Enter Event Time"
            }
            if (eventDate.isEmpty()){
                date.error="Please Enter Event Date"
            }
            if (eventGroup.isEmpty()){
                group.error="Please Enter Group"
            }
            if (eventVenue.isEmpty()){
                venue.error="Please Enter Venue"
            }

            val RcEvent= RCModel(eventName,eventTime,eventDate,eventGroup,eventVenue)

            dbRef.child(EveCode).setValue(RcEvent)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    event.setText("")
                    time.setText("")
                    date.setText("")
                    group.setText("")
                    venue.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun updateRcData(){
            var EveCode=code.text.toString()
            val eventName=event.text.toString()
            val eventTime=time.text.toString()
            val eventDate=date.text.toString()
            val eventGroup=group.text.toString()
            val eventVenue=venue.text.toString()


            val RcEventUpdate= RCModel(eventName,eventTime,eventDate,eventGroup,eventVenue)

            dbRef.child(EveCode).setValue(RcEventUpdate)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Updated Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    event.setText("")
                    time.setText("")
                    date.setText("")
                    group.setText("")
                    venue.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun deleteRcData(){
            var EveCode=code.text.toString()

            dbRef.child(EveCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminRcUpdate.setOnClickListener(){
            updateRcData()
        }

        imBtnAdminRcAdd.setOnClickListener(){
            saveRcData()
        }

        imBtnAdminRcDelete.setOnClickListener(){
            deleteRcData()
        }

        imBtnAdminRcRst.setOnClickListener(){
            code.setText("")
            event.setText("")
            time.setText("")
            date.setText("")
            group.setText("")
            venue.setText("")
        }

        imBtnAdminRcBk.setOnClickListener(){
            val intent= Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
