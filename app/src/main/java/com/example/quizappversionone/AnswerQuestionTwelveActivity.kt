package com.example.quizappversionone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityAnswerQuestionTwelveBinding
import java.util.Locale.getDefault

class AnswerQuestionTwelveActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnswerQuestionTwelveBinding

    lateinit var authViewModel: AuthViewModel

    lateinit var viewModel: ViewModel

    lateinit var currentUsername: String
    lateinit var currentUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerQuestionTwelveBinding.inflate(layoutInflater)
        setContentView(binding.root)


        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val green = R.drawable.bg_tv_green

        when (authViewModel.auth.currentUser?.email) {
            "team1@quiz.se" -> {
                currentUsername = "TEAM 1"
                currentUser = "team1@quiz.se"
            }

            "team2@quiz.se" -> {
                currentUsername = "TEAM 2"
                currentUser = "team2@quiz.se"
            }
        }
        binding.currentUser.text = currentUsername

        setDestinations()

        binding.btnClose.setOnClickListener {
            val resetAnswer = getString(R.string.answer)

            viewModel.updateAnswersQ12(
                currentUser,
                resetAnswer,
                resetAnswer,
                resetAnswer,
                resetAnswer,
                resetAnswer,
                resetAnswer,
                resetAnswer,
                resetAnswer,
                answered = false
            )

            finish()
        }

        binding.btnLockInAnswer.setOnClickListener {
            binding.btnLockInAnswer.setBackgroundResource(green)

            sendAnswers()
        }

    }

    fun setDestinations() {

        binding.ticket1.tvAirportFrom.text = getString(R.string.belfast_airport)
        binding.ticket1.tvAirportTo.text = getString(R.string.bangkok_airport)

        binding.ticket2.tvAirportFrom.text = getString(R.string.cardiff_airport)
        binding.ticket2.tvAirportTo.text = getString(R.string.nairobi_airport)

        binding.ticket3.tvAirportFrom.text = getString(R.string.london_airport)
        binding.ticket3.tvAirportTo.text = getString(R.string.canberra_airport)

        binding.ticket4.tvAirportFrom.text = getString(R.string.edinburgh_airport)
        binding.ticket4.tvAirportTo.text = getString(R.string.brasilia_airport)
    }

    private fun sendAnswers() {
        val answer1 = binding.ticket1.etAnswerFrom.text.toString().uppercase(getDefault())
        val answer2 = binding.ticket1.etAnswerTo.text.toString().uppercase(getDefault())
        val answer3 = binding.ticket2.etAnswerFrom.text.toString().uppercase(getDefault())
        val answer4 = binding.ticket2.etAnswerTo.text.toString().uppercase(getDefault())
        val answer5 = binding.ticket3.etAnswerFrom.text.toString().uppercase(getDefault())
        val answer6 = binding.ticket3.etAnswerTo.text.toString().uppercase(getDefault())
        val answer7 = binding.ticket4.etAnswerFrom.text.toString().uppercase(getDefault())
        val answer8 = binding.ticket4.etAnswerTo.text.toString().uppercase(getDefault())

        viewModel.updateAnswersQ12(
            currentUser, answer1, answer2, answer3,
            answer4, answer5, answer6, answer7,
            answer8, true
        )
    }
}