package com.example.nsbmassist.Activity.Foc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminFacultyActivity
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminFocEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_foc_add_data)

        var code:EditText=findViewById(R.id.adminFocCode)
        val lect:EditText=findViewById(R.id.adminFocLecture)
        val lecturer:EditText=findViewById(R.id.adminFocLecturerName)
        val time:EditText=findViewById(R.id.adminFocTime)
        val date:EditText=findViewById(R.id.adminFocDate)
        val batch:EditText=findViewById(R.id.adminFocBatchNum)
        val hall:EditText=findViewById(R.id.adminFocHallNum)

        val imBtnAdminFocAdd:ImageButton=findViewById(R.id.imBtnAdminFocAdd)
        val imBtnAdminFocUpdate:ImageButton=findViewById(R.id.imBtnAdminFocUpdate)
        val imBtnAdminFocDelete: ImageButton=findViewById(R.id.imBtnAdminFocDelete)
        val imBtnAdminFocRst:ImageButton=findViewById(R.id.imBtnAdminFocReset)
        val imBtnAdminFocBk: ImageButton =findViewById(R.id.imBtnAdminFocBk)

        var dbRef:DatabaseReference=FirebaseDatabase.getInstance().getReference("FocLecture")

        fun saveFocLectureData(){

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

            val lecture= FocModel(lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

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

        fun updateFocLectureData(){
            var lectCode=code.text.toString()
            val lectName=lect.text.toString()
            val lecturerName=lecturer.text.toString()
            val lecttime=time.text.toString()
            val lectdate=date.text.toString()
            val batchnum=batch.text.toString()
            val hallnum=hall.text.toString()


            val lectureUpdate= FocModel(lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

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

        fun deleteFocLectureData(){
            var lectCode=code.text.toString()

            dbRef.child(lectCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminFocUpdate.setOnClickListener(){
            updateFocLectureData()
        }

        imBtnAdminFocAdd.setOnClickListener(){
            saveFocLectureData()
        }

        imBtnAdminFocDelete.setOnClickListener(){
            deleteFocLectureData()
        }

        imBtnAdminFocRst.setOnClickListener(){
            code.setText("")
            lect.setText("")
            lecturer.setText("")
            time.setText("")
            date.setText("")
            batch.setText("")
            hall.setText("")
        }

        imBtnAdminFocBk.setOnClickListener(){
            val intent= Intent(this, AdminFacultyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
