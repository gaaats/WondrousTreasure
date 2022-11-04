package com.zplay.willheror.justcapy

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zplay.willheror.R
import com.zplay.willheror.databinding.FragmentChooseBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ChooseFragment : Fragment() {

    var counterSvord = 0.1f
    var diffSvord = 0.07f

    var counterScipeter = 0.9f
    var diffScipeter = 0.09f

    var counterCrovn = 0.5f
    var diffCrovn = 0.05f

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

        try {
            cycleUpAndDovnAlphaSvord()
            cycleUpAndDovnAlphaScepeter()
            cycleUpAndDovnAlphaCrovn()

            binding.btnImgExit.setOnClickListener {
                initAlertDialog()
            }
            binding.imgCrovn.setOnClickListener {
                findNavController().navigate(R.id.action_chooseFragment_to_resultFragment)
            }

            binding.imgScepeter.setOnClickListener {
                findNavController().navigate(R.id.action_chooseFragment_to_resultFragment)
            }

            binding.imgSvord.setOnClickListener {
                findNavController().navigate(R.id.action_chooseFragment_to_resultFragment)
            }

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


    private fun cycleUpAndDovnAlphaSvord() {
        lifecycleScope.launch {
            while (true) {
                binding.imgSvord.alpha = counterSvord
                if (counterSvord >= 1f) {
                    diffSvord = -0.07f
                }
                if (counterSvord <= 0.1f) {
                    diffSvord = 0.07f
                }
                delay(60)
                counterSvord += diffSvord
            }
        }
    }

    private fun cycleUpAndDovnAlphaCrovn() {
        lifecycleScope.launch {
            while (true) {
                binding.imgCrovn.alpha = counterCrovn
                if (counterCrovn >= 1f) {
                    diffCrovn = -0.05f
                }
                if (counterCrovn <= 0.1f) {
                    diffCrovn = 0.05f
                }
                delay(60)
                counterCrovn += diffCrovn
            }
        }
    }

    private fun cycleUpAndDovnAlphaScepeter() {
        lifecycleScope.launch {
            while (true) {
                binding.imgScepeter.alpha = counterScipeter
                if (counterScipeter >= 1f) {
                    diffScipeter = -0.05f
                }
                if (counterScipeter <= 0.1f) {
                    diffScipeter = 0.05f
                }
                delay(60)
                counterScipeter += diffScipeter
            }
        }
    }



    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}