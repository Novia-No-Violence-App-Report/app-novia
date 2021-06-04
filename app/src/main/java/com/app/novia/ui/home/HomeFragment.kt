package com.app.novia.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.novia.NewsActivity
import com.app.novia.R
import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.domain.model.Article
import com.app.novia.core.ui.NewsAdapter
import com.app.novia.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private var user: FirebaseUser? = null
    private var list: ArrayList<TipsTrikModel> = arrayListOf()
    private val newsAdapter = NewsAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.auth.currentUser.let {
            user = it
        }
        addRecyclerViewsContent()
        return binding.root
    }

    private fun addRecyclerViewsContent() {
        list.addAll(TipsTrikList.listData)

        lifecycleScope.launch {
            homeViewModel.getNews().observe(viewLifecycleOwner, { response ->
                if (response != null) {
                    when (response) {
                        is ApiResponse.Success -> {
                            Log.d("API RESPONSE", "SUCCESS GETTING NEWS DATA")
                            newsAdapter.setData(response.data.articles)
                        }
                        is ApiResponse.Empty -> Log.d("API RESPONSE", "EMPTY")
                        is ApiResponse.Error -> Log.d(
                            "API RESPONSE",
                            "ERROR ${response.errorMessage}"
                        )
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeGreetingsName.text = StringBuilder("Hai, " + user?.displayName)
        binding.homeCardBanner.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_chatbot)
        }
        binding.btnSaatnyabersuara.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_chatbot)
        }
        showRecyclerViews()
    }

    private fun showRecyclerViews() {
        with(binding) {
            // Tips & Trik
            rvTipsTrik.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            val tipsTrikAdapter = TipsTrikAdapter(list)
            rvTipsTrik.adapter = tipsTrikAdapter

            // Berita
            homeBeritaRv.layoutManager = LinearLayoutManager(context)
            homeBeritaRv.setHasFixedSize(true)
            homeBeritaRv.adapter = newsAdapter
            newsAdapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Article) {
                    val intent = Intent(context, NewsActivity::class.java)
                    intent.putExtra("ARTICLE", data)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context?.startActivity(intent)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}