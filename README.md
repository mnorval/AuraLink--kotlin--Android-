# AuraLink-Android

Modern **Kotlin + Jetpack Compose** mesh HUD. `ViewModel` collects a cold `Flow` of edge nodes; the repository is a simulator today and a `BluetoothLeScanner` tomorrow — same contract.

## Stack
- compileSdk 35 / minSdk 26 / Kotlin 2.0 / Compose BOM 2024.10
- unidirectional state: `ScanState` sealed interface
- `collectAsStateWithLifecycle` so scans pause off-screen

## Open in Android Studio
File → Open → this folder. Sync Gradle, run the `app` configuration.

Swap `SimulatedMesh` in `MeshViewModel` for a real BLE implementation when hardware is on the bench.
