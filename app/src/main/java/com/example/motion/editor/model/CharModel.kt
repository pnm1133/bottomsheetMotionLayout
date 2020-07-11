package com.example.motion.editor.model

import android.text.Editable
import android.text.SpannableStringBuilder
import androidx.lifecycle.MutableLiveData
import com.example.motion.editor.EditorModel
import com.example.motion.editor.utils.appendList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

interface CharModel : EditorModel {

    var chars: MutableLiveData<CharSequence?>

    var userModels: MutableList<UserModel>?

    var locationModels: MutableList<LocationModel>?

    suspend fun appendWith(userModel: UserModel): Boolean

    suspend fun appendLocation(locationModel: LocationModel): Boolean

    suspend fun appendCharModel(charModel: CharModel): CharModel?

    fun setEditableChange(char: Editable?)

    fun getEditable() = chars.value

    fun toggleFocusing() = kotlin.run { isFocusing = !isFocusing }

}

data class CharModelBase(
    override var isFocusing: Boolean = false,
    override var chars: MutableLiveData<CharSequence?>,
    override val id: UUID = UUID.randomUUID(),
    override var userModels: MutableList<UserModel>? = null,
    override var locationModels: MutableList<LocationModel>? = null) : CharModel {

    override suspend fun appendWith(userModel: UserModel): Boolean {
        return false
    }

    override suspend fun appendLocation(locationModel: LocationModel): Boolean {
        return false
    }

    override fun setEditableChange(char: Editable?) {
        char?.let { chars.postValue(it) }
    }

    override suspend fun appendCharModel(charModel: CharModel): CharModel? =
        withContext(Dispatchers.Default) {
            kotlin.runCatching {
                val newCar = SpannableStringBuilder(chars.value).append("\n").append(charModel.chars.value)
                val newUserModels = appendList(userModels, charModel.userModels)
                val newLocations = appendList(locationModels, charModel.locationModels)
                CharModelBase(false,MutableLiveData(newCar), UUID.randomUUID(), newUserModels, newLocations)
            }.getOrNull()
        }

    companion object {
        fun createTest(char : CharSequence) = CharModelBase(false,MutableLiveData(char))
    }
}