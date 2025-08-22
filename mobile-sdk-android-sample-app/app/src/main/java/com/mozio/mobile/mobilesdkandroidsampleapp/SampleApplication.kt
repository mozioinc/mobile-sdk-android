package com.mozio.mobile.mobilesdkandroidsampleapp

import android.app.Application
import com.mozio.mobile.android.sdk.ui.MozioSDK
import com.mozio.mobile.sdk.api.config.ApiKey
import com.mozio.mobile.sdk.api.config.environment.Environment

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MozioSDK.init(
            application = this,
            environment = Environment.TESTING,
            apiKey = ApiKey("YOUR_MOZIO_API_KEY")
        )
    }

}