package com.example.nsbmassist.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.nsbmassist.R
import com.google.firebase.auth.FirebaseAuth

class LogAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_admin)

        val btnBkLogAdmin:Button=findViewById(R.id.btnBkLogAdmin)
        val btnLogin:Button=findViewById(R.id.btnLogAdmin)
        val btnReset:Button=findViewById(R.id.btnRstAdmin)
        val auth:FirebaseAuth= FirebaseAuth.getInstance()
        val email:EditText=findViewById(R.id.logAdminMail)
        val pw:EditText=findViewById(R.id.logAdminPw)

        fun loginUser(email:String,password:String){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task->
                if(task.isSuccessful){
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this, AdminMainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
            }

        }
        btnBkLogAdmin.setOnClickListener(){
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener(){
            var txt_email=email.text.toString()
            var txt_password=pw.text.toString()
            if(txt_email==("")||txt_password==("")){
                Toast.makeText(applicationContext, "Please fill in the above fields.", Toast.LENGTH_SHORT).show()
            }else{
                loginUser(txt_email,txt_password)
            }
        }

        btnReset.setOnClickListener(){
            email.setText("")
            pw.setText("")
        }

    }
}