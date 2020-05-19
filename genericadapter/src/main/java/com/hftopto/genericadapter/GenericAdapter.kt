package com.hftopto.genericadapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hftopfo.genericadapter.GenericViewBinder

/**
 * todojs: pagination
 * todojs: spinner
 * todojs: mapping
 *
 * Created by jakub on 22.05.17.
 */
open class GenericAdapter(vararg val binders: GenericViewBinder<*>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val classList = binders.map { it.dataClass.java }
    private var data = mutableListOf<Any>()

    fun reset() {
        data = mutableListOf()
        notifyDataSetChanged()
    }

    fun addData(newData: List<Any>, hasMore: Boolean = false) {
        val offset = data.size
        data.addAll(newData)
        notifyItemRangeInserted(offset, newData.size) // Or invalidate
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = binders[getItemViewType(position)].bindGeneric(holder, data[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = binders[viewType].createViewHolder(parent)

    override fun getItemViewType(position: Int) = classList.lastIndexOf(data[position].javaClass).also {
        if (it == -1) throw IllegalStateException("Binder for data type not registered: ${data[position].javaClass}")
    }

    @Suppress("UNCHECKED_CAST") // this is required - bridge between declarative generics and runtime reflection
    private fun <T : Any> GenericViewBinder<*>.bindGeneric(holder: RecyclerView.ViewHolder, data: T) = (this as GenericViewBinder<T>).bindViewHolder(holder, data)
}