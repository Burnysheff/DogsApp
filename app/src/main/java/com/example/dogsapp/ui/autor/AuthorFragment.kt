package com.example.dogsapp.ui.autor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dogsapp.databinding.FragmentAuthorBinding

class AuthorFragment: Fragment() {
    private var _binding: FragmentAuthorBinding? = null
    private val binding get() = _binding!!

    private lateinit var author: TextView
    private lateinit var email: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorBinding.inflate(inflater, container, false)

        author = binding.textView10
        email = binding.textView11

        author.text = requireArguments().getString("username")
        email.text = requireArguments().getString("email")

        return binding.root
    }
}