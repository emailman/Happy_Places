package edu.mailman.happyplaces.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.mailman.happyplaces.databinding.ItemHappyPlaceBinding
import edu.mailman.happyplaces.models.HappyPlaceModel

class HappyPlacesAdapter(private var list: ArrayList<HappyPlaceModel>) :
    RecyclerView.Adapter<HappyPlacesAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    inner class ViewHolder(binding: ItemHappyPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle
        val tvDescription = binding.tvDescription
        val ivPlaceImage = binding.ivPlaceImage
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHappyPlaceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: HappyPlaceModel = list[position]

        holder.tvTitle.text = model.title
        holder.tvDescription.text = model.description
        holder.ivPlaceImage.setImageURI(Uri.parse(model.image))

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, model)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClick(position: Int, model: HappyPlaceModel)
    }
}