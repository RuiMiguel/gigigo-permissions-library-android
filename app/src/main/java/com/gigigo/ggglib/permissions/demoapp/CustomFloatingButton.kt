package com.gigigo.ggglib.permissions.demoapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.gigigo.ggglib.permissions.demoapp.CustomFloatingButton.Position.LEFT
import com.gigigo.ggglib.permissions.demoapp.CustomFloatingButton.Position.RIGHT
import kotlinx.android.synthetic.main.layout_custom_floating_button.view.*

class CustomFloatingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var text: String? = null
    private var position: Position = LEFT
    private var image: Int = -1

    private enum class Position {
        LEFT,
        RIGHT;
    }

    init {
        val a =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CustomFloatingButton, 0, 0)
        try {
            text = a.getString(R.styleable.CustomFloatingButton_button_text)
            val pos = a.getInt(R.styleable.CustomFloatingButton_button_text_position, 0)
            position = if (pos == 0) LEFT else RIGHT
            image = a.getResourceId(R.styleable.CustomFloatingButton_button_image, -1)
        } finally {
            a.recycle()
        }

        initUI()
    }

    private fun initUI() {
        initView()
        bindListeners()
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_custom_floating_button, this, true)

        button_text.text = text

        val textParams = button_text.layoutParams as ConstraintLayout.LayoutParams
        val buttonParams = button.layoutParams as ConstraintLayout.LayoutParams

        when (position) {
            LEFT -> {
                textParams.endToStart = button.id
                buttonParams.endToEnd = button_layout.id
                textParams.marginStart = 0
                buttonParams.startToStart = -1
                textParams.startToEnd = -1
            }
            RIGHT -> {
                buttonParams.startToStart = button_layout.id
                textParams.startToEnd = button.id
                textParams.marginEnd = 0
                buttonParams.endToEnd = -1
                textParams.endToStart = -1
            }
        }

        when (image) {
            -1 -> button.hide()
            else -> button.setImageResource(image)
        }
    }

    private fun bindListeners() {

    }
}