package com.example.motion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.motion.editor.model.CharModelBase
import com.example.motion.editor.model.ImageModelBase
import com.example.motion.editor.view.EditorListener
import com.example.motion.editor.view.chilld.EditorViewBase
import com.example.motion.utils.JobUtil
import com.example.motion.utils.logE
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.editor_view_group.*
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(_toolBarIds)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*JobUtil.doJob(Dispatchers.Main){
            _editor.newCharView()
            _editor.newCharView(CharModelBase.createTest("Vui long nhap title"))
            _editor.newImageView(ImageModelBase())
            _editor.newImageView(ImageModelBase())
            _editor.newCharView(CharModelBase.createTest("Vui long nhap title"))
            _editor.newImageView(ImageModelBase())
            _editor.newImageView(ImageModelBase())
            _editor.newCharView(CharModelBase.createTest("Vui long nhap title"))
            _editor.newCharView(CharModelBase.createTest("Vui long nhap title"))
            _editor.newCharView(CharModelBase.createTest("Vui long nhap title"))
            _editor.newImageView(ImageModelBase())
            _editor.newImageView(ImageModelBase())
            _editor.newImageView(ImageModelBase())
            _editor.newCharView(CharModelBase.createTest("Vui long nhap title"))
            _editor.newCharView(CharModelBase.createTest("Vui long nhap title"))
        }*/

        AndroidLiveCurrentLifecycle.registerActionHandler(this)
    }

    override fun onDestroy() {
        clearFindViewByIdCache()
        super.onDestroy()
    }
}
