package com.cupcake.ui.utill

import android.provider.SyncStateContract
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.children
import com.cupcake.ui.R
import com.cupcake.ui.databinding.ItemChipSkillBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.regex.Pattern

fun ChipGroup.attachTo(editText: EditText, chipChangedCallback: (List<String>) -> Unit) {
    editText.apply {
        onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (text.toString() == " ") {
                    text.clear()
                }
            } else {
                if (text.isNullOrEmpty() && childCount > 0) {
                    setText(" ")
                }
            }
        }

        fun getSelection(): List<String> = mutableListOf<String>().apply {
            for (view in children) {
                add(view.findViewById<Chip>(R.id.chipItem).text.toString())
            }
        }

        fun checkHashtag(hashtagInput: String) {

            addTag(hashtagInput) {
                chipChangedCallback(getSelection())
            }
            text.clear()
            chipChangedCallback(getSelection())

            if (Pattern.matches(SyncStateContract.Constants.ACCOUNT_NAME, hashtagInput)) {
                addTag(hashtagInput) {
                    chipChangedCallback(getSelection())
                }
                text.clear()
                chipChangedCallback(getSelection())
            }
        }

        setOnKeyListener { _, actionId, event ->
            if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                if (text.isNotEmpty() || childCount <= 0) return@setOnKeyListener false
                val lastChip = getChildAt(childCount - 1) as LinearLayout
                editText.append(lastChip.findViewById<Chip>(R.id.chipItem).text)
                removeView(lastChip)
                chipChangedCallback(getSelection())
            } else if (
                actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event.action == KeyEvent.ACTION_DOWN
                && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                checkHashtag(text.toString())
                return@setOnKeyListener true
            }
            actionId == KeyEvent.KEYCODE_ENTER
        }

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                val text = editable.toString()
                val length = text.length

                if (text == " ") {
                    editable.delete(length - 1, length)
                    return
                }
                if (text.isNotEmpty() && text.endsWith(" ")) {
                    val hashtagInput = text.removeSuffix(" ")
                    checkHashtag(hashtagInput)
                }
            }

        })

    }
}

private fun ChipGroup.addTag(tag: String, onRemoved: (View) -> Unit = {}) {
    addView(ItemChipSkillBinding.inflate(LayoutInflater.from(context)).also { itemUi ->
        itemUi.root.findViewById<Chip>(R.id.chipItem).apply {
            text = tag
            setOnClickListener {
                this@addTag.removeView(itemUi.root);
                onRemoved(this)
            }
        }
    }.root)
}

fun View.makeVisible(){
    this.visibility = View.VISIBLE
}
fun View.makeGone(){
    this.visibility=View.GONE
}