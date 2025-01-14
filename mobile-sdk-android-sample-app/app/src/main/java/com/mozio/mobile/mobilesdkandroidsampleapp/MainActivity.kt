package com.mozio.mobile.mobilesdkandroidsampleapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mozio.mobile.mobilesdkandroidsampleapp.ui.theme.MobileSDKAndroidSampleAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSDKAndroidSampleAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {
                            Intent(this@MainActivity, SearchRidesActivity::class.java).run {
                                startActivity(this)
                            }
                        }
                    ) {
                        Text(getString(R.string.search_rides_button))
                    }
                }
            }
        }
    }

}