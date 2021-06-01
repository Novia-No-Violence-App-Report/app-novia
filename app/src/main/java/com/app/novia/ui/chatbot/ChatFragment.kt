package com.app.novia.ui.chatbot

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.novia.R
import com.app.novia.core.data.source.remote.ApiResponse
import com.app.novia.core.domain.model.ChatEntity
import com.app.novia.core.ui.ChatAdapter
import com.app.novia.databinding.FragmentChatbotBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ChatFragment : Fragment() {

    private val chatViewModel: ChatViewModel by viewModel()
    private var _binding: FragmentChatbotBinding? = null
    private val adapter by lazy { context?.let { ChatAdapter(it) } }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatbotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            rvChatbot.adapter = adapter
            rvChatbot.itemAnimator = null
            rvChatbot.setHasFixedSize(true)
            rvChatbot.layoutManager = LinearLayoutManager(context)
            lifecycleScope.launch {
                delay(500L)
                adapter?.addData(
                    ChatEntity(
                        "Halo, perkenalkan saya Novia! Saya siap membantu Anda.",
                        true,
                        SimpleDateFormat("hh:mm", Locale.UK).format(Date())
                    )
                )
                delay(1000L)
                adapter?.addData(
                    ChatEntity(
                        "Silakan ketik keluhan Anda dengan cara membalas pesan ini.",
                        true,
                        SimpleDateFormat("hh:mm", Locale.UK).format(Date())
                    )
                )
            }
            inputChatbot.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    switchSendButtonColor(s)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
            btnSendChatbot.setOnClickListener {
                val msg = inputChatbot.text.toString()
                inputChatbot.text.clear()
                if (msg == "") {
                    Toast.makeText(
                        context, "Harap masukkan pesan terlebih dahulu.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    runBlocking {
                        sendChat(msg)
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun switchSendButtonColor(s: CharSequence?) {
        if(s?.length!! == 0)
            binding.btnSendChatbot.setColorFilter(ContextCompat.getColor(requireContext(), R.color.material_on_surface_disabled), android.graphics.PorterDuff.Mode.SRC_IN)
        else
            binding.btnSendChatbot.setColorFilter(ContextCompat.getColor(requireContext(), R.color.novia_red), android.graphics.PorterDuff.Mode.SRC_IN)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SimpleDateFormat")
    private suspend fun sendChat(msg: String?) {
        val currentDate = SimpleDateFormat("hh:mm", Locale.UK).format(Date())
        adapter?.addData(ChatEntity(msg, false, currentDate.toString()))
        adapter?.itemCount?.minus(1)?.let { binding.rvChatbot.scrollToPosition(it) }
        chatViewModel.sendChat(msg).observeOnce(viewLifecycleOwner, { response ->
            if (response != null) {
                when (response) {
                    is ApiResponse.Success -> {
                        Log.d("API RESPONSE", "SUCCESS SENDING CHAT")
                        response.data.senderIsBot = true
                        response.data.timeStamp =
                            SimpleDateFormat("hh:mm", Locale.UK).format(Date()).toString()
                        adapter?.addData(response.data)
                        adapter?.itemCount?.minus(1)?.let { binding.rvChatbot.scrollToPosition(it) }
                    }
                    is ApiResponse.Empty -> Log.d("API RESPONSE", "EMPTY")
                    is ApiResponse.Error -> Log.d("API RESPONSE", "ERROR ${response.errorMessage}")
                }
            }
        })
    }

    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }
}