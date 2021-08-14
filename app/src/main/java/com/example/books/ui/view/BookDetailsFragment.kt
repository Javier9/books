package com.example.books.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.books.R
import com.example.books.data.model.Item
import com.example.books.databinding.FragmentBookDetailsBinding
import com.example.books.ui.base.BaseViewBinding
import com.example.books.utils.setImageGlide


class BookDetailsFragment : BaseViewBinding<FragmentBookDetailsBinding>(FragmentBookDetailsBinding::inflate) {

    private lateinit var book:Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            book = it.getParcelable("book")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageBookDetail.setImageGlide(book.volumeInfo.imageLinks.thumbnail,null,true)
        binding.tvBookTittle.text = book.volumeInfo.title
        //binding.tvBookDirectors.text = book.volumeInfo.authors
        binding.tvBookDescription.text = book.volumeInfo.description


    }

}