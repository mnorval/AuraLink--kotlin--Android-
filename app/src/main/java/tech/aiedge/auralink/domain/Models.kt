package tech.aiedge.auralink.domain

data class EdgeNode(
    val id: String,
    val name: String,
    val rssi: Int,
    val batteryPct: Int,
    val online: Boolean,
)

sealed interface ScanState {
    data object Idle : ScanState
    data object Scanning : ScanState
    data class Live(val nodes: List<EdgeNode>) : ScanState
}
