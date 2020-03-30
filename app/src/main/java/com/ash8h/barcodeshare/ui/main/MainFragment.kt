package com.ash8h.barcodeshare.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ash8h.barcodeshare.R
import com.ash8h.barcodeshare.data.entity.Barcode
import com.ash8h.barcodeshare.util.BarcodeGenerator
import com.ash8h.barcodeshare.util.ext.observeNotNull
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_barcode.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = BarcodeRecyclerViewAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())

        viewModel.barcodes.observeNotNull(this) { barcodes ->
            adapter.items = barcodes
            adapter.notifyDataSetChanged()
            empty.visibility = if (barcodes.isEmpty()) View.VISIBLE else View.GONE
        }
        viewModel.fetchBarcodes()
    }

    private class BarcodeRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var items: List<Barcode> = emptyList()

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_barcode, parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = items[position]
            val width = holder.itemView.code_image.width
            val height = holder.itemView.code_image.height
            val bitmap = BarcodeGenerator.generate(item, width, height)
            holder.itemView.code_image.setImageBitmap(bitmap)
            holder.itemView.code_text.text = item.data
        }

        private class ViewHolder(contextView: View) : RecyclerView.ViewHolder(contextView)
    }
}
