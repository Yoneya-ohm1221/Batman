package com.example.aloanmini.ui.fragment.roomChat

import androidx.lifecycle.MutableLiveData
import com.example.aloanmini.ui.models.Chats
import com.example.aloanmini.ui.utill.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RoomChatRepository {

    fun getData(liveData: MutableLiveData<List<Chats>>, toUid: String) {
        var user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val refFeatured1 = FirebaseDatabase.getInstance().getReference("chats")

        refFeatured1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val data = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Chats::class.java)!!
                }
                val chats = data.filter {
                    (it.fromUid == user.uid && it.toUid == toUid) ||
                            (it.toUid == user.uid && it.fromUid == toUid)
                }
                liveData.postValue(chats)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun addChat(toUid: String, chat: String) {
        var user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val database = FirebaseDatabase.getInstance()
        val chats = Chats(
            fromUid = user.uid,
            toUid = toUid,
            message = chat,
            dateTime = Utils.dateTimeNow()
        )
        database.reference.child("chats").push().setValue(chats)

    }
}