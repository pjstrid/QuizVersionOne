package com.example.quizappversionone

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quizappversionone.databinding.ActivityQuestionFourBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionFourActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionFourBinding
    private lateinit var viewModel: ViewModel

    private var team1: AnswerQ4? = null
    private var team2: AnswerQ4? = null

    private lateinit var correctPairs1: List<Pair<String, String>>
    private lateinit var correctPairs2: List<Pair<String, String>>
    private lateinit var correctPairs3: List<Pair<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionFourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        setupCorrectPairs()
        setupResetButton()
        setupCloseButton()

        viewModel.answersQ4.observe(this) { answers ->
            team1 = answers.find { it.name == "TEAM 1" }
            team2 = answers.find { it.name == "TEAM 2" }

            checkAnswered(answers)
        }

        var qCount = 0
        binding.btnShow.setOnClickListener {
            qCount++
            startCountdown { updateAnswers(qCount) }
        }
    }

    private fun setupCorrectPairs() {
        correctPairs1 = listOf(
            getString(R.string.sausage_roll) to getString(R.string.korv_i_smördeg),
            getString(R.string.pig_in_blanket) to getString(R.string.korv_i_bacon),
            getString(R.string.toad_in_a_hole) to getString(R.string.korv_i_en_smet)
        )

        correctPairs2 = listOf(
            getString(R.string.tenderloin) to getString(R.string.oxfile),
            getString(R.string.sirloin) to getString(R.string.ryggbiff),
            getString(R.string.rib_eye) to getString(R.string.entrecote)
        )

        correctPairs3 = listOf(
            getString(R.string.cricket) to getString(R.string.yorker),
            getString(R.string.rugby) to getString(R.string.scrum),
            getString(R.string.hockey) to getString(R.string.the_d)
        )
    }

    private fun setupResetButton() {
        binding.btnReset.setOnClickListener {
            val orange = R.drawable.bg_tv_orange

            val team1Views = listOf(
                binding.answer1Team1, binding.answer2Team1, binding.answer3Team1,
                binding.answer4Team1, binding.answer5Team1, binding.answer6Team1
            )

            val team2Views = listOf(
                binding.answer1Team2, binding.answer2Team2, binding.answer3Team2,
                binding.answer4Team2, binding.answer5Team2, binding.answer6Team2
            )

            team1Views.forEachIndexed { index, tv ->
                tv.text = getString(R.string.pair, index % 3 + 1)
                tv.setBackgroundResource(orange)
            }

            team2Views.forEachIndexed { index, tv ->
                tv.text = getString(R.string.pair, index % 3 + 1)
                tv.setBackgroundResource(orange)
            }
        }
    }

    private fun setupCloseButton() {
        binding.btnClose.setOnClickListener { finish() }
    }

    private fun startCountdown(onFinish: () -> Unit) {
        binding.tvNumber.visibility = View.VISIBLE

        lifecycleScope.launch {
            binding.tvNumber.text = "3"
            delay(1000)
            binding.tvNumber.text = "2"
            delay(1000)
            binding.tvNumber.text = "1"
            delay(1000)
            binding.tvNumber.visibility = View.INVISIBLE

            onFinish()
        }
    }

    private fun checkAnswered(answers: List<AnswerQ4>) {
        val team1 = answers.find { it.name == "TEAM 1" }
        val team2 = answers.find { it.name == "TEAM 2" }

        val green = R.drawable.bg_tv_green
        val red = R.drawable.bg_tv_red

        binding.team1Check.setBackgroundResource(if (team1?.answered == true) green else red)
        binding.team2Check.setBackgroundResource(if (team2?.answered == true) green else red)

        binding.btnShow.visibility =
            if (team1?.answered == true && team2?.answered == true) View.VISIBLE else View.INVISIBLE
    }

    private fun highlightIfCorrect(
        left: TextView,
        right: TextView,
        pair: Pair<String?, String?>,
        qCount: Int
    ): Boolean {

        val listToCheck = when (qCount) {
            1 -> correctPairs1
            2 -> correctPairs2
            3 -> correctPairs3
            else -> emptyList()
        }

        val isCorrect = listToCheck.any { correct ->
            correct.first.equals(pair.first, ignoreCase = true) &&
                    correct.second.equals(pair.second, ignoreCase = true)
        }

        val color = if (isCorrect) R.drawable.bg_tv_green else R.drawable.bg_tv_red
        left.setBackgroundResource(color)
        right.setBackgroundResource(color)

        return isCorrect
    }


    private fun updateAnswers(qCount: Int) {

        // Team 1 answers
        binding.answer1Team1.text = team1?.answer1
        binding.answer2Team1.text = team1?.answer2
        binding.answer3Team1.text = team1?.answer3
        binding.answer4Team1.text = team1?.answer4
        binding.answer5Team1.text = team1?.answer5
        binding.answer6Team1.text = team1?.answer6

        // Team 2 answers
        binding.answer1Team2.text = team2?.answer1
        binding.answer2Team2.text = team2?.answer2
        binding.answer3Team2.text = team2?.answer3
        binding.answer4Team2.text = team2?.answer4
        binding.answer5Team2.text = team2?.answer5
        binding.answer6Team2.text = team2?.answer6

        // --- TEAM 1 ---
        var correctT1 = 0
        if (highlightIfCorrect(
                binding.answer1Team1,
                binding.answer4Team1,
                team1?.answer1 to team1?.answer4,
                qCount
            )
        ) correctT1++
        if (highlightIfCorrect(
                binding.answer2Team1,
                binding.answer5Team1,
                team1?.answer2 to team1?.answer5,
                qCount
            )
        ) correctT1++
        if (highlightIfCorrect(
                binding.answer3Team1,
                binding.answer6Team1,
                team1?.answer3 to team1?.answer6,
                qCount
            )
        ) correctT1++

        // --- TEAM 2 ---
        var correctT2 = 0
        if (highlightIfCorrect(
                binding.answer1Team2,
                binding.answer4Team2,
                team2?.answer1 to team2?.answer4,
                qCount
            )
        ) correctT2++
        if (highlightIfCorrect(
                binding.answer2Team2,
                binding.answer5Team2,
                team2?.answer2 to team2?.answer5,
                qCount
            )
        ) correctT2++
        if (highlightIfCorrect(
                binding.answer3Team2,
                binding.answer6Team2,
                team2?.answer3 to team2?.answer6,
                qCount
            )
        ) correctT2++

        // Uppdatera poäng EN gång per lag
        val current1 = viewModel.getScore(viewModel.team1Email)?.score ?: 0
        viewModel.updateScore(viewModel.team1Email, current1 + correctT1)

        val current2 = viewModel.getScore(viewModel.team2Email)?.score ?: 0
        viewModel.updateScore(viewModel.team2Email, current2 + correctT2)
    }

}
