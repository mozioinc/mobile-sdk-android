package com.mozio.mobile.mobilesdkandroidsampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mozio.mobile.android.sdk.ui.MozioSDK
import com.mozio.mobile.android.sdk.ui.config.BackIconConfiguration
import com.mozio.mobile.mobilesdkandroidsampleapp.ui.theme.MobileSDKAndroidSampleAppTheme

class SearchRidesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSDKAndroidSampleAppTheme {
                MozioSDK.SearchRidesScreen(backIconConfiguration = BackIconConfiguration.WithBackIcon(
                    onBackPress = {
                        finish()
                    }
                ))
            }
        }
    }

}