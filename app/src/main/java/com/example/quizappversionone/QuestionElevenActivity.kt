package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionElevenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionElevenActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionElevenBinding
    lateinit var viewModel: ViewModel
    lateinit var authViewModel: AuthViewModel

    var o1Clicked = false
    var o2Clicked = false
    var o3Clicked = false
    var o4Clicked = false
    var o5Clicked = false
    var o6Clicked = false
    var o7Clicked = false
    var o8Clicked = false
    var o9Clicked = false
    var o10Clicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionElevenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val green = R.drawable.bg_tv_green
        val red = R.drawable.bg_tv_red

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        if (authViewModel.auth.currentUser?.email == "team1@quiz.se" || authViewModel.auth.currentUser?.email == "team2@quiz.se") {
            binding.tvAddInfo.visibility = View.VISIBLE

            binding.tvQuestion.visibility = View.INVISIBLE
            binding.exampleContainer.visibility = View.INVISIBLE
            binding.btnStartTimer.visibility = View.INVISIBLE
            binding.tvNumber.visibility = View.INVISIBLE
            binding.btnShow.visibility = View.INVISIBLE
            binding.team1.visibility = View.INVISIBLE
            binding.team2.visibility = View.INVISIBLE
        }

        binding.btnStartTimer.setOnClickListener {


            lifecycleScope.launch {

                for (i in 40 downTo 1) {
                    binding.tvNumber.text = i.toString()
                    delay(1000)
                }
                binding.tvNumber.text = "0"

                binding.endOfTime.visibility = View.VISIBLE
                delay(100)
                binding.endOfTime.visibility = View.INVISIBLE
                binding.main.setBackgroundResource(red)
            }
        }

        binding.btnClose.setOnClickListener {
            finish()
        }

        var qCount = 1

        binding.btnReset.setOnClickListener {
            qCount++
            loadNewQuestion(qCount)
        }

        binding.team1.setOnClickListener {
            binding.team1.setBackgroundResource(green)
            binding.team2.setBackgroundResource(red)

            viewModel.addTwoPoint(viewModel.team1Email)
        }

        binding.team2.setOnClickListener {
            binding.team2.setBackgroundResource(green)
            binding.team1.setBackgroundResource(red)

            viewModel.addTwoPoint(viewModel.team2Email)
        }

        binding.option1.setOnClickListener {
            o1Clicked = true

            when (qCount) {
                1 -> {
                    binding.option1.text = getString(R.string.margaret_thatcher)
                    binding.option1.setBackgroundResource(green)
                }

                2 -> {
                    binding.option1.text = getString(R.string.aston_martin)
                    binding.option1.setBackgroundResource(green)
                }
            }

        }

        binding.option2.setOnClickListener {
            o2Clicked = true

            when (qCount) {
                1 -> {
                    binding.option2.text = getString(R.string.john_major)
                    binding.option2.setBackgroundResource(green)
                }

                2 -> {
                    binding.option2.text = getString(R.string.bentley)
                    binding.option2.setBackgroundResource(green)
                }
            }

        }

        binding.option3.setOnClickListener {
            o3Clicked = true

            when (qCount) {
                1 -> {
                    binding.option3.text = getString(R.string.tony_blair)
                    binding.option3.setBackgroundResource(green)
                }

                2 -> {
                    binding.option3.text = getString(R.string.jaguar)
                    binding.option3.setBackgroundResource(green)
                }
            }

        }

        binding.option4.setOnClickListener {
            o4Clicked = true

            when (qCount) {
                1 -> {
                    binding.option4.text = getString(R.string.gordon_brown)
                    binding.option4.setBackgroundResource(green)
                }

                2 -> {
                    binding.option4.text = getString(R.string.land_rover)
                    binding.option4.setBackgroundResource(green)
                }
            }

        }

        binding.option5.setOnClickListener {
            o5Clicked = true

            when (qCount) {
                1 -> {
                    binding.option5.text = getString(R.string.david_cameron)
                    binding.option5.setBackgroundResource(green)
                }

                2 -> {
                    binding.option5.text = getString(R.string.lotus)
                    binding.option5.setBackgroundResource(green)
                }
            }

        }

        binding.option6.setOnClickListener {
            o6Clicked = true

            when (qCount) {
                1 -> {
                    binding.option6.text = getString(R.string.theresa_may)
                    binding.option6.setBackgroundResource(green)
                }

                2 -> {
                    binding.option6.text = getString(R.string.mclaren)
                    binding.option6.setBackgroundResource(green)
                }
            }

        }

        binding.option7.setOnClickListener {
            o7Clicked = true

            when (qCount) {
                1 -> {
                    binding.option7.text = getString(R.string.boris_johnson)
                    binding.option7.setBackgroundResource(green)
                }

                2 -> {
                    binding.option7.text = getString(R.string.mg)
                    binding.option7.setBackgroundResource(green)
                }
            }

        }

        binding.option8.setOnClickListener {
            o8Clicked = true

            when (qCount) {
                1 -> {
                    binding.option8.text = getString(R.string.liz_truss)
                    binding.option8.setBackgroundResource(green)
                }

                2 -> {
                    binding.option8.text = getString(R.string.mini)
                    binding.option8.setBackgroundResource(green)
                }
            }

        }

        binding.option9.setOnClickListener {
            o9Clicked = true

            when (qCount) {
                1 -> {
                    binding.option9.text = getString(R.string.rishi_sunak)
                    binding.option9.setBackgroundResource(green)
                }

                2 -> {
                    binding.option9.text = getString(R.string.rolls_royce)
                    binding.option9.setBackgroundResource(green)
                }
            }

        }

        binding.option10.setOnClickListener {
            o10Clicked = true

            when (qCount) {
                1 -> {
                    binding.option10.text = getString(R.string.keir_starmer)
                    binding.option10.setBackgroundResource(green)
                }

                2 -> {
                    binding.option10.text = getString(R.string.vauxhall)
                    binding.option10.setBackgroundResource(green)
                }
            }

        }

        binding.btnShow.setOnClickListener {
            binding.btnReset.visibility = View.VISIBLE

            when (qCount) {
                1 -> {
                    binding.option1.text = getString(R.string.margaret_thatcher)
                    binding.option2.text = getString(R.string.john_major)
                    binding.option3.text = getString(R.string.tony_blair)
                    binding.option4.text = getString(R.string.gordon_brown)
                    binding.option5.text = getString(R.string.david_cameron)
                    binding.option6.text = getString(R.string.theresa_may)
                    binding.option7.text = getString(R.string.boris_johnson)
                    binding.option8.text = getString(R.string.liz_truss)
                    binding.option9.text = getString(R.string.rishi_sunak)
                    binding.option10.text = getString(R.string.keir_starmer)

                }

                2 -> {
                    binding.option1.text = getString(R.string.aston_martin)
                    binding.option2.text = getString(R.string.bentley)
                    binding.option3.text = getString(R.string.jaguar)
                    binding.option4.text = getString(R.string.land_rover)
                    binding.option5.text = getString(R.string.lotus)
                    binding.option6.text = getString(R.string.mclaren)
                    binding.option7.text = getString(R.string.mg)
                    binding.option8.text = getString(R.string.mini)
                    binding.option9.text = getString(R.string.rolls_royce)
                    binding.option10.text = getString(R.string.vauxhall)

                }
            }

            if (!o1Clicked) {
                binding.option1.setBackgroundResource(red)
            }

            if (!o2Clicked) {
                binding.option2.setBackgroundResource(red)
            }

            if (!o3Clicked) {
                binding.option3.setBackgroundResource(red)
            }

            if (!o4Clicked) {
                binding.option4.setBackgroundResource(red)
            }

            if (!o5Clicked) {
                binding.option5.setBackgroundResource(red)
            }

            if (!o6Clicked) {
                binding.option6.setBackgroundResource(red)
            }

            if (!o7Clicked) {
                binding.option7.setBackgroundResource(red)
            }

            if (!o8Clicked) {
                binding.option8.setBackgroundResource(red)
            }

            if (!o9Clicked) {
                binding.option9.setBackgroundResource(red)
            }

            if (!o10Clicked) {
                binding.option10.setBackgroundResource(red)
            }

        }

    }

    private fun loadNewQuestion(count: Int) {
        val orange = R.drawable.bg_tv_orange

        val blue = R.drawable.bg_full_screen

        binding.option1.text = getString(R.string._1)
        binding.option2.text = getString(R.string._2)
        binding.option3.text = getString(R.string._3)
        binding.option4.text = getString(R.string._4)
        binding.option5.text = getString(R.string._5)
        binding.option6.text = getString(R.string._6)
        binding.option7.text = getString(R.string._7)
        binding.option8.text = getString(R.string._8)
        binding.option9.text = getString(R.string._9)
        binding.option10.text = getString(R.string._10)

        binding.tvNumber.text = getString(R.string._40)

        binding.option1.setBackgroundResource(orange)
        binding.option2.setBackgroundResource(orange)
        binding.option3.setBackgroundResource(orange)
        binding.option4.setBackgroundResource(orange)
        binding.option5.setBackgroundResource(orange)
        binding.option6.setBackgroundResource(orange)
        binding.option7.setBackgroundResource(orange)
        binding.option8.setBackgroundResource(orange)
        binding.option9.setBackgroundResource(orange)
        binding.option10.setBackgroundResource(orange)

        binding.team1.setBackgroundResource(orange)
        binding.team2.setBackgroundResource(orange)

        binding.main.setBackgroundResource(blue)

        o1Clicked = false
        o2Clicked = false
        o3Clicked = false
        o4Clicked = false
        o5Clicked = false
        o6Clicked = false
        o7Clicked = false
        o8Clicked = false
        o9Clicked = false
        o10Clicked = false

        when (count) {
            2 -> {
                binding.tvQuestion.text = getString(R.string.question_11_2)
            }

        }

    }
}