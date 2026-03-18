package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionThreeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionThreeActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionThreeBinding
    lateinit var viewModel: ViewModel
    private var team1: AnswerQ3? = null
    private var team2: AnswerQ3? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val greenBg = R.drawable.bg_tv_green
        val redBg = R.drawable.bg_tv_red

        binding.btnClose.setOnClickListener {
            finish()
        }

        viewModel.answersQ3.observe(this) { answers ->
            checkAnswered(answers)

            team1 = answers.find { it.name == "TEAM 1" }
            team2 = answers.find { it.name == "TEAM 2" }
        }

        binding.btnShow.setOnClickListener {
            lifecycleScope.launch {
                binding.tvNumber.visibility = View.VISIBLE

                binding.tvNumber.text = "3"
                delay(1000)

                binding.tvNumber.text = "2"
                delay(1000)

                binding.tvNumber.text = "1"
                delay(1000)

                binding.tvNumber.visibility = View.INVISIBLE

                binding.tvQuestion.visibility = View.INVISIBLE
                binding.tvAddInfo.visibility = View.INVISIBLE

                binding.minesContainer.visibility = View.VISIBLE
                updateAnswers()

                delay(2000)
                binding.mine1.text = getString(R.string.arsenal)
                delay(1000)
                binding.mine2.text = getString(R.string.london_bridge)
                delay(1000)
                binding.mine3.text = getString(R.string.queens_park)
                delay(1000)
                binding.mine4.text = getString(R.string.west_ham)
                delay(1000)
                binding.mine5.text = getString(R.string.wimbledon)
            }
        }


        binding.answer1Team1.setOnClickListener {
            binding.answer1Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }

        binding.answer2Team1.setOnClickListener {
            binding.answer2Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }

        binding.answer3Team1.setOnClickListener {
            binding.answer3Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }

        binding.answer4Team1.setOnClickListener {
            binding.answer4Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }

        binding.answer5Team1.setOnClickListener {
            binding.answer5Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }

        binding.answer1Team1.setOnLongClickListener {
            binding.answer1Team1.setBackgroundResource(redBg); true
        }

        binding.answer2Team1.setOnLongClickListener {
            binding.answer2Team1.setBackgroundResource(redBg); true
        }

        binding.answer3Team1.setOnLongClickListener {
            binding.answer3Team1.setBackgroundResource(redBg); true
        }

        binding.answer4Team1.setOnLongClickListener {
            binding.answer4Team1.setBackgroundResource(redBg); true
        }

        binding.answer5Team1.setOnLongClickListener {
            binding.answer5Team1.setBackgroundResource(redBg); true
        }


        binding.answer1Team2.setOnClickListener {
            binding.answer1Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }

        binding.answer2Team2.setOnClickListener {
            binding.answer2Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }

        binding.answer3Team2.setOnClickListener {
            binding.answer3Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }

        binding.answer4Team2.setOnClickListener {
            binding.answer4Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }

        binding.answer5Team2.setOnClickListener {
            binding.answer5Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }

        binding.answer1Team2.setOnLongClickListener {
            binding.answer1Team2.setBackgroundResource(redBg); true
        }
        binding.answer2Team2.setOnLongClickListener {
            binding.answer2Team2.setBackgroundResource(redBg); true
        }
        binding.answer3Team2.setOnLongClickListener {
            binding.answer3Team2.setBackgroundResource(redBg); true
        }
        binding.answer4Team2.setOnLongClickListener {
            binding.answer4Team2.setBackgroundResource(redBg); true
        }
        binding.answer5Team2.setOnLongClickListener {
            binding.answer5Team2.setBackgroundResource(redBg); true
        }

    }


    private fun checkAnswered(answers: List<AnswerQ3>) {

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

        binding.answer1Team1.text = team1?.answer1
        binding.answer2Team1.text = team1?.answer2
        binding.answer3Team1.text = team1?.answer3
        binding.answer4Team1.text = team1?.answer4
        binding.answer5Team1.text = team1?.answer5

        binding.answer1Team2.text = team2?.answer1
        binding.answer2Team2.text = team2?.answer2
        binding.answer3Team2.text = team2?.answer3
        binding.answer4Team2.text = team2?.answer4
        binding.answer5Team2.text = team2?.answer5
    }

}