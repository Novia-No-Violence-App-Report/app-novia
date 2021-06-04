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
        binding.homeCardBanner.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_chatbot)
        }
        binding.btnSaatnyabersuara.setOnClickListener {
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_chatbot)
        }
        showRecyclerViews()
    }

    private fun showRecyclerViews() {
        // Tips & Trik
        binding.rvTipsTrik.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val tipsTrikAdapter = TipsTrikAdapter(list)
        binding.rvTipsTrik.adapter = tipsTrikAdapter

        // Berita
        binding.homeBeritaRv.layoutManager = LinearLayoutManager(context)
        val newsAdapter = NewsAdapter()
        binding.homeBeritaRv.setHasFixedSize(true)
        binding.homeBeritaRv.adapter = newsAdapter
        newsAdapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                val intent = Intent(context, NewsActivity::class.java)
                intent.putExtra("ARTICLE", data)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context?.startActivity(intent)
            }
        })

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}