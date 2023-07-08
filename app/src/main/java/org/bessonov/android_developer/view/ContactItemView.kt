package org.bessonov.android_developer.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.bessonov.android_developer.R
import org.bessonov.android_developer.databinding.ContactItemBinding

class ContactItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: ContactItemBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.contact_item, this, true)
        binding = ContactItemBinding.bind(this)
        initializeAttributes(attrs = attrs, defStyleAttr = defStyleAttr, defStyleRes = defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ContactItemView,
            defStyleAttr,
            defStyleRes
        )

        val icon = typedArray.getDrawable(R.styleable.ContactItemView_icon)
        setIcon(icon = icon)

        val title = typedArray.getString(R.styleable.ContactItemView_title)
        setTitle(title = title)

        val value = typedArray.getString(R.styleable.ContactItemView_value)
        setValue(value = value)

        typedArray.recycle()
    }

    private fun setIcon(icon: Drawable?) {
        binding.iconIv.setImageDrawable(icon)
    }

    private fun setTitle(title: String?) {
        binding.titleTv.text = title
    }

    fun setValue(value: String?) {
        binding.valueTv.text = value
    }
}