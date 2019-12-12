package org.verzhbitski.placeholderapiclient.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.verzhbitski.placeholderapiclient.R

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
