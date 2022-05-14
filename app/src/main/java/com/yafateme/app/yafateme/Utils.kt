package com.yafateme.app.yafateme
import android.content.Context
import android.os.Environment
import androidx.core.content.ContextCompat
import java.io.File
import java.util.*




/**
 * Created by amitshekhar on 13/11/17.
 */
object Utils {
    fun getRootDirPath(context: Context): String {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file: File = ContextCompat.getExternalFilesDirs(
                context.applicationContext,
                null
            ).get(0)
            file.absolutePath
        } else {
            context.applicationContext.filesDir.absolutePath
        }
    }

    fun getProgressDisplayLine(currentBytes: Long, totalBytes: Long): String {
        return com.yafateme.app.yafateme.Utils.getBytesToMBString(currentBytes) + "/" + com.yafateme.app.yafateme.Utils.getBytesToMBString(
            totalBytes
        )
    }

    private fun getBytesToMBString(bytes: Long): String {
        return String.format(Locale.ENGLISH, "%.2fMb", bytes / (1024.00 * 1024.00))
    }
}