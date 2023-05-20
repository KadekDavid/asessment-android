package org.d3if0126.asessment.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0126.asessment.db.HasilDao
import org.d3if0126.asessment.db.HasilDb
import org.d3if0126.asessment.db.HasilEntity
import org.d3if0126.asessment.model.HasilPersegiPanjang
import org.d3if0126.asessment.model.hasilHitung

class HitungViewModel(private val db: HasilDao) : ViewModel() {

    private val hasilPersegiPanjang = MutableLiveData<HasilPersegiPanjang?>()

     fun hitungPersegiPanjang( panjang : Int,  lebar : Int) {
         val dataHasil = HasilEntity(
             panjang = panjang,
             lebar = lebar
         )
         hasilPersegiPanjang.value = dataHasil.hasilHitung()

         viewModelScope.launch { withContext(Dispatchers.IO) {
             db.insert(dataHasil)
            }
         }
    }
    fun getHasilPersegiPanjang(): LiveData<HasilPersegiPanjang?> = hasilPersegiPanjang

}
