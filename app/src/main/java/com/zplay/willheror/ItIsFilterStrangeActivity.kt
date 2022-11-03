package com.zplay.willheror

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zplay.willheror.MainClasHere.Companion.jsoupCheck
import com.zplay.willheror.justcapy.GameActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ItIsFilterStrangeActivity : AppCompatActivity() {
    lateinit var jsoup: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_it_is_filter_strange)
        jsoup = ""
        val asyncJs = SomeAsyncJshit(applicationContext)

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = asyncJs.coTask()
        }

        runBlocking {
            job.join()
            if (jsoup == jsoupCheck) {
                Intent(applicationContext, GameActivity::class.java).also { startActivity(it) }
            } else {
                Intent(applicationContext, WeeebVievActivity::class.java).also { startActivity(it) }
            }
            finish()
        }
    }
}