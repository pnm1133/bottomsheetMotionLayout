package com.example.motion.editor.view.editor

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.text.toSpannable
import com.example.motion.R
import com.example.motion.editor.EditorModel
import com.example.motion.editor.controller.EditorController
import com.example.motion.editor.model.CharModel
import com.example.motion.editor.model.ImageModel
import com.example.motion.editor.recyler.adapter.EditorViewBaseAdapter
import com.example.motion.editor.utils.ofType
import com.example.motion.editor.view.EditorListener
import com.example.motion.editor.view.chilld.charview.CharViewGroup
import com.example.motion.editor.view.chilld.EditorViewBase
import com.example.motion.editor.view.chilld.charview.CharView
import com.example.motion.editor.view.chilld.image.ImageViewGroup
import com.example.motion.utils.JobUtil
import com.example.motion.utils.logE
import com.example.motion.view.BottomSheetMotionFr
import kotlinx.android.synthetic.main.editor_view_group.view.*
import kotlinx.coroutines.Dispatchers
import java.util.*

class EditorViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, def: Int = 0) : FrameLayout(context, attrs, def), EditorView, EditorListener {

    private val childs: MutableList<EditorViewBase>?
        get() = getListChild()

    var editorViewBaseAdapter: EditorViewBaseAdapter? = null

    override val models: MutableList<EditorModel> = mutableListOf()

    var editFr = BottomSheetMotionFr()

    private val editorController = EditorController(models)

    init {
        View.inflate(context, R.layout.editor_view_group, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.initLayout()
        _bottomSheetEditorId.inflateFr(editFr)
    }

    override suspend fun setContentChar(positon: Int, char: CharModel): Boolean {
        return runCatching {
            val child = getChildAt(positon)
            if (child is CharViewGroup) {
                child.setContentChar(char.chars.value)
                char.locationModels?.forEach { child.setLocation(it) }
                char.userModels?.forEach { child.setWithUser(it) }
            }
        }.isSuccess
    }

    override suspend fun setContentImage(positon: Int, imageModel: ImageModel): Boolean {
        return runCatching {
            val child = getChildAt(positon)
            if (child is ImageViewGroup) {
                imageModel.path?.let { child.setImageResource(it) }
                    ?: imageModel.bitmap?.let { child.setImageResource(it) }
            }
        }.isSuccess
    }

    override suspend fun swapPosition(firstModel: EditorModel, secondModel: EditorModel): Boolean {
        return kotlin.runCatching {
        }.isSuccess
    }

    override suspend fun getModels(): List<EditorModel> = models

    override suspend fun newImageView(vararg imageModel: ImageModel) {
        if (editorController.addNewModel(imageModel.toMutableList())) {
            applyChange()
        }
    }

    override suspend fun newCharView(vararg charModel: CharModel) {
        if (editorController.addNewModel(charModel.toMutableList())) {
            applyChange()
        }
    }

    override suspend fun currentFocusView(): EditorViewBase? =
        childs?.find { it.isFocusing.value ?: false }

    override fun applyChange() {
        "applyChange".logE()
        // add last
        editorViewBaseAdapter?.addLastModel()
        //add positon

        // change all
    }

    override fun showBottomHalfExpand() {
        _bottomSheetEditorId?.expand()
    }


    private fun getListChild(): MutableList<EditorViewBase>? {
        return kotlin.runCatching {
            val child = mutableListOf<EditorViewBase>()
            for (i in 0 until childCount) child.add(getChildAt(i) as EditorViewBase)
            child
        }.getOrNull()
    }

    override fun onClickItem(viewBase: EditorViewBase, position: Int) {
        JobUtil.doJob(Dispatchers.Main) {
            handleFocusing(viewBase)
        }
    }

    private fun handleFocusing(viewBase: EditorViewBase) {
        models.filter { viewBase.id != it.id }.let {
            it.forEach { model ->
                model.isFocusing = false
            }
        }
    }
}