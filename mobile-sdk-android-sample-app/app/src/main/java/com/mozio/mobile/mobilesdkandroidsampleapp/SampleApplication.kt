package com.mozio.mobile.mobilesdkandroidsampleapp

import android.app.Application
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import com.mozio.mobile.android.sdk.ui.MozioSDK
import com.mozio.mobile.sdk.api.config.ApiKey
import com.mozio.mobile.sdk.api.config.LogSeverity
import com.mozio.mobile.sdk.api.config.environment.Environment

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // The SDK logs through Kermit but doesn't change its settings: the app sets the minimum severity
        Logger.setMinSeverity(if (BuildConfig.DEBUG) Severity.Debug else Severity.Info)
        MozioSDK.init(
            application = this,
            environment = Environment.TESTING,
            apiKey = ApiKey("YOUR_MOZIO_API_KEY"),
            logSeverity = if (BuildConfig.DEBUG) LogSeverity.VERBOSE else LogSeverity.INFO,
            lightColors = sampleLightColorsConfig,
        )
    }

}