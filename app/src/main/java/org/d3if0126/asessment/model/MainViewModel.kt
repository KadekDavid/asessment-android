package org.d3if0126.asessment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val hasilPersegiPanjang = MutableLiveData<HasilPersegiPanjang?>()

     fun hitungPersegiPanjang( panjang : Int,  lebar : Int) {
        val luas = panjang * lebar
        val keliling  = 2 * (panjang + lebar)
        hasilPersegiPanjang.value = HasilPersegiPanjang(luas, keliling)
    }
    fun getHasilPersegiPanjang(): LiveData<HasilPersegiPanjang?> = hasilPersegiPanjang
}
