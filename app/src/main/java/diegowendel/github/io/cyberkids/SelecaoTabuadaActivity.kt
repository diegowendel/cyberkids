package diegowendel.github.io.cyberkids

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import diegowendel.github.io.cyberkids.utils.PreferenceUtils

class SelecaoTabuadaActivity : AppCompatActivity() {

    private lateinit var buttons:Array<ViewGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecao_tabuada)
        setupComponents()
    }

    override fun onResume() {
        super.onResume()
        buttons.forEach { button ->
            val tabuada = (button.getChildAt(1) as TextView).text.toString().toInt()
            val isConcluido = PreferenceUtils.getBoolean(this,"tabuada-${tabuada}")
            if (isConcluido) {
                (button.getChildAt(0) as ImageView).setImageResource(R.drawable.diamond_emoji)
            }
        }
    }

    fun setupComponents() {
        buttons = arrayOf(findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9),
            findViewById(R.id.button10))

        buttons.forEach { button ->
            val tabuada = (button.getChildAt(1) as TextView).text.toString().toInt()
            val isConcluido = PreferenceUtils.getBoolean(this,"tabuada-${tabuada}")
            if (isConcluido) {
                (button.getChildAt(0) as ImageView).setImageResource(R.drawable.diamond_emoji)
            }
            button.setOnClickListener{ onClickButtonSelecao(tabuada) }
        }
    }

    fun onClickButtonSelecao(tabuada: Int) {
        val intent = Intent(this, TabuadaActivity::class.java)
        intent.putExtra("tabuada", tabuada)
        startActivity(intent)
    }
}
