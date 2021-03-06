package com.example.aloanmini.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aloanmini.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance();

        binding.imgback.setOnClickListener {
            onBackPressed()
        }

        binding.btnregis.setOnClickListener {
            val email = binding.editemail.text
            val pass = binding.editpas.text
            val conPass = binding.editconpass.text
            val displayName = binding.txtdisplayname.text

            if (pass.toString() == conPass.toString()) {
                addUser(email = email.toString(), password = pass.toString(),displayName = displayName.toString())
            } else {
                Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun addUser(email: String, password: String, displayName: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName).build()
                    user!!.updateProfile(profileUpdates)
                    Toast.makeText(this, "register success", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }

            }
    }
}

