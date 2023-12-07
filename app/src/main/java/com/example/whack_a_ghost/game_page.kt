//Irdina
//This game_page for game functions including the timer when ghost will come out
//and random hole for it visible. I used lambda on click listener to simplify and avoid more mistakes

package com.example.whack_a_ghost


import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.*

class game_page : AppCompatActivity() {

            private val random = Random()
            private var score = 0
            private var numGhostsAppeared = 0
            private var gameRunning = false  // to ensure the game stop when its reached

            private lateinit var holes: Array<ImageView>
            private lateinit var ghosts: Array<ImageView>
            private lateinit var score_count: TextView


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_game_page)

                // Initialize hole and ghost imageViews
                holes = arrayOf(
                    findViewById(R.id.hole_1),
                    findViewById(R.id.hole_2),
                    findViewById(R.id.hole_3),
                    findViewById(R.id.hole_4)
                )

                ghosts = arrayOf(
                    findViewById(R.id.ghost_1),
                    findViewById(R.id.ghost_2),
                    findViewById(R.id.ghost_3),
                    findViewById(R.id.ghost_4)
                )

                //Initialize score counter
                score_count = findViewById(R.id.score_count)


                // Set the initial visibility of ghosts to View.INVISIBLE
                ghosts.forEach { ghost ->
                    ghost.visibility = View.INVISIBLE
                }



                // Set click listeners for ghost image views
                // Using Lambda to use setOnClickListener
                ghosts.forEachIndexed { index, ghost ->
                    ghost.setOnClickListener {
                        if (gameRunning && ghost.visibility == View.VISIBLE) {
                            score++
                            score_count.text = "$score"
                            // Change the color of the ghost
                            val color = Color.RED // Change ghost colour to RED
                            ghost.setColorFilter(color, PorterDuff.Mode.SRC_IN)

                            // Delay for a certain duration
                            val delayMillis = 400L // delay in millisecond
                            Handler().postDelayed({
                                // Reset the color of the ghost
                                ghost.clearColorFilter()
                            }, delayMillis)
                        }
                    }
                }


            // Start the game loop
                startGameLoop()
            }

            private fun startGameLoop() {
                gameRunning = true
                numGhostsAppeared = 0
                score = 0
                val handler = Handler(Looper.getMainLooper())
                val delayMillis = 1000L // Delay between ghost appearances (in milliseconds)
                handler.postDelayed(object : Runnable {
                    override fun run() {
                        if (numGhostsAppeared >= 10) {
                            // Game finished
                            gameRunning = false
                            displayScore()
                        } else {
                            // Randomly choose a hole to appear from
                            val holeIndex = random.nextInt(holes.size)
                            val hole = holes[holeIndex]

                            // Move ghost to the chosen hole
                            val ghost = ghosts[holeIndex]
                            ghost.x = hole.x + (hole.width - ghost.width) / 2
                            ghost.y = hole.y + hole.paddingTop + (hole.height - ghost.height)

                            // Reset ghost visibility and start animation
                            ghost.alpha = 1f
                            ghost.visibility = View.VISIBLE

                            ghost.animate()
                                .translationYBy(-100f) // movement
                                .setDuration(550) //delay before disappear
                                .withEndAction {
                                    ghost.visibility = View.INVISIBLE
                                }
                                .start()

                            // Increment the number of ghosts appeared
                            numGhostsAppeared++

                            // Schedule the next appearance
                            handler.postDelayed(this, delayMillis)
                        }
                    }
                }, delayMillis)
            }


            //Displaying score using alert dialog
            private fun displayScore() {
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("GAME OVER!")
                    .setMessage("Your Score: $score")
                    .setPositiveButton("PLAY AGAIN") { dialog, _ ->
                        // Perform replay action here
                        startGameLoop()
                        dialog.dismiss()
                        score_count.text = "0"
                    }
                    .create()

                alertDialog.show()
            }

        }


