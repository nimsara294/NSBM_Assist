package com.example.nsbmassist.Activity.EveCal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminMainActivity
import com.example.nsbmassist.Model.EveCalModel
import com.example.nsbmassist.Model.SCModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminEveCalEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_eve_cal_edit)

        var code: EditText =findViewById(R.id.adminEcCode)
        val event: EditText =findViewById(R.id.adminEcEvent)
        val time: EditText =findViewById(R.id.adminEcTime)
        val date: EditText =findViewById(R.id.adminEcDate)
        val orgBy: EditText =findViewById(R.id.adminEcOrgBy)
        val venue: EditText=findViewById(R.id.adminEcVenue)

        val imBtnAdminEcAdd: ImageButton =findViewById(R.id.imBtnAdminEcAdd)
        val imBtnAdminEcUpdate: ImageButton =findViewById(R.id.imBtnAdminEcUpdate)
        val imBtnAdminEcDelete: ImageButton =findViewById(R.id.imBtnAdminEcDelete)
        val imBtnAdminEcRst: ImageButton =findViewById(R.id.imBtnAdminEcReset)
        val imBtnAdminEcBk: ImageButton =findViewById(R.id.imBtnAdminEcBk)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("EventCalender")

        fun saveEcData(){

            var eveCode=code.text.toString()
            val eveName=event.text.toString()
            val eveTime=time.text.toString()
            val eveDate=date.text.toString()
            val eveOrgBy=orgBy.text.toString()
            val eveVenue=venue.text.toString()

            if (eveCode.isEmpty()){
                code.error="Please Enter Code"
            }
            if (eveName.isEmpty()){
                event.error="Please Enter Event Name"
            }
            if (eveTime.isEmpty()){
                time.error="Please Enter Event Time"
            }
            if (eveDate.isEmpty()){
                date.error="Please Enter Event Date"
            }
            if (eveOrgBy.isEmpty()){
                orgBy.error="Please Enter Organization parties"
            }
            if (eveVenue.isEmpty()){
                venue.error="Please Enter Event Venue"
            }

            val ecEvent= EveCalModel(eveName,eveOrgBy,eveTime,eveDate,eveVenue)

            dbRef.child(eveCode).setValue(ecEvent)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    event.setText("")
                    orgBy.setText("")
                    time.setText("")
                    date.setText("")
                    venue.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun updateEcData(){

            var eveCode=code.text.toString()
            val eveName=event.text.toString()
            val eveTime=time.text.toString()
            val eveDate=date.text.toString()
            val eveOrgBy=orgBy.text.toString()
            val eveVenue=venue.text.toString()


            val ecEventUpdate= EveCalModel(eveName,eveOrgBy,eveTime,eveDate,eveVenue)

            dbRef.child(eveCode).setValue(ecEventUpdate)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Updated Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    event.setText("")
                    orgBy.setText("")
                    time.setText("")
                    date.setText("")
                    venue.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun deleteEcData(){
            var eveCode=code.text.toString()

            dbRef.child(eveCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminEcUpdate.setOnClickListener(){
            updateEcData()
        }

        imBtnAdminEcAdd.setOnClickListener(){
            saveEcData()
        }

        imBtnAdminEcDelete.setOnClickListener(){
            deleteEcData()
        }

        imBtnAdminEcRst.setOnClickListener(){
            code.setText("")
            event.setText("")
            orgBy.setText("")
            time.setText("")
            date.setText("")
            venue.setText("")
        }

        imBtnAdminEcBk.setOnClickListener(){
            val intent= Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
