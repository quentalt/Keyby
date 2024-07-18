package com.quentin.keyby

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MyInputMethodService : InputMethodService() {

    private var isCaps = false
    private lateinit var suggestion1: TextView
    private lateinit var suggestion2: TextView
    private lateinit var suggestion3: TextView

    override fun onCreateInputView(): View {
        val keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null)

        // Initialiser les suggestions
        suggestion1 = keyboardView.findViewById(R.id.suggestion1)
        suggestion2 = keyboardView.findViewById(R.id.suggestion2)
        suggestion3 = keyboardView.findViewById(R.id.suggestion3)

        // Ajoutez des écouteurs pour les suggestions
        suggestion1.setOnClickListener { commitSuggestion(suggestion1.text.toString()) }
        suggestion2.setOnClickListener { commitSuggestion(suggestion2.text.toString()) }
        suggestion3.setOnClickListener { commitSuggestion(suggestion3.text.toString()) }

        // Initialiser les touches du clavier
        val keyShift = keyboardView.findViewById<Button>(R.id.key_shift)
        keyShift.setOnClickListener {
            isCaps = !isCaps
            updateKeys(keyboardView)
        }

        val keys = listOf(
            R.id.key_a, R.id.key_b, R.id.key_c, R.id.key_d, R.id.key_e, R.id.key_f, R.id.key_g,
            R.id.key_h, R.id.key_i, R.id.key_j, R.id.key_k, R.id.key_l, R.id.key_m, R.id.key_n,
            R.id.key_o, R.id.key_p, R.id.key_q, R.id.key_r, R.id.key_s, R.id.key_t, R.id.key_u,
            R.id.key_v, R.id.key_w, R.id.key_x, R.id.key_y, R.id.key_z
        )

        for (keyId in keys) {
            val key = keyboardView.findViewById<Button>(keyId)
            key.setOnClickListener {
                val text = if (isCaps) key.text.toString().uppercase(Locale.ROOT) else key.text.toString()
                    .lowercase(
                        Locale.ROOT
                    )
                currentInputConnection.commitText(text, 1)
                updateSuggestions()
            }
        }

        val keyBackspace = keyboardView.findViewById<Button>(R.id.key_backspace)
        keyBackspace.setOnClickListener {
            currentInputConnection.deleteSurroundingText(1, 0)
        }

        val keySpace = keyboardView.findViewById<Button>(R.id.key_space)
        keySpace.setOnClickListener {
            currentInputConnection.commitText(" ", 1)
        }

        val keyEnter = keyboardView.findViewById<Button>(R.id.key_enter)
        keyEnter.setOnClickListener {
            currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_DONE)
        }

        val keyEmoji = keyboardView.findViewById<Button>(R.id.key_emoji)
        keyEmoji.setOnClickListener {
            currentInputConnection.commitText("😊", 1)
        }

        return keyboardView
    }

    private fun updateKeys(keyboardView: View) {
        val keys = listOf(
            R.id.key_a, R.id.key_b, R.id.key_c, R.id.key_d, R.id.key_e, R.id.key_f, R.id.key_g,
            R.id.key_h, R.id.key_i, R.id.key_j, R.id.key_k, R.id.key_l, R.id.key_m, R.id.key_n,
            R.id.key_o, R.id.key_p, R.id.key_q, R.id.key_r, R.id.key_s, R.id.key_t, R.id.key_u,
            R.id.key_v, R.id.key_w, R.id.key_x, R.id.key_y, R.id.key_z
        )

        for (keyId in keys) {
            val key = keyboardView.findViewById<Button>(keyId)
            key.text = if (isCaps) key.text.toString().toUpperCase() else key.text.toString().toLowerCase()
        }
    }

    private fun commitSuggestion(suggestion: String) {
        currentInputConnection.commitText(suggestion, 1)
    }

    private fun updateSuggestions() {
        // Mettez à jour les suggestions de texte ici
        suggestion1.text = "Hello"
        suggestion2.text = "World"
        suggestion3.text = "!"
    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        // Initialisez votre clavier ici si nécessaire
    }
}
