package com.eldarovich99.remote_assistant.presentation.view.call

import android.widget.Toast
import com.eldarovich99.remote_assistant.data.error_handling.Result
import com.eldarovich99.remote_assistant.domain.files.FileInteractor
import com.eldarovich99.remote_assistant.utils.FileWriter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CallPresenter @Inject constructor(val view: CallFragment, val interactor: FileInteractor, val fileWriter: FileWriter) {
    fun downloadFile(link: String){
        CoroutineScope(Dispatchers.Main).launch{
            val downloadResult = interactor.downloadFile(link)
            if (downloadResult is Result.Success){
                Toast.makeText(view.context, "File downloaded", Toast.LENGTH_SHORT).show()
                val writeSuccess = fileWriter.writeResponseBodyToDisk(downloadResult.data)
                if (writeSuccess)
                    Toast.makeText(view.context, "File saved", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(view.context, "Failed to save the tile", Toast.LENGTH_SHORT).show()
            }
        }
    }
}