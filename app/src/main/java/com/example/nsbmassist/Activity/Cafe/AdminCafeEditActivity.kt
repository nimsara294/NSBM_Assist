package com.example.nsbmassist.Activity.Cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.nsbmassist.Activity.AdminMainActivity
import com.example.nsbmassist.Model.CafeModel
import com.example.nsbmassist.Model.TransModel
import com.example.nsbmassist.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminCafeEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cafe_edit)

        var code: EditText =findViewById(R.id.adminCafeCode)
        val name: EditText =findViewById(R.id.adminCafeName)
        val openAt: EditText =findViewById(R.id.adminCafeOpen)
        val closeAt: EditText =findViewById(R.id.adminCafeClose)

        val imBtnAdminCafeAdd: ImageButton =findViewById(R.id.imBtnAdminCafeAdd)
        val imBtnAdminCafeUpdate: ImageButton =findViewById(R.id.imBtnAdminCafeUpdate)
        val imBtnAdminCafeDelete: ImageButton =findViewById(R.id.imBtnAdminCafeDelete)
        val imBtnAdminCafeRst: ImageButton =findViewById(R.id.imBtnAdminCafeReset)
        val imBtnAdminCafeBk: ImageButton =findViewById(R.id.imBtnAdminCafeBk)

        var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Cafe")

        fun saveCafeData(){

            var cafeCode=code.text.toString()
            val cafeName=name.text.toString()
            val cafeOpenAt=openAt.text.toString()
            val cafeCloseAt=closeAt.text.toString()

            if (cafeCode.isEmpty()){
                code.error="Please Enter Code"
            }
            if (cafeName.isEmpty()){
                name.error="Please Enter Cafe Name"
            }
            if (cafeOpenAt.isEmpty()){
                openAt.error="Please Enter Opening Time"
            }
            if (cafeCloseAt.isEmpty()){
                closeAt.error="Please Enter Closing Time"
            }

            val cafeData= CafeModel(cafeName,cafeOpenAt,cafeCloseAt)

            dbRef.child(cafeCode).setValue(cafeData)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    name.setText("")
                    openAt.setText("")
                    closeAt.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun updateCafeData(){

            var cafeCode=code.text.toString()
            val cafeName=name.text.toString()
            val cafeOpenAt=openAt.text.toString()
            val cafeCloseAt=closeAt.text.toString()


            val cafeDataUpdate= CafeModel(cafeName,cafeOpenAt,cafeCloseAt)

            dbRef.child(cafeCode).setValue(cafeDataUpdate)
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Updated Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")
                    name.setText("")
                    openAt.setText("")
                    closeAt.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

        fun deleteCafeData(){
            var cafeCode=code.text.toString()

            dbRef.child(cafeCode).removeValue()
                .addOnCompleteListener{
                    Toast.makeText(this,"Data Deleted Successfully", Toast.LENGTH_LONG).show()

                    code.setText("")

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }

        imBtnAdminCafeUpdate.setOnClickListener(){
            updateCafeData()
        }

        imBtnAdminCafeAdd.setOnClickListener(){
            saveCafeData()
        }

        imBtnAdminCafeDelete.setOnClickListener(){
            deleteCafeData()
        }

        imBtnAdminCafeRst.setOnClickListener(){
            code.setText("")
            name.setText("")
            openAt.setText("")
            closeAt.setText("")
        }

        imBtnAdminCafeBk.setOnClickListener(){
            val intent= Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}