package com.zplay.willheror.justcapy

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.zplay.willheror.R
import com.zplay.willheror.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private val listRandom = listOf("dog", "cat", "mouse")

    private val userChoiceText = listRandom.random()

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentEnterBinding = null")

    var display: String = ""

    private val listImage = listOf(
        R.drawable.king,
        R.drawable.scepter,
        R.drawable.sword,
    )

    private var imgResId = listImage.random()



    private var compImg = listImage.random()
    val compChoice = generateChoice()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            setUserChoice()
            setCompChoice()
            setTextResult()
            binding.btnImgExit.setOnClickListener {
                initAlertDialog()
            }

            binding.btnTryAgain.setOnClickListener {
                requireActivity().onBackPressed()
            }


        } catch (e: Exception){
            snackBarError()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun setTextResult() {
        //When true. This replaces nested if and else if statements.
        display = when {
            (userChoiceText == "cat" && compChoice == "dog") -> "You Lose!"
            (userChoiceText == "dog" && compChoice == "mouse") -> "You Lose!"
            (userChoiceText == "mouse" && compChoice == "cat") -> "You Lose!"
            (userChoiceText == compChoice) -> "Draw!"
            else -> "You Win!"
        }
        binding.tvResult.text = display
    }

    private fun setCompChoice() {
        when (compChoice) {
            "dog" -> compImg = R.drawable.king
            "cat" -> compImg = R.drawable.scepter
            "mouse" -> compImg = R.drawable.sword
            else -> R.drawable.scepter
        }
        binding.imageDovn.setImageResource(compImg)
    }

    private fun setUserChoice() {
        when (userChoiceText) {
            "dog" -> imgResId = R.drawable.king
            "cat" -> imgResId = R.drawable.scepter
            "mouse" -> imgResId = R.drawable.sword
            else -> R.drawable.king
        }
        binding.imgTop.setImageResource(imgResId)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
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

    fun generateChoice(): String {
        //Create list of [0,1,2]
        val list = (0..2)
        //Shuffle the list, grab a random number
        val choice = list.shuffled().take(1)[0]
        val rps = arrayOf("Dog", "Cat", "Mouse")
        //Take the index of the random number, and return the value
        return rps.elementAtOrElse(choice) { index -> "The value for index $index is undefined" }
    }


}