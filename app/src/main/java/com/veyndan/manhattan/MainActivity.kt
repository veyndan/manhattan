package com.veyndan.manhattan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.*

val BUTTON_IDS = List(9) { View.generateViewId() }

class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityComponent().setContentView(this)

        val button0 = findViewById<Button>(BUTTON_IDS[0])

        disposables.add(button0.clicks()
                .subscribe {
                    if (button0.text.isEmpty()) {
                        button0.text = "X"
                    } else {
                        button0.text = if (button0.text == "X") "O" else "X"
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}

class MainActivityComponent : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        gridLayout {
            columnCount = 3
            BUTTON_IDS.forEach { buttonId ->
                button {
                    id = buttonId
                }
            }
        }
    }
}
