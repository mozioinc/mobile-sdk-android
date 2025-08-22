package com.mozio.mobile.mobilesdkandroidsampleapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mozio.mobile.android.sdk.ui.MozioSDK
import com.mozio.mobile.mobilesdkandroidsampleapp.ui.theme.MobileSDKAndroidSampleAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showFindReservationBottomSheet by remember {
                mutableStateOf(false)
            }

            MobileSDKAndroidSampleAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            Intent(this@MainActivity, SearchRidesActivity::class.java).run {
                                startActivity(this)
                            }
                        }
                    ) {
                        Text(getString(R.string.search_rides_button))
                    }
                    Button(
                        onClick = {
                            showFindReservationBottomSheet = true
                        }
                    ) {
                        Text(getString(R.string.find_reservation_button))
                    }
                }

                if (showFindReservationBottomSheet) {
                    MozioSDK.FindReservationBottomSheet(
                        onXClick = {
                            showFindReservationBottomSheet = false
                        }
                    )
                }
            }
        }
    }

}