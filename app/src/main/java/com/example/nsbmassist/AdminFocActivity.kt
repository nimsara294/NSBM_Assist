package com.example.nsbmassist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminFocActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_foc)

        val id:EditText=findViewById(R.id.adminFocId)
        val lect:EditText=findViewById(R.id.adminFocLecture)
        val lecturer:EditText=findViewById(R.id.adminFocLecturerName)
        val time:EditText=findViewById(R.id.adminFocTime)
        val date:EditText=findViewById(R.id.adminFocDate)
        val batch:EditText=findViewById(R.id.adminFocBatchNum)
        val hall:EditText=findViewById(R.id.adminFocHallNum)

        val imBtnAdminFocAdd:ImageButton=findViewById(R.id.imBtnAdminFocAdd)
        val imBtnAdminFocRst:ImageButton=findViewById(R.id.imBtnAdminFocReset)
        val imBtnAdminFocBk: ImageButton =findViewById(R.id.imBtnAdminFocBk)

        var dbRef:DatabaseReference=FirebaseDatabase.getInstance().getReference("FocLecture")

        fun saveFocLectureData(){

            var lectId=id.text.toString()
            val lectName=lect.text.toString()
            val lecturerName=lecturer.text.toString()
            val lecttime=time.text.toString()
            val lectdate=date.text.toString()
            val batchnum=batch.text.toString()
            val hallnum=hall.text.toString()

            if (lectId.isEmpty()){
                id.error="Please Enter ID"
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

            lectId=dbRef.push().key!!

            val lecture=FocModel(lectId,lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

            dbRef.child(lectId).setValue(lecture)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data inserted Successfully", Toast.LENGTH_LONG).show()

                    id.setText("")
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

        imBtnAdminFocAdd.setOnClickListener(){
            saveFocLectureData()
        }

        imBtnAdminFocRst.setOnClickListener(){
            id.setText("")
            lect.setText("")
            lecturer.setText("")
            time.setText("")
            date.setText("")
            batch.setText("")
            hall.setText("")
        }
        imBtnAdminFocBk.setOnClickListener(){
            val intent= Intent(this,AdminFacultyActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}