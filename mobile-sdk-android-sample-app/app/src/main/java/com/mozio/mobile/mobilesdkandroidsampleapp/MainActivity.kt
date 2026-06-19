package com.mozio.mobile.mobilesdkandroidsampleapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = getString(R.string.consumer_app_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                    Text(
                        text = getString(R.string.consumer_app_subtitle),
                        fontSize = 15.sp,
                        color = Color(0xFF637280)
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Button(
                        onClick = {
                            Intent(this@MainActivity, SearchRidesActivity::class.java).run {
                                startActivity(this)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mozioYellow,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(getString(R.string.search_rides_button), fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = {
                            showFindReservationBottomSheet = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mozioYellow,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(getString(R.string.find_reservation_button), fontWeight = FontWeight.Bold)
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