package com.thecode.cryptoview.presentation.onboarding


import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.thecode.cryptoview.databinding.ActivityOnboardingFinishBinding
import com.thecode.cryptoview.presentation.main.MainActivity

class OnboardingFinishActivity : AppCompatActivity() {
    private lateinit var btnStart: LinearLayout

    private lateinit var binding: ActivityOnboardingFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingFinishBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        btnStart = binding.layoutStart
        btnStart.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
