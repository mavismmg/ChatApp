package com.example.chatapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FirebaseApp.initializeApp(applicationContext)

        button_loginActivity_btt.setOnClickListener {
            val email = username_loginActivity_txt.text.toString()
            //val email = email_loginActivity_txt.text.toString()
            val password = password_loginActivity_txt.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please provide a e-mail and password", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Log.d("Login", "Successfully logged in: ${it.result.user!!.uid}")
                    } else {
                        Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()

                        return@addOnCompleteListener
                    }
                }
                .addOnFailureListener {
                    Log.d("Login", "Failed to log in: ${it.message}")
                }
        }

        dont_have_account_login.setOnClickListener {
            Log.d("LoginActivity", "User already have an acount")

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}