package diegowendel.github.io.cyberkids

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class SelecaoTabuadaActivity : AppCompatActivity() {

    private lateinit var buttons:Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecao_tabuada)
        setupComponents()
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
            val tabuada = button.text.toString().toInt()
            button.setOnClickListener{ onClickButtonSelecao(tabuada) }
        }
    }

    fun onClickButtonSelecao(tabuada: Int) {
        val intent = Intent(this, TabuadaActivity::class.java)
        intent.putExtra("tabuada", tabuada)
        startActivity(intent)
    }
}
