package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionFiveBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionFiveActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionFiveBinding
    lateinit var viewModel: ViewModel
    private var team1: AnswerQ5? = null
    private var team2: AnswerQ5? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionFiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        binding.btnClose.setOnClickListener {
            finish()
        }

        viewModel.answersQ5.observe(this) { answers ->
            checkAnswered(answers)

            team1 = answers.find { it.name == "TEAM 1" }
            team2 = answers.find { it.name == "TEAM 2" }
        }

        binding.btnShow.setOnClickListener {
            updateAnswers()
        }

        var correctCount = 0
        binding.correctAnswer.setOnClickListener {
            correctCount++

            binding.tvNumber.visibility = View.VISIBLE
            lifecycleScope.launch {
                binding.tvNumber.text = "3"
                delay(1000)

                binding.tvNumber.text = "2"
                delay(1000)

                binding.tvNumber.text = "1"
                delay(1000)

                binding.tvNumber.visibility = View.INVISIBLE

                compareAnswer(correctCount)
                showCorrectAnswer(correctCount)
            }

        }

        var qCount = 1
        binding.btnReset.setOnClickListener {
            qCount++
            resetAnswers()
            loadNewQuestion(qCount)
        }

    }


    private fun compareAnswer(count: Int) {
        val green = R.drawable.bg_tv_green
        val red = R.drawable.bg_tv_red

        val team1High: Int = team1?.high!!
        val team1Low: Int = team1?.low!!
        val team1Span = team1High.minus(team1Low)

        val team2High: Int = team2?.high!!
        val team2Low: Int = team2?.low!!
        val team2Span = team2High.minus(team2Low)

        var correctAnswer = 0

        when (count) {
            1 -> correctAnswer = 1440
            2 -> correctAnswer = 2000000
            3 -> correctAnswer = 24
        }


        if ((correctAnswer in team1Low..team1High) &&
            (correctAnswer in team2Low..team2High)
        ) {

            if (team1Span < team2Span) {
                binding.answer1Team1.setBackgroundResource(green)
                binding.answer2Team1.setBackgroundResource(green)
                binding.answer3Team1.setBackgroundResource(green)


                binding.answer1Team2.setBackgroundResource(red)
                binding.answer2Team2.setBackgroundResource(red)
                binding.answer3Team2.setBackgroundResource(red)

                viewModel.addPoint(viewModel.team1Email)

            } else if (team2Span < team1Span) {
                binding.answer1Team2.setBackgroundResource(green)
                binding.answer2Team2.setBackgroundResource(green)
                binding.answer3Team2.setBackgroundResource(green)

                binding.answer1Team1.setBackgroundResource(red)
                binding.answer2Team1.setBackgroundResource(red)
                binding.answer3Team1.setBackgroundResource(red)

                viewModel.addPoint(viewModel.team2Email)

            }

        } else if (correctAnswer in team1Low..team1High) {
            binding.answer1Team1.setBackgroundResource(green)
            binding.answer2Team1.setBackgroundResource(green)
            binding.answer3Team1.setBackgroundResource(green)

            binding.answer1Team2.setBackgroundResource(red)
            binding.answer2Team2.setBackgroundResource(red)
            binding.answer3Team2.setBackgroundResource(red)

            viewModel.addPoint(viewModel.team1Email)


        } else if (correctAnswer in team2Low..team2High) {
            binding.answer1Team2.setBackgroundResource(green)
            binding.answer2Team2.setBackgroundResource(green)
            binding.answer3Team2.setBackgroundResource(green)

            binding.answer1Team1.setBackgroundResource(red)
            binding.answer2Team1.setBackgroundResource(red)
            binding.answer3Team1.setBackgroundResource(red)

            viewModel.addPoint(viewModel.team2Email)

        } else {
            binding.answer1Team2.setBackgroundResource(red)
            binding.answer2Team2.setBackgroundResource(red)
            binding.answer3Team2.setBackgroundResource(red)

            binding.answer1Team1.setBackgroundResource(red)
            binding.answer2Team1.setBackgroundResource(red)
            binding.answer3Team1.setBackgroundResource(red)
        }
    }

    private fun resetAnswers() {
        val orange = R.drawable.bg_tv_orange

        binding.answer1Team1.text = getString(R.string.low)
        binding.answer2Team1.text = getString(R.string.high)
        binding.answer3Team1.text = getString(R.string.span)

        binding.answer1Team2.text = getString(R.string.low)
        binding.answer2Team2.text = getString(R.string.high)
        binding.answer3Team2.text = getString(R.string.span)

        binding.correctAnswer.text = getString(R.string.correct_answer)

        binding.answer1Team1.setBackgroundResource(orange)
        binding.answer2Team1.setBackgroundResource(orange)
        binding.answer3Team1.setBackgroundResource(orange)

        binding.answer1Team2.setBackgroundResource(orange)
        binding.answer2Team2.setBackgroundResource(orange)
        binding.answer3Team2.setBackgroundResource(orange)
    }

    private fun loadNewQuestion(count: Int) {
        val question1 = getString(R.string.question_5_1)
        val question1add = getString(R.string.add_5_1)

        val question2 = getString(R.string.question_5_2)
        val question2add = getString(R.string.add_5_2)

        val question3 = getString(R.string.question_5_3)

        when (count) {
            1 -> {
                binding.tvQuestion.text = question1
                binding.tvAddInfo.text = question1add
            }

            2 -> {
                binding.tvQuestion.text = question2
                binding.tvAddInfo.text = question2add
            }

            3 -> {
                binding.tvQuestion.text = question3
                binding.tvAddInfo.text = ""
            }
        }
    }

    private fun showCorrectAnswer(count: Int) {

        when (count) {
            1 -> binding.correctAnswer.text = getString(R.string._1440_km)
            2 -> binding.correctAnswer.text = getString(R.string._2000000)
            3 -> binding.correctAnswer.text = getString(R.string._24_seasons)
        }
    }

    private fun checkAnswered(answers: List<AnswerQ5>) {

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
            team2?.answered == true
        ) {

            binding.btnShow.visibility = View.VISIBLE

        }
    }

    private fun updateAnswers() {
        val team1High: Int = team1?.high!!
        val team1Low: Int = team1?.low!!
        val team1Span = team1High.minus(team1Low)

        val team2High: Int = team2?.high!!
        val team2Low: Int = team2?.low!!
        val team2Span = team2High.minus(team2Low)


        binding.answer1Team1.text = team1Low.toString()
        binding.answer2Team1.text = team1High.toString()
        binding.answer3Team1.text = team1Span.toString()

        binding.answer1Team2.text = team2Low.toString()
        binding.answer2Team2.text = team2High.toString()
        binding.answer3Team2.text = team2Span.toString()
    }

}