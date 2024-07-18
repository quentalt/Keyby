import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import com.quentin.keyby.R

class MyKeyboardService : InputMethodService() {



    override fun onCreateInputView(): View {
        val keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null)

        val keyEmoji = keyboardView.findViewById<Button>(R.id.key_emoji)
        keyEmoji.setOnClickListener {
            currentInputConnection.commitText("😊", 1)
        }

        val keyA = keyboardView.findViewById<Button>(R.id.key_a)
        keyA.setOnClickListener {
            currentInputConnection.commitText("A", 1)
        }
        return keyboardView
    }
        // Ajoutez des écouteurs pour les autres touches

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        // Initialisez votre clavier ici
    }
}
