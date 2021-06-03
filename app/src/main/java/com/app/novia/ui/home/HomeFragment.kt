package com.app.novia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.novia.databinding.FragmentHomeBinding
import com.app.novia.databinding.ItemTipsTrikBinding
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private var user: FirebaseUser? = null

    private var list: ArrayList<TipsTrikModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.auth.currentUser.let {
            user = it
        }

        binding.rvTipsTrik.setHasFixedSize(true)

        list.addAll(TipsTrikList.listData)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeGreetingsName.text = StringBuilder("Hai, " + user?.displayName)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showRecyclerView()
    }

    private fun showRecyclerView() {
        binding.rvTipsTrik.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val tipsTrikAdapter = TipsTrikAdapter(list)
        binding.rvTipsTrik.adapter = tipsTrikAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}