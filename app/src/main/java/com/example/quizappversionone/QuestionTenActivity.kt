package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionTenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionTenActivity : AppCompatActivity() {

    lateinit var binding : ActivityQuestionTenBinding
    lateinit var viewModel: ViewModel
    private  var team1 : AnswerQ10? = null
    private var team2 : AnswerQ10? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionTenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val greenBg = R.drawable.bg_tv_green
        val redBg = R.drawable.bg_tv_red

        binding.btnClose.setOnClickListener {
            finish()
        }

        viewModel.answersQ10.observe(this) { answers ->
            checkAnswered(answers)

            team1 = answers.find { it.name == "TEAM 1" }
            team2 = answers.find { it.name == "TEAM 2" }
        }


        binding.btnShow.setOnClickListener {
            binding.tvNumber.visibility = View.VISIBLE

            lifecycleScope.launch {
                binding.tvNumber.text = "3"
                delay(1000)

                binding.tvNumber.text = "2"
                delay(1000)

                binding.tvNumber.text = "1"
                delay(1000)

                binding.tvNumber.visibility = View.INVISIBLE
                updateAnswers()

                binding.btnShowOuts.visibility = View.VISIBLE
            }
        }

        var clickCount = 0
        binding.btnShowOuts.setOnClickListener {
                clickCount++

                if (clickCount % 2 == 1) {

                    binding.tvOuts.visibility = View.VISIBLE

                } else if (clickCount % 2 == 0) {

                    binding.tvOuts.visibility = View.INVISIBLE
                }
        }


        binding.answer1Team1.setOnClickListener {
            binding.answer1Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }

        binding.answer1Team1.setOnLongClickListener {
            binding.answer1Team1.setBackgroundResource(redBg); true }

        binding.answer1Team2.setOnClickListener {
            binding.answer1Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }

        binding.answer1Team2.setOnLongClickListener {
            binding.answer1Team2.setBackgroundResource(redBg); true }

    }


    private fun checkAnswered(answers: List<AnswerQ10>) {

        val team1 = answers.find { it.name == "TEAM 1" }
        val team2 = answers.find { it.name == "TEAM 2" }

        val green = R.drawable.bg_tv_green
        val red = R.drawable.bg_tv_red


        if (team1?.answered == true) {
            binding.team1Check.setBackgroundResource(green)

        } else {
            binding.team1Check.setBackgroundResource(red)
        }

        if (team2?.answered == true) {
            binding.team2Check.setBackgroundResource(green)

        } else {
            binding.team2Check.setBackgroundResource(red)
        }

        // Show "show-button" when both teams are ready
        if (team1?.answered == true &&
            team2?.answered == true) {

            binding.btnShow.visibility = View.VISIBLE

        }
    }

    private fun updateAnswers() {

        binding.answer1Team1.text = team1?.answer1

        binding.answer1Team2.text = team2?.answer1
    }
}