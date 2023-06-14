package org.d3if0126.asessment.ui.rumus

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0126.asessment.model.Rumus
import org.d3if0126.asessment.network.ApiStatus
import org.d3if0126.asessment.network.RumusApi
import org.d3if0126.asessment.network.UpdateWorker
import java.util.concurrent.TimeUnit

class RumusViewModel : ViewModel() {
    private val data = MutableLiveData<List<Rumus>>()
    private val status = MutableLiveData<ApiStatus>()
    init {
        retrieveData()
    }

    fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(RumusApi.service.getRumus())
                status.postValue(ApiStatus.SUCCES)
            } catch (e: Exception) {
                Log.d("RumusViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Rumus>> = data
    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}