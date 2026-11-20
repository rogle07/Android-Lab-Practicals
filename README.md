# 📱 Android Lab Practicals — by Yogesh

> One Android app containing **8 complete Lab Practicals** (Practical 3 to Practical 10), built in **Java**.

---

## ⬇️ Download APK

> **👉 [Go to GitHub Releases to Download the APK](../../releases/latest)**

Every time code is pushed to this repository, a **GitHub Actions workflow** automatically builds a fresh `.apk` file and uploads it to the Releases page — so **everyone** can download it directly.

---

## 📋 Practical List & Direct Download

| Practical | Title | Download | Description |
|-----------|-------|----------|-------------|
| **Practical 03** | Simple Calculator | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | Addition, subtraction, multiplication, division with decimal support and error checks |
| **Practical 04** | Menus & Navigation | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | Options Menu, Context Menu, and screen switching via multiple Activities |
| **Practical 05** | Internal Storage | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | File writing (`openFileOutput`) and reading (`openFileInput`) in private storage |
| **Practical 06** | SQLite To-Do List | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | SQLite database CRUD (`INSERT`, `UPDATE`, `SELECT`, `DELETE`) |
| **Practical 07** | Internet & Email | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | Connectivity check, open websites, and email via `ACTION_SENDTO` Intent |
| **Practical 08** | Graphics & Animations | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | Custom Canvas drawing + `ValueAnimator` for Rotate, Scale, Translate, Fade |
| **Practical 09** | Location Based Services | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | `FusedLocationProviderClient` with runtime location permission and Google Maps |
| **Practical 10** | Device Camera | [⬇️ Download APK](../../releases/latest/download/AndroidLabPracticals-Yogesh.apk) | `ACTION_IMAGE_CAPTURE` Intent and thumbnail preview via `ActivityResult` |

> *(All 8 practicals are bundled in one APK. Launch the app and tap any card to open that practical.)*

---

## 🛠️ Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java** | Primary programming language |
| **XML** | UI layouts |
| **SQLite** | Local database (Practical 6) |
| **Android SDK 34** | Target platform |
| **Gradle** | Build system |
| **GitHub Actions** | CI/CD — auto-build APK on every push |
| **Material Components** | Modern UI |
| **Google Play Services Location** | GPS (Practical 9) |

---

## 🏗️ Repository Structure

```
AndroidLabPracticals/
├── .github/
│   └── workflows/
│       └── build-apk.yml          # Automated CI/CD pipeline
├── app/src/main/
│   ├── java/com/yogesh/androidlabpracticals/
│   │   ├── MainActivity.java       # Dashboard with 8 practical cards
│   │   ├── practical3/             # Simple Calculator
│   │   ├── practical4/             # Menus & Navigation (3 Activities)
│   │   ├── practical5/             # Internal Storage
│   │   ├── practical6/             # SQLite To-Do List
│   │   ├── practical7/             # Internet & Email
│   │   ├── practical8/             # Graphics & Animations
│   │   ├── practical9/             # Location Services
│   │   └── practical10/            # Device Camera
│   ├── res/layout/                 # XML layouts for all screens
│   └── AndroidManifest.xml
├── build.gradle
└── settings.gradle
```

---

## ⚙️ Build Locally (Android Studio)

```bash
git clone https://github.com/rogle07/Android-Lab-Practicals.git
```

1. Open **Android Studio** → File → Open → select the cloned folder
2. Wait for Gradle sync
3. **Build → Build APK(s)**
4. APK at: `app/build/outputs/apk/debug/app-debug.apk`

---

## 📲 Install on Your Android Phone

1. Download `AndroidLabPracticals-Yogesh.apk` from [Releases](../../releases/latest)
2. On your phone: **Settings → Security → Install Unknown Apps → Allow**
3. Open the downloaded APK → **Install**
4. Launch **"Android Lab Practicals"** from your app drawer

---

## 👨‍💻 Author

**Yogesh** — Android Lab Practicals College Assignment

*Made by Yogesh*
