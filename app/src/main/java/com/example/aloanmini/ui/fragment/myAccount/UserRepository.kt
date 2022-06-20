package com.example.aloanmini.ui.fragment.myAccount

import com.example.aloanmini.ui.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {

    suspend fun getUser(): User {
        var user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        return User(
            id = user.uid,
            name = user.displayName.toString(),
            email = user.email.toString(),
            image = user.photoUrl.toString()
        )
    }
}