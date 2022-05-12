package com.example.nsbmassist.Activity.Foe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminFacultyActivity
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.Model.FoeModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminFoeEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_foe_edit)

        var code: EditText =findViewById(R.id.adminFoeCode)
        val lect: EditText =findViewById(R.id.adminFoeLecture)
        val lecturer: EditText =findViewById(R.id.adminFoeLecturerName)
        val time: EditText =findViewById(R.id.adminFoeTime)
        val date: EditText =findViewById(R.id.adminFoeDate)
        val batch: EditText =findViewById(R.id.adminFoeBatchNum)
        val hall: EditText =findViewById(R.id.adminFoeHallNum)

        val imBtnAdminFoeAdd: ImageButton =findViewById(R.id.imBtnAdminFoeAdd)
        val imBtnAdminFoeUpdate: ImageButton =findViewById(R.id.imBtnAdminFoeUpdate)
        val imBtnAdminFoeDelete: ImageButton =findViewById(R.id.imBtnAdminFoeDelete)
        val imBtnAdminFoeRst: ImageButton =findViewById(R.id.imBtnAdminFoeReset)
        val imBtnAdminFoeBk: ImageButton =findViewById(R.id.imBtnAdminFoeBk)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("FoeLecture")

        fun saveFoeLectureData(){

            var lectCode=code.text.toString()
            val lectName=lect.text.toString()
            val lecturerName=lecturer.text.toString()
            val lecttime=time.text.toString()
            val lectdate=date.text.toString()
            val batchnum=batch.text.toString()
            val hallnum=hall.text.toString()

            if (lectCode.isEmpty()){
                code.error="Please Enter ID"
            }
            if (lectName.isEmpty()){
                lect.error="Please Enter Lecture"
            }
            if (lecturerName.isEmpty()){
                lecturer.error="Please Enter Lecturer Name"
            }
            if (lecttime.isEmpty()){
                time.error="Please Enter Lecture Time"
            }
            if (lectdate.isEmpty()){
                date.error="Please Enter Lecture Date"
            }
            if (batchnum.isEmpty()){
                batch.error="Please Enter Batch Number"
            }
            if (hallnum.isEmpty()){
                hall.error="Please Enter Hall Number"
            }

            val lecture= FoeModel(lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

            dbRef.child(lectCode).setValue(lecture)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    lect.setText("")
                    lecturer.setText("")
                    time.setText("")
                    date.setText("")
                    batch.setText("")
                    hall.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun updateFoeLectureData(){
            var lectCode=code.text.toString()
            val lectName=lect.text.toString()
            val lecturerName=lecturer.text.toString()
            val lecttime=time.text.toString()
            val lectdate=date.text.toString()
            val batchnum=batch.text.toString()
            val hallnum=hall.text.toString()


            val lectureUpdate= FoeModel(lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

            dbRef.child(lectCode).setValue(lectureUpdate)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Updated Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    lect.setText("")
                    lecturer.setText("")
                    time.setText("")
                    date.setText("")
                    batch.setText("")
                    hall.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun deleteFoeLectureData(){
            var lectCode=code.text.toString()

            dbRef.child(lectCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminFoeUpdate.setOnClickListener(){
            updateFoeLectureData()
        }

        imBtnAdminFoeAdd.setOnClickListener(){
            saveFoeLectureData()
        }

        imBtnAdminFoeDelete.setOnClickListener(){
            deleteFoeLectureData()
        }

        imBtnAdminFoeRst.setOnClickListener(){
            code.setText("")
            lect.setText("")
            lecturer.setText("")
            time.setText("")
            date.setText("")
            batch.setText("")
            hall.setText("")
        }

        imBtnAdminFoeBk.setOnClickListener(){
            val intent= Intent(this, AdminFacultyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
