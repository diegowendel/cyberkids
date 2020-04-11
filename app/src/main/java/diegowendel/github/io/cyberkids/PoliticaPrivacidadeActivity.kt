package diegowendel.github.io.cyberkids

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class PoliticaPrivacidadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_politica_privacidade)
        val webview = findViewById<WebView>(R.id.webview)
        webview.loadUrl("file:///android_asset/privacy_policy.html")
    }
}
