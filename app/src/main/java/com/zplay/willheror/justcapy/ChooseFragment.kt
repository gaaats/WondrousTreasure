package com.zplay.willheror.justcapy

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zplay.willheror.R
import com.zplay.willheror.databinding.FragmentChooseBinding


class ChooseFragment : Fragment() {
    private var _binding: FragmentChooseBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentChooseBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnImgExit.setOnClickListener {
            initAlertDialog()
        }
        binding.btnMouse.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_resultFragment)
        }

        binding.btnDog.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_resultFragment)
        }

        binding.btnCat.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_resultFragment)
        }

        try {




        } catch (e: Exception){
            snackBarError()
        }


        super.onViewCreated(view, savedInstanceState)
    }


    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you definitely want to log out, the current data will not be saved?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().finish()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }

    private fun sendMessage(text: String) {

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}