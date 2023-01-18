package com.palash.noteappwithrealtimedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.palash.noteappwithrealtimedb.Adapter.MyAdapter
import com.palash.noteappwithrealtimedb.R
import com.palash.noteappwithrealtimedb.databinding.FragmentAllNoteBinding
import com.palash.noteappwithrealtimedb.viewmodel.UserViewModel

class AllNoteFragment : Fragment() {
    private var _binding: FragmentAllNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : UserViewModel
    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllNoteBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userRecyclerView = view.findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        adapter = MyAdapter()
        userRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {

            adapter.updateUserList(it)

        })
        //floating action button click..............
        binding.floatingActionBtnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_allNoteFragment_to_addOrUpdateFragment)
        }
    }



    /*override fun onPause() {
        super.onPause()
        findNavController().popBackStack()
    }*/
    override fun onDetach() {
        super.onDetach()
        findNavController().popBackStack()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}