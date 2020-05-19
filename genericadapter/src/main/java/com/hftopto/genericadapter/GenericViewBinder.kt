package com.hftopfo.genericadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

/**
 * Created by jakub on 22.05.17.
 */
open class GenericViewBinder<T : Any>(@LayoutRes private val layout: Int,
                                      val dataClass: KClass<T>,
                                      private val onViewCreated: (View) -> Unit = {},
                                      private val bindPayloads: RecyclerView.ViewHolder.(T, MutableList<Any>?) -> Unit = { data, _ -> bind(data) },
                                      private val bind: RecyclerView.ViewHolder.(T) -> Unit = {}
) {
    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        this.onViewCreated(view)
        return object : RecyclerView.ViewHolder(view) {}
    }

    fun bindViewHolder(holder: RecyclerView.ViewHolder, data: T) = holder.bind(data)
    fun bindViewHolderPayloads(holder: RecyclerView.ViewHolder, data: T, payloads: MutableList<Any>?) = holder.bindPayloads(data, payloads)

}