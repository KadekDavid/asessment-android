package org.d3if0126.asessment.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if0126.asessment.R
import org.d3if0126.asessment.databinding.ItemHistoriBinding
import org.d3if0126.asessment.db.HasilEntity
import org.d3if0126.asessment.model.hasilHitung
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<HasilEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<HasilEntity>() { override fun areItemsTheSame(
                oldData: HasilEntity, newData: HasilEntity
            ): Boolean {
                return oldData.id == newData.id }
                override fun areContentsTheSame(
                    oldData: HasilEntity, newData: HasilEntity
                ): Boolean {
                    return oldData == newData
                }
            }

    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: HasilEntity) = with(binding) {
            val hasilHitung = item.hasilHitung()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))

            LuasTextView.text = root.context.getString(
                R.string.hasil_x,
                hasilHitung.luas, hasilHitung.keliling)

            dataTextView.text = root.context.getString(R.string.data_x,
                item.panjang, item.lebar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}