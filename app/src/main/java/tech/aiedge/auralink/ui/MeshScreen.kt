package tech.aiedge.auralink.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tech.aiedge.auralink.domain.EdgeNode
import tech.aiedge.auralink.domain.ScanState

private val Ink = Color(0xFF070B14)
private val Card = Color(0xFF121A33)
private val Mint = Color(0xFF34D399)
private val Warn = Color(0xFFFBBF24)

@Composable
fun MeshScreen(vm: MeshViewModel) {
    val state by vm.state.collectAsStateWithLifecycle()
    Surface(color = Ink, modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("AURALINK", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFD7E3FF))
            Text("edge mesh · live RSSI", color = Color(0xFF8BA0C7))
            when (val s = state) {
                ScanState.Idle, ScanState.Scanning -> LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                is ScanState.Live -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(s.nodes, key = { it.id }) { NodeCard(it) }
                }
            }
        }
    }
}

@Composable
private fun NodeCard(n: EdgeNode) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Card, MaterialTheme.shapes.medium)
            .padding(14.dp),
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(n.name, color = Color.White)
            Text(if (n.online) "LIVE" else "DROP", color = if (n.online) Mint else Warn)
        }
        Text(
            "${n.id}   rssi ${n.rssi} dBm   batt ${n.batteryPct}%",
            color = Color(0xFF9BB0D4),
            fontFamily = FontFamily.Monospace,
        )
    }
}
