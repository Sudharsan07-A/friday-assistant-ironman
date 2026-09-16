# FRIDAY Assistant - Build & Development Guide

## Building the APK

### Prerequisites
- Android Studio (latest version)
- JDK 11 or higher
- Android SDK (API 26+)
- Git

### Build Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Sudharsan07-A/friday-assistant-ironman.git
   cd friday-assistant-ironman
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory
   - Wait for Gradle sync to complete

3. **Configure Local Properties**
   Create `local.properties` file in project root:
   ```properties
   sdk.dir=/path/to/your/android/sdk
   ```

4. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```
   APK will be located at: `app/build/outputs/apk/debug/app-debug.apk`

5. **Build Release APK**
   ```bash
   ./gradlew assembleRelease
   ```
   APK will be located at: `app/build/outputs/apk/release/app-release.apk`

### Running on Emulator or Device

#### Using Android Studio
1. Connect device or start emulator
2. Click "Run" button (green play icon)
3. Select target device
4. App will launch automatically

#### Using Command Line
```bash
# Install debug APK
./gradlew installDebug

# Launch app
adb shell am start -n com.ironman.friday/.ui.activity.MainActivity
```

## Project Structure

```
friday-assistant-ironman/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ironman/friday/
│   │   │   │   ├── ui/
│   │   │   │   │   ├── activity/         # Activities (MainActivity, etc.)
│   │   │   │   │   └── adapter/          # RecyclerView Adapters
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/            # Data models (Command, etc.)
│   │   │   │   │   └── database/         # Room database classes
│   │   │   │   ├── service/              # Background services
│   │   │   │   ├── network/              # API client and models
│   │   │   │   ├── utils/                # Utility classes
│   │   │   │   └── FridayAssistantApp.kt # Application class
│   │   │   ├── res/
│   │   │   │   ├── layout/               # XML layouts
│   │   │   │   ├── drawable/             # Drawable resources
│   │   │   │   ├── values/               # Colors, strings, styles
│   │   │   │   └── menu/                 # Menu resources
│   │   │   └── AndroidManifest.xml
│   │   └── test/                         # Unit tests
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── README.md
```

## Configuration

### API Keys
Add your API keys in `local.properties` or update them in the Settings activity within the app.

### Theme Customization
Modify colors in `app/src/main/res/values/colors.xml`:
- `gold_primary` - Primary branding color
- `red_secondary` - Secondary action color
- `arc_reactor_blue` - Accent color
- `bg_dark_primary` - Main background

## Development Guidelines

### Code Style
- Use Kotlin for new code
- Follow Google's Kotlin style guide
- Use meaningful variable and function names
- Add comments for complex logic

### Git Workflow
1. Create feature branch: `git checkout -b feature/YourFeature`
2. Make changes and commit: `git commit -m 'Add YourFeature'`
3. Push to branch: `git push origin feature/YourFeature`
4. Create Pull Request on GitHub

### Testing
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Troubleshooting

### Gradle Sync Issues
```bash
./gradlew clean
./gradlew build
```

### Missing SDK/API Levels
- Open Android Studio SDK Manager
- Install API Level 26+ and latest build tools

### Permission Denied on Linux/Mac
```bash
chmod +x gradlew
```

### APK Installation Failed
- Ensure device has sufficient storage
- Uninstall previous version: `adb uninstall com.ironman.friday`
- Try installing again

## Continuous Integration

The project is set up for CI/CD. GitHub Actions will automatically:
1. Build the project on every push
2. Run tests
3. Generate APK artifacts

## Performance Optimization

- ProGuard/R8 is enabled for Release builds
- Unused code is automatically removed
- Optimize APK size in Release mode

## Testing the App

### Basic Voice Commands to Test
1. "Hello" - Test basic greeting
2. "What time is it?" - Test time query
3. "What's the weather?" - Test weather feature
4. "Help" - Test help command
5. "Shutdown" - Test shutdown command

## Support & Issues

For bugs, feature requests, or questions:
1. Check existing [Issues](https://github.com/Sudharsan07-A/friday-assistant-ironman/issues)
2. Create a new issue with detailed description
3. Include device info and Android version
4. Attach error logs if applicable

---

**Happy coding, Sir!** 🤖✨
