package com.zplay.willheror

import android.content.Context
import android.util.Log
import com.orhanobut.hawk.Hawk
import com.zplay.willheror.MainClasHere.Companion.C1
import com.zplay.willheror.MainClasHere.Companion.D1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class SomeAsyncJshit (val context: Context) {
    private var jsoup: String = ""

    suspend fun coTask(): String {

        val nameParameter: String? = Hawk.get(C1)
        val appLinkParameter: String? = Hawk.get(D1)


        val taskName =
            "${MainClasHere.linkFilterPart1}${MainClasHere.linkFilterPart2}${MainClasHere.odone}$nameParameter"
        val taskLink =
            "${MainClasHere.linkFilterPart1}${MainClasHere.linkFilterPart2}${MainClasHere.odone}$appLinkParameter"

        withContext(Dispatchers.IO) {
            //changed logical null to string null
            if (nameParameter != "null") {
                getDocSecretKey(taskName)
//                Log.d("Check1C", taskName)
            } else {
                getDocSecretKey(taskLink)
//                Log.d("Check1C", taskLink)
            }
        }
        return jsoup
    }

    private fun getDocSecretKey(link: String) {
        val text = Jsoup.connect(link).get().text()
        Log.d("Text from jsoup", text)
        jsoup = text
    }
}