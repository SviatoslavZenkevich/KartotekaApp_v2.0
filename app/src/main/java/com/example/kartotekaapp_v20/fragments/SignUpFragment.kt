package com.example.kartotekaapp_v20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.kartotekaapp_v20.R
import com.example.kartotekaapp_v20.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init(view)

//        binding.textViewSignIn.setOnClickListener {
//            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
//        }

        binding.regBtn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            val verifyPass = binding.etVerifyPass.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty() && verifyPass.isNotEmpty()) {
                if (pass == verifyPass) {

                    registerUser(email, pass)

                } else {
                    Toast.makeText(context, "Password is not same", Toast.LENGTH_SHORT).show()
                }
            } else
                Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun registerUser(email: String, pass: String) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Registered ", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_signUpFragment_to_signInFragment)
            } else {
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        mAuth = FirebaseAuth.getInstance()
    }
}