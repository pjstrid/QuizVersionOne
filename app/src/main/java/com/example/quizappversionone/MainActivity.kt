package com.example.quizappversionone

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quizappversionone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var authViewModel: AuthViewModel

    lateinit var viewModel: ViewModel

    private var currentUserEmail: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val email1 = "team1@quiz.se"
        val email2 = "team2@quiz.se"
        val emailQ = "quizmaster@quiz.se"

        val green = R.drawable.bg_tv_green
        val red = R.drawable.bg_tv_red

        val greenLight = R.drawable.bg_round_green
        val redLight = R.drawable.bg_round_red


        binding.btnResetReady.setOnClickListener {
            resetButtonClicked(email1, email2, emailQ, red)
            binding.btnTeam1.setBackgroundResource(R.drawable.bg_tv_orange)
            binding.btnTeam2.setBackgroundResource(R.drawable.bg_tv_orange)
            binding.btnQuizmaster.setBackgroundResource(R.drawable.bg_tv_orange)
        }

        viewModel.users.observe(this) { users ->
            checkClicked(users, green, red, greenLight, redLight)
        }


        binding.btnTeam1.setOnClickListener {
            currentUserEmail = email1
            login(email1)
            binding.btnTeam1.setBackgroundResource(R.drawable.bg_tv_selected)
        }

        binding.btnTeam2.setOnClickListener {
            currentUserEmail = email2
            login(email2)
            binding.btnTeam2.setBackgroundResource(R.drawable.bg_tv_selected)

        }

        binding.btnQuizmaster.setOnClickListener {
            currentUserEmail = emailQ
            login(emailQ)

            binding.btnQuizmaster.setBackgroundResource(R.drawable.bg_tv_selected)
            binding.btnResetReady.visibility = View.VISIBLE
        }

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.putExtra("currentUserEmail", currentUserEmail)
            startActivity(intent)
        }

    }

    private fun resetButtonClicked(
        email1: String,
        email2: String,
        emailQM: String,
        red: Int
    ) {

        // Team 1 reset
        viewModel.updateButtonClicked(email1, false)
        // Team 2 reset
        viewModel.updateButtonClicked(email2, false)
        // Quizmaster reset
        viewModel.updateButtonClicked(emailQM, false)

        binding.btnStart.setBackgroundResource(red)
        binding.btnStart.isClickable = false
    }


    fun login(email: String) {
        val password = "quiz123"
        authViewModel.login(email, password, onSuccess = {
            viewModel.updateButtonClicked(email, true)

        }, onFailure = {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
        })
    }


    private fun checkClicked(users: List<User>, green: Int, red: Int, greenLight: Int, redLight: Int) {

        val team1 = users.find { it.name == "TEAM 1" }
        val team2 = users.find { it.name == "TEAM 2" }
        val qm = users.find { it.name == "QUIZMASTER" }

        if (team1?.buttonClicked == true) {
            binding.readyTeam1.setBackgroundResource(greenLight)
        } else {
            binding.readyTeam1.setBackgroundResource(redLight)
        }

        if (team2?.buttonClicked == true) {
            binding.readyTeam2.setBackgroundResource(greenLight)
        } else {
            binding.readyTeam2.setBackgroundResource(redLight)
        }

        if (qm?.buttonClicked == true) {
            binding.readyQM.setBackgroundResource(greenLight)
        } else {
            binding.readyQM.setBackgroundResource(redLight)
        }

        // Start button turns green when everybody is ready
        val allReady = team1?.buttonClicked == true &&
                team2?.buttonClicked == true &&
                qm?.buttonClicked == true

        if (allReady) {
            binding.btnStart.setBackgroundResource(green)
            binding.btnStart.isClickable = true
        } else {
            binding.btnStart.setBackgroundResource(red)
            binding.btnStart.isClickable = false
        }
    }
}