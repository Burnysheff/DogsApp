package com.example.dogsapp.ui.person

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.dogsapp.CourseActivity
import com.example.dogsapp.MainActivity
import com.example.dogsapp.R
import com.example.dogsapp.databinding.FragmentPersonBinding
import com.example.dogsapp.ui.registration.RegistrationFragment

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var list: HorizontalScrollView
    private lateinit var text: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        list = binding.scroll
        text = binding.texter

        val linkTextView = text
        linkTextView.movementMethod = LinkMovementMethod.getInstance()
        linkTextView.setOnClickListener {
            course()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun course() {
        val main = Intent(super.getContext(), CourseActivity::class.java)
        startActivity(main)
    }
}