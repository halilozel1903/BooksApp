package com.canerture.booksapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.canerture.booksapp.R
import com.canerture.booksapp.databinding.BookDetailBottomSheetBinding
import com.canerture.booksapp.viewmodel.BooksFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class BookDetailBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BookDetailBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BooksFragmentViewModel() }

    private val args: BookDetailBottomSheetArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.book_detail_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.booksModel

        binding.apply {
            booksModel = book

            Picasso.get().load(book.book_image_url).into(bookImage)

            addCartButton.setOnClickListener {
                viewModel.addCartBook(book.book_id)
            }

            cancelButton.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}