package com.example.dogsapp.ui.autor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dogsapp.databinding.FragmentAuthorBinding
import com.example.dogsapp.databinding.FragmentPlanBinding

class AuthorFragment: Fragment() {
    private var _binding: FragmentAuthorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorBinding.inflate(inflater, container, false)

        return binding.root
    }
}