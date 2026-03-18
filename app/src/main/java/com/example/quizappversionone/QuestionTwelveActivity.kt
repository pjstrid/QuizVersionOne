package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionTwelveBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionTwelveActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionTwelveBinding
    lateinit var viewModel: ViewModel
    private var team1: AnswerQ12? = null
    private var team2: AnswerQ12? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionTwelveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val greenBg = R.drawable.bg_tv_green
        val redBg = R.drawable.bg_tv_red

        binding.btnClose.setOnClickListener {
            finish()
        }


        viewModel.answersQ12.observe(this) { answers ->
            checkAnswered(answers)

            team1 = answers.find { it.name == "TEAM 1" }
            team2 = answers.find { it.name == "TEAM 2" }
        }

        var showCount = 0
        binding.btnShow.setOnClickListener {
            binding.btnShow.visibility = View.INVISIBLE
            showCount++

            lifecycleScope.launch {
                binding.tvNumber.visibility = View.VISIBLE

                binding.tvNumber.text = "3"
                delay(1000)

                binding.tvNumber.text = "2"
                delay(1000)

                binding.tvNumber.text = "1"
                delay(1000)

                binding.tvNumber.visibility = View.INVISIBLE
                updateAnswers(showCount)

                if (showCount < 4) {
                    binding.btnNext.visibility = View.VISIBLE
                }
            }
        }


        binding.answer1Team1.setOnClickListener {
            binding.answer1Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }
        binding.answer1Team1.setOnLongClickListener {
            binding.answer1Team1.setBackgroundResource(redBg); true
        }
        binding.answer2Team1.setOnClickListener {
            binding.answer2Team1.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team1Email)
        }
        binding.answer2Team1.setOnLongClickListener {
            binding.answer2Team1.setBackgroundResource(redBg); true
        }



        binding.answer1Team2.setOnClickListener {
            binding.answer1Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }
        binding.answer1Team2.setOnLongClickListener {
            binding.answer1Team2.setBackgroundResource(redBg); true
        }
        binding.answer2Team2.setOnClickListener {
            binding.answer2Team2.setBackgroundResource(greenBg)
            viewModel.addPoint(viewModel.team2Email)
        }
        binding.answer2Team2.setOnLongClickListener {
            binding.answer2Team2.setBackgroundResource(redBg); true
        }


        binding.flightTicket.tvAirportFrom.text = getString(R.string.belfast_airport)
        binding.flightTicket.tvAirportTo.text = getString(R.string.bangkok_airport)

        var clickCount = 0
        binding.btnNext.setOnClickListener {
            clickCount++

            resetTicket(clickCount)
        }

    }

    private fun resetTicket(clickCount: Int) {
        binding.btnNext.visibility = View.INVISIBLE
        binding.btnShow.visibility = View.VISIBLE

        binding.answer1Team1.setBackgroundResource(R.drawable.bg_tv_orange)
        binding.answer2Team1.setBackgroundResource(R.drawable.bg_tv_orange)
        binding.answer1Team2.setBackgroundResource(R.drawable.bg_tv_orange)
        binding.answer2Team2.setBackgroundResource(R.drawable.bg_tv_orange)

        binding.answer1Team1.text = getString(R.string.hint_answer_1)
        binding.answer2Team1.text = getString(R.string.hint_answer_2)
        binding.answer1Team2.text = getString(R.string.hint_answer_1)
        binding.answer2Team2.text = getString(R.string.hint_answer_2)

        binding.flightTicket.tvAnswerFrom.setTextColor(getColor(R.color.light_gray))
        binding.flightTicket.tvAnswerTo.setTextColor(getColor(R.color.light_gray))
        binding.flightTicket.tvAnswerFrom.text = getString(R.string.hidden_answer)
        binding.flightTicket.tvAnswerTo.text = getString(R.string.hidden_answer)


        when (clickCount) {
            1 -> {
                binding.flightTicket.tvAirportFrom.text = getString(R.string.cardiff_airport)
                binding.flightTicket.tvAirportTo.text = getString(R.string.nairobi_airport)
            }

            2 -> {
                binding.flightTicket.tvAirportFrom.text = getString(R.string.london_airport)
                binding.flightTicket.tvAirportTo.text = getString(R.string.canberra_airport)
            }

            3 -> {
                binding.flightTicket.tvAirportFrom.text = getString(R.string.edinburgh_airport)
                binding.flightTicket.tvAirportTo.text = getString(R.string.brasilia_airport)
            }
        }
    }


    private fun checkAnswered(answers: List<AnswerQ12>) {

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

    private fun updateAnswers(showAnswer: Int) {

        binding.flightTicket.tvAnswerFrom.setTextColor(getColor(R.color.green))
        binding.flightTicket.tvAnswerTo.setTextColor(getColor(R.color.green))

        when (showAnswer) {
            1 -> {
                binding.answer1Team1.text = team1?.answer1
                binding.answer2Team1.text = team1?.answer2

                binding.answer1Team2.text = team2?.answer1
                binding.answer2Team2.text = team2?.answer2

                binding.flightTicket.tvAnswerFrom.text = getString(R.string.belfast)
                binding.flightTicket.tvAnswerTo.text = getString(R.string.bangkok)
            }

            2 -> {
                binding.answer1Team1.text = team1?.answer3
                binding.answer2Team1.text = team1?.answer4

                binding.answer1Team2.text = team2?.answer3
                binding.answer2Team2.text = team2?.answer4

                binding.flightTicket.tvAnswerFrom.text = getString(R.string.cardiff)
                binding.flightTicket.tvAnswerTo.text = getString(R.string.nairobi)
            }

            3 -> {
                binding.answer1Team1.text = team1?.answer5
                binding.answer2Team1.text = team1?.answer6

                binding.answer1Team2.text = team2?.answer5
                binding.answer2Team2.text = team2?.answer6

                binding.flightTicket.tvAnswerFrom.text = getString(R.string.london)
                binding.flightTicket.tvAnswerTo.text = getString(R.string.canberra)
            }
            4 -> {
                binding.answer1Team1.text = team1?.answer7
                binding.answer2Team1.text = team1?.answer8

                binding.answer1Team2.text = team2?.answer7
                binding.answer2Team2.text = team2?.answer8

                binding.flightTicket.tvAnswerFrom.text = getString(R.string.edinburgh)
                binding.flightTicket.tvAnswerTo.text = getString(R.string.brasilia)
            }
        }
    }

}