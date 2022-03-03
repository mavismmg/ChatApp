package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FirebaseApp.initializeApp(applicationContext)

        button_loginActivity_btt.setOnClickListener {
            userLogin()
        }

        dont_have_account_login.setOnClickListener {
            userHaveAnAccount()
        }
    }

    private fun userLogin() : Unit {
        val email = username_loginActivity_txt.text.toString()
        //val email = email_loginActivity_txt.text.toString()
        val password = password_loginActivity_txt.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please provide a e-mail and password", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Log.d("Login", "Successfully logged in: ${it.result.user!!.uid}")
                } else {


                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                Log.d("Login", "Failed to log in: ${it.message}")

                if (it.message == "There is no user record corresponding to this identifier. The user may have been deleted.") {
                    Toast.makeText(this, "E-mail or password is wrong", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Invalid e-mail", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun userHaveAnAccount() : Unit {
        Log.d("LoginActivity", "User already have an account")
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}