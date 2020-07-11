package com.example.motion.editor.view.chilld.charview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.liveData
import com.example.motion.AndroidLiveCurrentLifecycle
import com.example.motion.R
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.model.LocationModel
import com.example.motion.editor.model.UserModel
import com.example.motion.editor.view.EditorListener
import com.example.motion.editor.view.chilld.charview.CharView
import com.example.motion.utils.JobUtil
import com.example.motion.utils.logE
import kotlinx.android.synthetic.main.char_view_group.view.*
import kotlinx.coroutines.Dispatchers
import java.util.*

class CharViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, def: Int = 0) : ConstraintLayout(context, attrs, def), CharView {

    var editorCallback: EditorListener? = null

    override var charModel: CharModel? = null

    override var position: Int? = null

    override val isFocusing: MutableLiveData<Boolean> by lazy {  MutableLiveData(false) }

    override var id: UUID? = charModel?.id


    init {
        View.inflate(context, R.layout.char_view_group, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        _edtEditorId?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                charModel?.setEditableChange(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        setOnClickListener {
            isFocusing.value = charModel?.isFocusing ?: false
        }

        AndroidLiveCurrentLifecycle.lifecycleOwner?.get()?.let {
            isFocusing.observe(it, androidx.lifecycle.Observer {
                JobUtil.doJob(Dispatchers.Main){
                    onFocusingState(it)
                    charModel?.isFocusing = it
                }
            })
        }
    }

    override fun bind(charModel: CharModel?) {
        JobUtil.doJob(Dispatchers.Main){
            charModel?.let {
                this@CharViewGroup.charModel = charModel
                setContentChar(it.getEditable())
                _edtEditorId?.isEnabled = it.isFocusing
            }
        }
    }

    override fun setContentChar(charSequence: CharSequence?) {
        _edtEditorId?.setText(charSequence)
    }

    override fun setWithUser(vararg userModel: UserModel?) {

    }

    override fun setLocation(vararg userModel: LocationModel?) {

    }

    override suspend fun onFocusingState(turnOn: Boolean): Boolean {
        if (turnOn) {
            _focusView?.focusedBg()
            _edtEditorId?.isEnabled = true
        } else {
            _focusView?.unfocusedBg()
            _edtEditorId?.isEnabled = false
        }
        return false
    }
}