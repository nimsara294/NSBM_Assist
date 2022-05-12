package com.example.nsbmassist.Activity.SCEvents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminFacultyActivity
import com.example.nsbmassist.Activity.AdminMainActivity
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.Model.SCModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminSCEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_scedit)

        var code: EditText =findViewById(R.id.adminScCode)
        val event: EditText =findViewById(R.id.adminScEvent)
        val time: EditText =findViewById(R.id.adminScTime)
        val date: EditText =findViewById(R.id.adminScDate)
        val orgBy: EditText =findViewById(R.id.adminScOrgBy)

        val imBtnAdminScAdd: ImageButton =findViewById(R.id.imBtnAdminScAdd)
        val imBtnAdminScUpdate: ImageButton =findViewById(R.id.imBtnAdminScUpdate)
        val imBtnAdminScDelete: ImageButton =findViewById(R.id.imBtnAdminScDelete)
        val imBtnAdminScRst: ImageButton =findViewById(R.id.imBtnAdminScReset)
        val imBtnAdminScBk: ImageButton =findViewById(R.id.imBtnAdminScBk)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("StudentCenter")

        fun saveScData(){

            var eveCode=code.text.toString()
            val eveName=event.text.toString()
            val eveTime=time.text.toString()
            val eveDate=date.text.toString()
            val eveOrgBy=orgBy.text.toString()

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

            val scEvent= SCModel(eveName,eveOrgBy,eveTime,eveDate)

            dbRef.child(eveCode).setValue(scEvent)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    event.setText("")
                    orgBy.setText("")
                    time.setText("")
                    date.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun updateScData(){
            var eveCode=code.text.toString()
            val eveName=event.text.toString()
            val eveTime=time.text.toString()
            val eveDate=date.text.toString()
            val eveOrgBy=orgBy.text.toString()


            val scEventUpdate= SCModel(eveName,eveOrgBy,eveTime,eveDate)

            dbRef.child(eveCode).setValue(scEventUpdate)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Updated Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    event.setText("")
                    orgBy.setText("")
                    time.setText("")
                    date.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun deleteScData(){
            var eveCode=code.text.toString()

            dbRef.child(eveCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminScUpdate.setOnClickListener(){
            updateScData()
        }

        imBtnAdminScAdd.setOnClickListener(){
            saveScData()
        }

        imBtnAdminScDelete.setOnClickListener(){
            deleteScData()
        }

        imBtnAdminScRst.setOnClickListener(){
            code.setText("")
            event.setText("")
            orgBy.setText("")
            time.setText("")
            date.setText("")
        }

        imBtnAdminScBk.setOnClickListener(){
            val intent= Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}