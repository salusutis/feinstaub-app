/*
 * Copyright © 2019 Marc Auberer. All rights reserved.
 */

package com.mrgames13.jimdo.feinstaubapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.mrgames13.jimdo.feinstaubapp.R
import com.mrgames13.jimdo.splashscreen.App.SplashScreenBuilder

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Display SplashScreen
        SplashScreenBuilder.getInstance(this)
            .setVideo(R.raw.splash_animation)
            .setVideoDark(R.raw.splash_animation_dark)
            .setImage(R.drawable.app_icon)
            .show()
        overridePendingTransition(0, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SplashScreenBuilder.SPLASH_SCREEN_FINISHED) {
            val intent = Intent(this, MainActivity::class.java)
            if (getIntent().extras != null) intent.putExtras(getIntent().extras!!)
            startActivity(intent)
            finish()
        }
    }
}
