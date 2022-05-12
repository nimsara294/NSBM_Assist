package com.example.nsbmassist.Activity.Fob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminFacultyActivity
import com.example.nsbmassist.Model.FobModel
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminFobEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_fob_edit)

        var code: EditText =findViewById(R.id.adminFobCode)
        val lect: EditText =findViewById(R.id.adminFobLecture)
        val lecturer: EditText =findViewById(R.id.adminFobLecturerName)
        val time: EditText =findViewById(R.id.adminFobTime)
        val date: EditText =findViewById(R.id.adminFobDate)
        val batch: EditText =findViewById(R.id.adminFobBatchNum)
        val hall: EditText =findViewById(R.id.adminFobHallNum)

        val imBtnAdminFobAdd: ImageButton =findViewById(R.id.imBtnAdminFobAdd)
        val imBtnAdminFobUpdate: ImageButton =findViewById(R.id.imBtnAdminFobUpdate)
        val imBtnAdminFobDelete: ImageButton =findViewById(R.id.imBtnAdminFobDelete)
        val imBtnAdminFobRst: ImageButton =findViewById(R.id.imBtnAdminFobReset)
        val imBtnAdminFobBk: ImageButton =findViewById(R.id.imBtnAdminFobBk)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("FobLecture")

        fun saveFobLectureData(){

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

            val lecture= FobModel(lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

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

        fun updateFobLectureData(){
            var lectCode=code.text.toString()
            val lectName=lect.text.toString()
            val lecturerName=lecturer.text.toString()
            val lecttime=time.text.toString()
            val lectdate=date.text.toString()
            val batchnum=batch.text.toString()
            val hallnum=hall.text.toString()


            val lectureUpdate= FobModel(lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

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

        fun deleteFobLectureData(){
            var lectCode=code.text.toString()

            dbRef.child(lectCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminFobUpdate.setOnClickListener(){
            updateFobLectureData()
        }

        imBtnAdminFobAdd.setOnClickListener(){
            saveFobLectureData()
        }

        imBtnAdminFobDelete.setOnClickListener(){
            deleteFobLectureData()
        }

        imBtnAdminFobRst.setOnClickListener(){
            code.setText("")
            lect.setText("")
            lecturer.setText("")
            time.setText("")
            date.setText("")
            batch.setText("")
            hall.setText("")
        }

        imBtnAdminFobBk.setOnClickListener(){
            val intent= Intent(this, AdminFacultyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}