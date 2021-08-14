package com.example.books.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.data.model.BooksResponse
import com.example.books.data.model.Item
import com.example.books.data.model.VolumeInfo
import com.example.books.databinding.ItemBookBinding
import com.example.books.utils.setImageGlide
import com.squareup.picasso.Picasso
import javax.inject.Inject


class BookAdapter (
    private val book: List<Item>,
    private val context: Context,
    private val itemClickListener:OnBookClickListener
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    interface OnBookClickListener{
        fun onBookClick (bookItem:Item,position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemBookBinding.bind(itemView)
        fun bind(book:Item){

            binding.imageBook.setImageGlide(book.volumeInfo.imageLinks.smallThumbnail,null,true)
//            Glide.with(context).load(book.volumeInfo.imageLinks.thumbnail).centerCrop()
//                .into(binding.imageBook)
//            Picasso.get().load(book.volumeInfo.imageLinks.thumbnail).into(binding.imageBook)
            binding.titulo.text = book.volumeInfo.title
            binding.description.text = book.volumeInfo.description
            itemView.setOnClickListener { itemClickListener.onBookClick(book,position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutinflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutinflater.inflate(R.layout.item_book,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = book[position]
        holder.bind(book)
    }

    override fun getItemCount() = book.size


}
