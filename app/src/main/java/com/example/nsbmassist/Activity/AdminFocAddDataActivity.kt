package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.nsbmassist.Model.FocModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminFocAddDataActivity : AppCompatActivity() {
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

            val lectId=dbRef.push().key!!

            val lecture= FocModel(lectId,lectCode,lectName,lecturerName,lecttime,lectdate,batchnum,hallnum)

            dbRef.child(lectId).setValue(lecture)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data inserted Successfully", Toast.LENGTH_LONG).show()

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

        fun updateFocLecture(
          code:String,
          lect:String,
          lectName:String,
          time:String,
          date:String,
          batch:String,
          hall:String
        ){
            dbRef=FirebaseDatabase.getInstance().getReference("FocLecture").child(code)
            val focLect=FocModel(code,lect,lectName,time,date,batch,hall)
            dbRef.setValue(focLect)
        }

        fun openUpdateDialog(code: String){
            val updateDialog = AlertDialog.Builder(this)
            val inflater=layoutInflater
            val updateDialogView=inflater.inflate(R.layout.update_dialog,null)

            updateDialog.setView(updateDialogView)

            val etCode:EditText=updateDialogView.findViewById(R.id.etCode)
            val etLect:EditText=updateDialogView.findViewById(R.id.etLect)
            val etLectName:EditText=updateDialogView.findViewById(R.id.etLectName)
            val etTime:EditText=updateDialogView.findViewById(R.id.etTime)
            val etDate:EditText=updateDialogView.findViewById(R.id.etDate)
            val etBatch:EditText=updateDialogView.findViewById(R.id.etBatch)
            val etHall:EditText=updateDialogView.findViewById(R.id.etHall)
            val btnUpdateData:EditText=updateDialogView.findViewById(R.id.btnUpdateData)

            etCode.setText(code)

            val alertDialog=updateDialog.create()
            alertDialog.show()

            btnUpdateData.setOnClickListener{
                updateFocLecture(
                    code,
                    etLect.text.toString(),
                    etLectName.text.toString(),
                    etTime.text.toString(),
                    etDate.text.toString(),
                    etBatch.text.toString(),
                    etHall.text.toString()
                )
            }
            Toast.makeText(applicationContext,"Lecture Schedule Updated", Toast.LENGTH_LONG).show()

            alertDialog.dismiss()
        }



        imBtnAdminFocUpdate.setOnClickListener{
            openUpdateDialog(
                code.toString()
            )
        }

        imBtnAdminFocAdd.setOnClickListener(){
            saveFocLectureData()
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