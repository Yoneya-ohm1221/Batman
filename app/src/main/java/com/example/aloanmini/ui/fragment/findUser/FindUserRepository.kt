package com.example.aloanmini.ui.fragment.findUser

import androidx.lifecycle.MutableLiveData
import com.example.aloanmini.ui.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FindUserRepository {

     fun getData(liveData: MutableLiveData<List<Users>>) {
         var user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val refFeatured1 = FirebaseDatabase.getInstance().getReference("user")
        refFeatured1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val users = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Users::class.java)!!
                }
                liveData.postValue(users.filter { it.uid != user.uid  })
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}