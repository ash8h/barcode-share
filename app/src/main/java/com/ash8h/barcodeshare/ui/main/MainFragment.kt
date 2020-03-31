package com.ash8h.barcodeshare.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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

    private enum class FabState { OPEN, CLOSE }

    private val viewModel: MainViewModel by viewModel()
    private var fabState: FabState = FabState.CLOSE
    private val fabOpenAnimation: Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open)
    }
    private val fabCloseAnimation: Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close)
    }
    private val fabRotateClockwise: Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_clock)
    }
    private val fabRotateCounterClockwise: Animation by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_anticlock)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = BarcodeRecyclerViewAdapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())

        fab.setOnClickListener {
            when (fabState) {
                FabState.OPEN -> closeFab()
                FabState.CLOSE -> openFab()
            }
        }
        fab_camera.setOnClickListener { }
        fab_library.setOnClickListener { }

        viewModel.barcodes.observeNotNull(this) { barcodes ->
            adapter.items = barcodes
            adapter.notifyDataSetChanged()
            empty.visibility = if (barcodes.isEmpty()) View.VISIBLE else View.GONE
        }
        viewModel.fetchBarcodes()
    }

    private fun openFab() {
        fab_background.visibility = View.VISIBLE
        fab_camera_text.visibility = View.VISIBLE
        fab_library_text.visibility = View.VISIBLE
        fab_camera.visibility = View.VISIBLE
        fab_library.visibility = View.VISIBLE
        fab_camera.startAnimation(fabOpenAnimation)
        fab_library.startAnimation(fabOpenAnimation)
        fab.startAnimation(fabRotateClockwise)
        fabState = FabState.OPEN
    }

    private fun closeFab() {
        fab_background.visibility = View.GONE
        fab_camera_text.visibility = View.GONE
        fab_library_text.visibility = View.GONE
        fab_camera.visibility = View.GONE
        fab_library.visibility = View.GONE
        fab_camera.startAnimation(fabCloseAnimation)
        fab_library.startAnimation(fabCloseAnimation)
        fab.startAnimation(fabRotateCounterClockwise)
        fabState = FabState.CLOSE
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
