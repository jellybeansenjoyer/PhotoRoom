package com.example.practiceapp.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practiceapp.Data.ImageEntity
import com.example.practiceapp.R
import com.example.practiceapp.databinding.ImageBinding

class ImageAdapter(private val listener:ImageAdapter.onImageClickListener) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var oldlist:List<ImageEntity> = emptyList()

    class ImageViewHolder(private val parent : ViewGroup , private val view : View,private val listener : ImageAdapter.onImageClickListener): RecyclerView.ViewHolder(view){
        lateinit private var binding : ImageBinding
        fun bind(image: ImageEntity){
            binding = DataBindingUtil.bind(view)!!
            Glide.with(parent.context).load(image.imageUrl).into(binding.image)
            binding.image.setOnClickListener {
                listener.getImage(image)
            }
        }

    }
    interface onImageClickListener{
        fun getImage(image : ImageEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return create(parent,listener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(oldlist[position])
    }

    override fun getItemCount() = oldlist.size

    fun submitList(newList:List<ImageEntity>){
        val diff = ImageDiff(oldlist,newList)
        oldlist = newList
        val calc = DiffUtil.calculateDiff(diff)
        calc.dispatchUpdatesTo(this)
    }

    companion object{
        fun create(parent:ViewGroup,listener:ImageAdapter.onImageClickListener):ImageAdapter.ImageViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.image,parent,false)
                return ImageViewHolder(parent,view,listener)
        }
    }
}