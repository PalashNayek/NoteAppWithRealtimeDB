package com.palash.noteappwithrealtimedb.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.palash.noteappwithrealtimedb.datamodel.User

class UserRepository {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    @Volatile private var INSTANCE : UserRepository?= null

    fun getInstance() : UserRepository{
        return INSTANCE ?: synchronized(this){

            val instance = UserRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadUsers(userList : MutableLiveData<List<User>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList : List<User> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(User::class.java)!!

                    }

                    userList.postValue(_userList)

                }catch (e : Exception){


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }

}