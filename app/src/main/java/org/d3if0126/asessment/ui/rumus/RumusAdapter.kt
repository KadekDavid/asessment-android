package org.d3if0126.asessment.ui.rumus

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0126.asessment.R
import org.d3if0126.asessment.databinding.ListRumusBinding
import org.d3if0126.asessment.model.Rumus
import org.d3if0126.asessment.network.RumusApi

class RumusAdapter : RecyclerView.Adapter<RumusAdapter.ViewHolder>() {
    private val data = mutableListOf<Rumus>()
    fun updateData(newData: List<Rumus>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ListRumusBinding
    ) : RecyclerView.ViewHolder (binding.root){

        fun bind (rumus: Rumus) = with(binding) {
            namaTextView.text = rumus.nama

            Glide.with(imageView.context)
                .load(RumusApi.getRumusUrl(rumus.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, rumus.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRumusBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}