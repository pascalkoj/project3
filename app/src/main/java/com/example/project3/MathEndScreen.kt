package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MathEndScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class MathEndScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun GetScoreString() : String
    {
        val mathapp = MathApp.instance
        val correct = mathapp.numCorrect
        val total = mathapp.numQuestions
        return "$correct of $total"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_math_end_screen, container, false)

        val scoreText = view.findViewById<TextView>(R.id.textScore)
        scoreText.setText(GetScoreString())

        val startOverButton = view.findViewById<Button>(R.id.bStartOver)
        startOverButton.setOnClickListener {
            MathApp.instance.Reset()
            val fm = parentFragmentManager
            if (fm != null)
            {
                val startScreen = fm.findFragmentById(R.id.nav_host_fragment) as MathEndScreen
                val navController = startScreen.findNavController()
                navController.navigate(R.id.action_mathEndScreen_to_startScreen)
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MathEndScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MathEndScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}