package diegowendel.github.io.cyberkids

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class TabuadaActivity : AppCompatActivity() {

    // View components
    private lateinit var buttonAnswer1:Button
    private lateinit var buttonAnswer2:Button
    private lateinit var buttonAnswer3:Button
    private lateinit var buttonAnswer4:Button
    private lateinit var textQuestion:TextView
    private lateinit var live1:ImageView
    private lateinit var live2:ImageView
    private lateinit var live3:ImageView
    private lateinit var textPoints:TextView

    // Media components
    private lateinit var soundError:MediaPlayer
    private lateinit var soundCorrect:MediaPlayer

    // Operations controller
    private lateinit var alternatives:MutableSet<Int>
    private var factor1:Int = 1
    private var factor2:Int = 1
    private var lives:Int = 3
    private var points:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // Setup
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        factor1 = intent.getIntExtra("tabuada", 1)

        // App
        setupComponents()
        setNumbers()
    }

    fun setupComponents() {
        buttonAnswer1 = findViewById(R.id.buttonAnswer1)
        buttonAnswer2 = findViewById(R.id.buttonAnswer2)
        buttonAnswer3 = findViewById(R.id.buttonAnswer3)
        buttonAnswer4 = findViewById(R.id.buttonAnswer4)
        buttonAnswer1.setOnClickListener { onClickButtonAnswer(buttonAnswer1) }
        buttonAnswer2.setOnClickListener { onClickButtonAnswer(buttonAnswer2) }
        buttonAnswer3.setOnClickListener { onClickButtonAnswer(buttonAnswer3) }
        buttonAnswer4.setOnClickListener { onClickButtonAnswer(buttonAnswer4) }
        textQuestion = findViewById(R.id.textQuestion)
        live1 = findViewById(R.id.live1)
        live2 = findViewById(R.id.live2)
        live3 = findViewById(R.id.live3)
        textPoints = findViewById(R.id.textPoints)
        soundError = MediaPlayer.create(this, R.raw.error)
        soundCorrect = MediaPlayer.create(this, R.raw.correct)
    }

    fun setNumbers() {
        val correctAlternative = factor1 * factor2
        alternatives = mutableSetOf(correctAlternative)
        while (alternatives.size < 4) {
            alternatives.add(getRandomNumber(until = factor1*10))
        }
        var alternativesShuffled = alternatives.shuffled()
        buttonAnswer1.text = "${alternativesShuffled[0]}"
        buttonAnswer2.text = "${alternativesShuffled[1]}"
        buttonAnswer3.text = "${alternativesShuffled[2]}"
        buttonAnswer4.text = "${alternativesShuffled[3]}"
        buttonAnswer1.setBackgroundResource(R.drawable.rounded_button)
        buttonAnswer2.setBackgroundResource(R.drawable.rounded_button)
        buttonAnswer3.setBackgroundResource(R.drawable.rounded_button)
        buttonAnswer4.setBackgroundResource(R.drawable.rounded_button)
        textQuestion.text = "${factor1} x ${factor2}"
    }

    fun onClickButtonAnswer(button: Button) {
        val isCorrect:Boolean = (factor1 * factor2) == (button.text.toString().toInt())
        if (isCorrect) {
            button.setBackgroundResource(R.drawable.rounded_button_correct)
            soundCorrect.start()
            points += 10
            textPoints.text = "${points} Pontos"
            factor2++
            if (factor2 > 2) {
                showDialogVictory()
            }
            Handler().postDelayed({ setNumbers() }, 1000)
        } else {
            button.setBackgroundResource(R.drawable.rounded_button_error)
            soundError.start()
            lives--
            if(lives == 2) {
                live3.setColorFilter(ContextCompat.getColor(this, R.color.black))
            } else if (lives == 1) {
                live2.setColorFilter(ContextCompat.getColor(this, R.color.black))
            } else {
                live1.setColorFilter(ContextCompat.getColor(this, R.color.black))
                showDialogGameOver()
            }
        }
    }

    fun getRandomNumber(from: Int = 1, until: Int = 10) = Random.nextInt(from, until)

    fun showDialogGameOver() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_game_over, null)
        val builder = AlertDialog.Builder(this).setView(dialogView)
        val dialog = builder.show()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.findViewById<Button>(R.id.buttonTryAgain)?.setOnClickListener {
            setNumbers()
            factor2 = 1
            lives = 3
            live1.setColorFilter(ContextCompat.getColor(this, R.color.red))
            live2.setColorFilter(ContextCompat.getColor(this, R.color.red))
            live3.setColorFilter(ContextCompat.getColor(this, R.color.red))
            dialog.dismiss()
        }
    }

    fun showDialogVictory() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_victory, null)
        val builder = AlertDialog.Builder(this).setView(dialogView)
        val dialog = builder.show()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.findViewById<Button>(R.id.buttonMenu)?.setOnClickListener {
            finish()
        }

        dialog.findViewById<Button>(R.id.buttonNext)?.setOnClickListener {

        }
    }
}
