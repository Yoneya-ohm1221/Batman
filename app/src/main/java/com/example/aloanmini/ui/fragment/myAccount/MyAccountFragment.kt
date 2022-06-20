package com.example.aloanmini.ui.fragment.myAccount

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aloanmini.databinding.FragmentMyAccountBinding
import com.example.aloanmini.ui.activity.LoginActivity
import com.example.aloanmini.ui.models.User
import com.example.aloanmini.ui.utill.Resource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth


class MyAccountFragment : Fragment() {
    lateinit var binding: FragmentMyAccountBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)

        binding.btnlogout.setOnClickListener {
            mAuth.signOut()
            googleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

        userViewModel.user.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading ->{

                }
                is Resource.Success ->{
                    updateUi(it.data!!)
                }
            }
        }
    }

    private fun updateUi(user: User){
        binding.txtuuid.text = user.name

    }

}