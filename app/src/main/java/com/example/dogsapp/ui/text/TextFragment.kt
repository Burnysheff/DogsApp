package com.example.dogsapp.ui.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dogsapp.databinding.FragmentPlanBinding
import com.example.dogsapp.databinding.FragmentTextBinding

class TextFragment:Fragment() {
    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTextBinding.inflate(inflater, container, false)

        return binding.root
    }
}