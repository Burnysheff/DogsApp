package com.example.dogsapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.dogsapp.LoginActivity
import com.example.dogsapp.MainActivity
import com.example.dogsapp.R
import com.example.dogsapp.databinding.FragmentLoginBinding
import com.example.dogsapp.sender.Sender
import com.example.dogsapp.ui.registration.RegistrationFragment


class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var reg: TextView

    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        email = binding.email
        password = binding.password
        reg = binding.reg
        image = binding.imageView

        val linkTextView = reg
        linkTextView.movementMethod = LinkMovementMethod.getInstance()
        linkTextView.setOnClickListener {
            getReg()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun entry() {
        Thread {
            val checker = Sender.log(email.text, password.text)
            if (checker) {
                val main = Intent(super.getContext(), MainActivity::class.java)
                startActivity(main)
            }
        }.start()
    }

    private fun getReg() {
        val fragment = RegistrationFragment()
        val manager = requireActivity().supportFragmentManager
        manager.beginTransaction().replace(R.id.container, fragment).commit()

        val button: Button? = activity?.findViewById<Button>(R.id.button)
        button?.setOnClickListener { fragment.entry() }
    }
}