package org.d3if0126.asessment.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0126.asessment.db.HasilDao

class HistoriViewModel(private val db: HasilDao) : ViewModel() {
    val data = db.getLastHasil()

    fun hapusData() = viewModelScope.launch { withContext(Dispatchers.IO) {
        db.clearData() }
    }
}