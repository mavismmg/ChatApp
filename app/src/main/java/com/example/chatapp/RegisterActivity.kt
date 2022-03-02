package com.example.chatapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        FirebaseApp.initializeApp(applicationContext)

        button_registerActivity_btt.setOnClickListener {
            val email = email_registerActivity_txt.text.toString()
            val password = password_registerActivity_txt.text.toString()

            Log.d("RegisterActivity", "Email is: $email")
            Log.d("RegisterActivity", "Password: $password")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener  {
                    if (it.isSuccessful) {
                        Log.d("Register", "Succesfully created user with uid: ${it.result.user!!.uid}")
                    } else {
                        return@addOnCompleteListener
                    }
                }
        }

        already_have_account.setOnClickListener {
            Log.d("RegisterActivity", "Try to show login activity")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}