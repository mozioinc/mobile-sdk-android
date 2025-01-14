# Mozio Android SDK Integration Guide

Welcome to the **Mozio Android SDK**!
This repository includes a sample app to help you quickly integrate and test the SDK in your Android projects. Follow this guide to set up the SDK, customize it to your needs, and begin utilizing its powerful features.

---

## 📦 Setup the SDK

### 1. Add Maven Repositories
In your `settings.gradle.kts` file, include the Mozio and JitPack repositories:

```kotlin
maven("https://raw.githubusercontent.com/mozioinc/mobile-sdk-android/main/maven/") {
    content {
        includeGroupByRegex("com.mozio.mobile.*")
    }
}
maven("https://jitpack.io") {
    content {
        includeModule("com.github.jeziellago", "compose-markdown")
        includeModule("com.github.jeziellago", "Markwon")
    }
}
```

### 2. Add Dependencies
In your `libs.versions.toml`, specify the Mozio SDK version:

```toml
mozio-mobile-sdk = "0.1.0"
mozio-mobile-sdk-ui = { group = "com.mozio.mobile.android", name = "sdk-ui", version.ref = "mozio-mobile-sdk" }
```

Then, include the dependency in your app's `build.gradle.kts` file:

```kotlin
implementation(libs.mozio.mobile.sdk.ui)
```

### 3. Add Google Maps API Key

If your application doesn't use Google Maps already, include the following metadata in your `AndroidManifest.xml` file with your Google Maps API key:

```xml
<meta-data
android:name="com.google.android.geo.API_KEY"
android:value="YOUR_GOOGLE_MAPS_API_KEY" />
```

Replace `YOUR_GOOGLE_MAPS_API_KEY` with your actual Google Maps API key.

---

## 🛠 Initialize the SDK
In your `Application` class, inside the `onCreate()` method, initialize the Mozio SDK with the required parameters like:

```kotlin
MozioSDK.init(
    application = this,
    environment = Environment.STAGING,
    apiKey = ApiKey("YOUR_MOZIO_API_KEY")
)
```

Use `Environment.STAGING` or `Environment.PRODUCTION` based on your build variant configuration.
Replace `YOUR_MOZIO_API_KEY` with the appropriate apiKey provided by Mozio.

---

## 🚀 Using the SDK
To utilize the SDK's UI components, you can integrate the search rides view directly into your app using this compose view:

```kotlin
MozioSDK.SearchRidesScreen()
```

This will open the Mozio search rides flow, providing users with a seamless booking experience.

---

## 🎨 Customizing the SDK

### SDK initialization

#### Overview
`MozioSDK.init()` is the method used to initialize the Mozio SDK. It sets up the required environment, API key, logging configurations, and other settings needed for operation.

#### Function Signature
```kotlin
fun init(
    application: Application,
    environment: Environment,
    apiKey: ApiKey,
    shouldDisplayResultsWhileLoading: Boolean = true,
    termsOfServiceUrl: TermsOfServiceUrl = TermsOfServiceUrl("https://www.mozio.com/en-us/terms-of-service"),
    logSeverity: LogSeverity = LogSeverity.DEBUG,
    ridesFilterAndSorter: RidesFilterAndSorter = provideRidesFilterAndSorter(),
    lightColors: ColorsConfig = defaultLightColorsConfig,
    darkColors: ColorsConfig = defaultDarkColorsConfig
)
```

#### Parameters
- **`application`** *(Application)*: The Android application instance.
- **`environment`** *(Environment)*: Defines whether to use the `STAGING` or `PRODUCTION` environment.
- **`apiKey`** *(ApiKey)*: The API key required to authenticate with the Mozio API.
- **`shouldDisplayResultsWhileLoading`** *(Boolean, optional)*: Determines whether ride results should start being displayed while search is still running. Defaults to `true`.
- **`termsOfServiceUrl`** *(TermsOfServiceUrl, optional)*: The URL linking to the terms of service. Defaults to Mozio's official terms.
- **`logSeverity`** *(LogSeverity, optional)*: Defines the severity level for logs. Defaults to `DEBUG`.
- **`ridesFilterAndSorter`** *(RidesFilterAndSorter, optional)*: A function that filters and sorts ride results before displaying them. Defaults to no sort/filter logic applied.
- **`lightColors`** *(ColorsConfig, optional)*: Defines the light theme colors for the SDK. Defaults to Mozio light colors.
- **`darkColors`** *(ColorsConfig, optional)*: Defines the dark theme colors for the SDK. Defaults to Mozio dark colors.

#### Environment Options
```kotlin
enum class Environment {
    STAGING, PRODUCTION
}
```

#### API Key
```kotlin
data class ApiKey(
    val apiKey: String
)
```

#### Log Severity Levels
```kotlin
enum class LogSeverity {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ASSERT
}
```

#### Ride Filtering and Sorting
```kotlin
data class RidesFilterAndSorter(
    val filterAndSorter: (List<RideResult>) -> List<RideResult>
)
```

#### Theme Configuration
The `ColorsConfig` interface defines the color scheme used in the SDK:
```kotlin
interface ColorsConfig {
    val primary: Color // The primary color of the app, usually the one that identifies the brand. For main CTAs and notifications
    val secondary: Color // The secondary brand color or a contrasting color that matches the primary color. Used to mark selections, tool tips, helpers etc
    val foreground: Color // The foreground color mainly used for text
    val background: Color // The primary color used as a background color
    val route: Color // The color used to highlight the current route or waypoints on a map
    val information: Color // The primary color used when displaying informative elements
    val error: Color // The color used when displaying or highlighting error elements
    val success: Color // The color used when displaying or highlighting success elements
    val neutral1: Color // The neutral color used when highlighting light gray UI elements
    val neutral2: Color // The neutral color used when highlighting medium gray UI elements
    val neutral3: Color // The neutral color used when highlighting dark gray UI elements
}
```

#### Usage Examples

##### Basic Initialization
```kotlin
init(
    application = this,
    environment = Environment.PRODUCTION,
    apiKey = ApiKey("my-api-key")
)
```

##### Customizing Logging and Theme
```kotlin
init(
    application = this,
    environment = Environment.STAGING,
    apiKey = ApiKey("my-staging-api-key"),
    logSeverity = LogSeverity.VERBOSE,
    lightColors = customLightColors,
    darkColors = customDarkColors
)
```

##### Applying Custom Ride Filtering and Sorting
```kotlin
init(
    application = myApplication,
    environment = Environment.PRODUCTION,
    apiKey = ApiKey("my-api-key"),
    ridesFilterAndSorter = RidesFilterAndSorter { rides ->
        rides.sortedBy { it.price.totalCost.toDouble() }
    }
)
```

### SearchRidesScreen

#### Overview
`SearchRidesScreen` is a composable function designed to allow users to search and book a ride. Depending on the UI/UX requirements, it provides options for configuring a back icon and handling booking events.

#### Function Signature
```kotlin
@Composable
fun SearchRidesScreen(
    modifier: Modifier = Modifier,
    backIconConfiguration: BackIconConfiguration = BackIconConfiguration.NoIcon,
    onBookingEvent: (bookingEvent: BookingEvent) -> Unit = {}
)
```

#### Parameters
- **`modifier`** *(Modifier, optional)*: Used to apply layout and styling configurations.
- **`backIconConfiguration`** *(BackIconConfiguration, optional)*: Defines whether and how a back icon is displayed. Defaults to `BackIconConfiguration.NoIcon`.
- **`onBookingEvent`** *(Function, optional)*: A lambda function triggered during specific booking events. It takes a `BookingEvent` as input and provides a way to react to booking flow changes.

#### Back Icon Configuration
The `backIconConfiguration` parameter allows for different back icon behaviors using the `BackIconConfiguration` sealed interface:

```kotlin
sealed interface BackIconConfiguration {
    object NoIcon : BackIconConfiguration // No back icon is shown

    data class WithBackIcon(
        val onBackPress: () -> Unit // Displays a back arrow icon, triggers `onBackPress` when clicked
    ) : BackIconConfiguration

    data class WithCloseIcon(
        val onBackPress: () -> Unit // Displays an 'X' close icon, triggers `onBackPress` when clicked
    ) : BackIconConfiguration
}
```

#### Booking Events
The `onBookingEvent` callback allows handling booking-related events. It reacts to `BookingEvent`, which is a sealed interface representing different stages of the booking process:

```kotlin
sealed interface BookingEvent {
    object BookingComplete : BookingEvent // Triggered when the booking is completed
    object BookingConfirmationClosed : BookingEvent // Triggered when the user closes the booking confirmation screen shown after the booking is complete
}
```

#### Usage Examples
##### 1. Basic Usage Without Back Icon
```kotlin
SearchRidesScreen()
```

##### 2. Adding a Back Icon with Custom Behavior
```kotlin
SearchRidesScreen(
    backIconConfiguration = BackIconConfiguration.WithBackIcon(
        onBackPress = { /* Handle back press */ }
    )
)
```

##### 3. Handling Booking Events
```kotlin
SearchRidesScreen(
    onBookingEvent = { event ->
        when (event) {
            BookingEvent.BookingComplete -> println("Booking completed!")
            BookingEvent.BookingConfirmationClosed -> println("Booking confirmation closed")
        }
    }
)
```

Depending on your UI/UX, you might decide to show a back icon (or close icon) and close the booking flow when this icon is pressed, or you can use `onBookingEvent()` to react to `BookingEvent.BookingConfirmationClosed` and close the booking flow when the ride is booked and the user closes the booking confirmation screen.

---

## 🐞 Reporting Issues
If you encounter any issues or have questions, please open an issue in the [Mozio Android SDK repository](https://github.com/mozioinc/mobile-sdk-android).