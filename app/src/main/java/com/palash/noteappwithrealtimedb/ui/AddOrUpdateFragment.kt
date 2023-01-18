package com.palash.noteappwithrealtimedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.palash.noteappwithrealtimedb.R
import com.palash.noteappwithrealtimedb.databinding.FragmentAddOrUpdateBinding
import com.palash.noteappwithrealtimedb.databinding.FragmentAllNoteBinding
import com.palash.noteappwithrealtimedb.datamodel.User

class AddOrUpdateFragment : Fragment() {
    private var _binding: FragmentAddOrUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private var userId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddOrUpdateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = Firebase.database.reference
        binding.btnSave.setOnClickListener {
            userId++
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val user = User(name, email)
            database.child("users").child(userId.toString()).setValue(user)
            findNavController().navigate(R.id.action_addOrUpdateFragment_to_allNoteFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}