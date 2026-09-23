package tech.aiedge.auralink.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.aiedge.auralink.domain.EdgeRepository
import tech.aiedge.auralink.domain.ScanState
import tech.aiedge.auralink.domain.SimulatedMesh

class MeshViewModel(
    repo: EdgeRepository = SimulatedMesh(),
) : ViewModel() {

    private val _state = MutableStateFlow<ScanState>(ScanState.Idle)
    val state: StateFlow<ScanState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ScanState.Scanning
            repo.watch().collect { nodes ->
                _state.update { ScanState.Live(nodes.sortedByDescending { it.rssi }) }
            }
        }
    }
}
