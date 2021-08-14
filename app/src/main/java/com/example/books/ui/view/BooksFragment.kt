package com.example.books.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books.R
import com.example.books.data.model.Item
import com.example.books.databinding.BooksFragmentBinding
import com.example.books.ui.base.BaseViewBinding
import com.example.books.ui.viewmodel.BooksViewModel
import com.example.books.vo.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : BaseViewBinding<BooksFragmentBinding>(BooksFragmentBinding::inflate),BookAdapter.OnBookClickListener {

    private val bookModel : BooksViewModel by viewModels()
    private val LIST_VIEW = "LIST_VIEW"
    private val GRID_VIEW = "GRID_VIEW"
    var currentView = LIST_VIEW

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerListView()
        searchViewBook()
        bookObserver()

        binding.floatingActionGrid.setOnClickListener {
            if (currentView == LIST_VIEW){
                binding.floatingActionGrid.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_list
                    )
                )
                setupRecyclerGridView()
            }else{
                binding.floatingActionGrid.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_grid
                    )
                )
                setupRecyclerListView()
            }

        }
    }

    private fun setupRecyclerListView(){
        currentView = LIST_VIEW
        binding.recycleBooks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(requireContext(),
                    DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun setupRecyclerGridView(){
        currentView = GRID_VIEW
        binding.recycleBooks.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            addItemDecoration(
                DividerItemDecoration(requireContext(),
                    DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun bookObserver(){
        bookModel.book.observe(viewLifecycleOwner, Observer {book ->
            when(book){
                is Resource.Succes -> {
//                    Toast.makeText(requireContext(),book.value.items.toString(),Toast.LENGTH_LONG).show()
                    val adapterBook = BookAdapter(book.value.items,requireContext(),this)
                    binding.recycleBooks.apply {
                        setHasFixedSize(true)
                        adapter = adapterBook
                    }
                    binding.progressBar2.visibility = View.GONE
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(),"Failure",Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun searchViewBook(){
        binding.searchview.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                bookModel.getBooks(query!!)
                binding.progressBar2.visibility = View.VISIBLE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onBookClick(bookItem: Item, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("book",bookItem)
        findNavController().navigate(R.id.action_booksFragment_to_bookDetailsFragment,bundle)
    }

}