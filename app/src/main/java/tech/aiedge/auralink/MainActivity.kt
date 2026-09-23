package tech.aiedge.auralink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import tech.aiedge.auralink.ui.MeshScreen
import tech.aiedge.auralink.ui.MeshViewModel

class MainActivity : ComponentActivity() {
    private val vm: MeshViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                MeshScreen(vm)
            }
        }
    }
}
