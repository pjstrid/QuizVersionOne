package com.example.quizappversionone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityScoreBinding


class ScoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityScoreBinding

    lateinit var authViewModel: AuthViewModel

    lateinit var viewModel: ViewModel

    private var currentUserEmail: String? = ""
    private var currentUsername: String? = ""

    var scoreTeam1 = 0

    var scoreTeam2 = 0

//    var questionCount = 0

    private var team1: Score? = null
    private var team2: Score? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        setNumbersInRoundButtons()

        currentUserEmail = intent.getStringExtra("currentUserEmail")

        val team1Email = "team1@quiz.se"
        val team2Email = "team2@quiz.se"
        val qmEmail = "quizmaster@quiz.se"

        when (currentUserEmail) {
            team1Email -> {
                currentUsername = "TEAM 1"
            }

            team2Email -> {
                currentUsername = "TEAM 2"
            }

            qmEmail -> {
                currentUsername = "QUIZMASTER"
                binding.editScore.visibility = View.VISIBLE
            }
        }
        binding.currentUser.text = currentUsername


        viewModel.scores.observe(this)
        { scoreList ->
            updateScoreUI(scoreList)
        }

        binding.btnExitQuiz.setOnClickListener {
            exitQuiz()
        }
        binding.textExitQuiz.setOnClickListener {
            exitQuiz()
        }


        binding.btnResetScore.setOnClickListener {
            scoreTeam1 = 0
            scoreTeam2 = 0

            updateScore(team1Email, scoreTeam1)
            updateScore(team2Email, scoreTeam2)
        }


        binding.btnPlusTeam1.setOnClickListener {
            scoreTeam1++
            updateScore(team1Email, scoreTeam1)
        }

        binding.btnMinusTeam1.setOnClickListener {
            scoreTeam1--
            updateScore(team1Email, scoreTeam1)
        }

        binding.btnPlusTeam2.setOnClickListener {
            scoreTeam2++
            updateScore(team2Email, scoreTeam2)
        }

        binding.btnMinusTeam2.setOnClickListener {
            scoreTeam2--
            updateScore(team2Email, scoreTeam2)
        }

        var clickCount = 0
        binding.editScore.setOnClickListener {
            clickCount++

            if (clickCount % 2 == 1) {

                binding.btnResetScore.visibility = View.VISIBLE
                binding.btnPlusTeam1.visibility = View.VISIBLE
                binding.btnPlusTeam2.visibility = View.VISIBLE
                binding.btnMinusTeam1.visibility = View.VISIBLE
                binding.btnMinusTeam2.visibility = View.VISIBLE
            } else if (clickCount % 2 == 0) {


                binding.btnResetScore.visibility = View.INVISIBLE
                binding.btnPlusTeam1.visibility = View.INVISIBLE
                binding.btnPlusTeam2.visibility = View.INVISIBLE
                binding.btnMinusTeam1.visibility = View.INVISIBLE
                binding.btnMinusTeam2.visibility = View.INVISIBLE
            }

        }



        binding.qNr1.root.setOnClickListener {
            binding.qNr1.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("1")
        }

        binding.qNr2.root.setOnClickListener {
            binding.qNr2.root.setBackgroundResource(R.drawable.bg_round_green)
            Log.i("!!!", "scores: ${viewModel.scores.value}")

            openQuestion("2")
        }

        binding.qNr3.root.setOnClickListener {
            binding.qNr3.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("3")
        }

        binding.qNr4.root.setOnClickListener {
            binding.qNr4.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("4")
        }

        binding.qNr5.root.setOnClickListener {
            binding.qNr5.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("5")
        }

        binding.qNr6.root.setOnClickListener {
            binding.qNr6.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("6")
        }

        binding.qNr7.root.setOnClickListener {
            binding.qNr7.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("7")
        }

        binding.qNr8.root.setOnClickListener {
            binding.qNr8.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("8")
        }

        binding.qNr9.root.setOnClickListener {
            binding.qNr9.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("9")
        }

        binding.qNr10.root.setOnClickListener {
            binding.qNr10.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("10")
        }

        binding.qNr11.root.setOnClickListener {
            binding.qNr11.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("11")
        }

        binding.qNr12.root.setOnClickListener {
            binding.qNr12.root.setBackgroundResource(R.drawable.bg_round_green)
            openQuestion("12")

            if (currentUserEmail == qmEmail) {
                binding.btnWinnerOfQuiz.visibility = View.VISIBLE
            }
        }

        var winnerClickCount = 0
        binding.btnWinnerOfQuiz.setOnClickListener {

            winnerClickCount++

            checkWinner()

            if (winnerClickCount % 2 == 1) {
                binding.winnerBox.visibility = View.VISIBLE
            } else {
                binding.winnerBox.visibility = View.INVISIBLE

            }
        }

    }

    private fun checkWinner() {

        if (scoreTeam1 > scoreTeam2) {
            binding.winnerBox.text = getString(R.string.the_winner_is_team1)
        } else {
            binding.winnerBox.text = getString(R.string.the_winner_is_team2)
        }
    }

    private fun setNumbersInRoundButtons() {
        binding.qNr1.root.text = getString(R.string._1)
        binding.qNr2.root.text = getString(R.string._2)
        binding.qNr3.root.text = getString(R.string._3)
        binding.qNr4.root.text = getString(R.string._4)
        binding.qNr5.root.text = getString(R.string._5)
        binding.qNr6.root.text = getString(R.string._6)
        binding.qNr7.root.text = getString(R.string._7)
        binding.qNr8.root.text = getString(R.string._8)
        binding.qNr9.root.text = getString(R.string._9)
        binding.qNr10.root.text = getString(R.string._10)
        binding.qNr11.root.text = getString(R.string._11)
        binding.qNr12.root.text = getString(R.string._12)
//        binding.qNr13.root.text = getString(R.string._13)
//        binding.qNr14.root.text = getString(R.string._14)
//        binding.qNr15.root.text = getString(R.string._15)
//        binding.qNr16.root.text = getString(R.string._16)
//        binding.qNr17.root.text = getString(R.string._17)
//        binding.qNr18.root.text = getString(R.string._18)
//        binding.qNr19.root.text = getString(R.string._19)
//        binding.qNr20.root.text = getString(R.string._20)
    }

    fun updateScore(id: String, score: Int) {

        viewModel.updateScore(id, score)
    }

    fun updateScoreUI(scoreList: List<Score>) {
        team1 = scoreList.find { it.name == "TEAM 1" }
        team2 = scoreList.find { it.name == "TEAM 2" }

        binding.scoreTeam1.text = team1?.score.toString()
        binding.scoreTeam2.text = team2?.score.toString()

        scoreTeam1 = team1?.score ?: 0
        scoreTeam2 = team2?.score ?: 0
    }

    fun openQuestion(number: String) {
        val number = number

        val intent = when (number) {
            "1" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionOneActivity::class.java)
                } else {
                    Intent(this, QuestionOneActivity::class.java)
                }
            }

            "2" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionTwoActivity::class.java)
                } else {
                    Intent(this, QuestionTwoActivity::class.java)
                }
            }

            "3" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionThreeActivity::class.java)
                } else {
                    Intent(this, QuestionThreeActivity::class.java)
                }
            }

            "4" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionFourActivity::class.java)
                } else {
                    Intent(this, QuestionFourActivity::class.java)
                }
            }

            "5" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionFiveActivity::class.java)
                } else {
                    Intent(this, QuestionFiveActivity::class.java)
                }
            }

            /**
             * QUESTION 6 = ACTIVITY EIGHT
             */
            "6" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionEightActivity::class.java)
                } else {
                    Intent(this, QuestionEightActivity::class.java)
                }
            }

            "7" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionSevenActivity::class.java)
                } else {
                    Intent(this, QuestionSevenActivity::class.java)
                }
            }

            /**
             * QUESTION 8 = ACTIVITY SIX
             */
            "8" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionSixActivity::class.java)
                } else {
                    Intent(this, QuestionSixActivity::class.java)
                }
            }


            "9" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionNineActivity::class.java)
                } else {
                    Intent(this, QuestionNineActivity::class.java)
                }
            }

            "10" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionTenActivity::class.java)
                } else {
                    Intent(this, QuestionTenActivity::class.java)
                }
            }

            "11" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, QuestionElevenActivity::class.java)
                } else {
                    Intent(this, QuestionElevenActivity::class.java)
                }
            }

            "12" -> {
                if (currentUsername == "TEAM 1" || currentUsername == "TEAM 2") {
                    Intent(this, AnswerQuestionTwelveActivity::class.java)
                } else {
                    Intent(this, QuestionTwelveActivity::class.java)
                }
            }


            else -> {
                Intent(this, ScoreActivity::class.java)
            }
        }

        startActivity(intent)
    }

    private fun exitQuiz() {

        viewModel.updateButtonClicked("team1@quiz.se", false)
        viewModel.updateButtonClicked("team2@quiz.se", false)
        viewModel.updateButtonClicked("quizmaster@quiz.se", false)

        authViewModel.logOut()

        val intent = Intent(this@ScoreActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}