//Irdina
//start_page is the beginning page where user can choose to read the instruction first
//or start the game.

package com.example.whack_a_ghost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.ImageButton
import android.widget.ImageView


class start_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //ghost animation movement
        val ghost: ImageView = findViewById<ImageView>(R.id.ghost_move)
        val animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 1000
        animation.fillAfter = true

        val translateAnimation = TranslateAnimation(0f, 500f, 0f, 0f)
        translateAnimation.duration = 1000
        translateAnimation.fillAfter = true

        val animationFadeOut = AlphaAnimation(1.0f, 0.0f)
        animationFadeOut.startOffset = 1000
        animationFadeOut.duration = 1000
        animationFadeOut.fillAfter = true

        val set = AnimationSet(true)
        set.addAnimation(animation)
        set.addAnimation(translateAnimation)
        set.addAnimation(animationFadeOut)
        set.repeatCount = Animation.INFINITE

        ghost.startAnimation(set)

        // open game_page when clicked start button
        val startbtn = findViewById<ImageButton>(R.id.start_button)
        startbtn.setOnClickListener {
            val intent = Intent(this, game_page::class.java)
            startActivity(intent)
        }

        //open about_page when clicked about button
        val aboutbtn = findViewById<ImageButton>(R.id.about_button)
        aboutbtn.setOnClickListener {
            val intent = Intent(this, about_page::class.java)
            startActivity(intent)
        }



    }
}