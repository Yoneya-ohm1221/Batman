package com.example.aloanmini.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aloanmini.R
import com.example.aloanmini.databinding.ActivityLoginBinding
import com.example.aloanmini.ui.customView.Loading
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.example.aloanmini.ui.activity.MainActivity as MainActivity1

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var mAuth: FirebaseAuth
    lateinit var loading: Loading

    //google
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val REQ_CODE: Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance();
        val user = mAuth.currentUser
        if (user != null) {
            val intent = Intent(this, MainActivity1::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginWithEmailPassword(email: String, password: String) {
        loading.show()
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                addUserToDatabase(
                    uid = mAuth.currentUser!!.uid,
                    name = mAuth.currentUser!!.displayName!!,
                    email = mAuth.currentUser!!.email!!
                )
                loading.dismiss()
                val intent = Intent(this, MainActivity1::class.java)
                startActivity(intent)
                finish()
            } else {
                loading.dismiss()
                Toast.makeText(this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginWithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (account != null) {
                    updateUI(account)
                }
            } catch (e: ApiException) {
                loading.dismiss()
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        loading.show()
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                addUserToDatabase(
                    uid = mAuth.currentUser!!.uid,
                    name = mAuth.currentUser!!.displayName!!,
                    email = mAuth.currentUser!!.email!!
                )
                loading.dismiss()
                val intent = Intent(this, MainActivity1::class.java)
                startActivity(intent)
                finish()
            } else {
                loading.dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        loading = Loading(this)
        //google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.btnlogin.setOnClickListener {
            val email = binding.editemail.text
            val password = binding.editpas.text
            if (email.toString().isNotEmpty() && password.toString().isNotEmpty()) {
                loginWithEmailPassword(email = email.toString(), password = password.toString())
            } else {
                Toast.makeText(this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show()
            }
        }

        binding.googleLoginButton.setOnClickListener {
            loginWithGoogle()
        }

        binding.btnregislogin.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addUserToDatabase(uid: String, name: String, email: String) {
        val database = FirebaseDatabase.getInstance()
        var databaseReference = database.reference.child("user")
        databaseReference.child(uid).child("uid").setValue(uid)
        databaseReference.child(uid).child("name").setValue(name)
        databaseReference.child(uid).child("email").setValue(email)
    }

}