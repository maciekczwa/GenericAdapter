package com.hftopfo.genericadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

/**
 * Created by jakub on 22.05.17.
 */
open class GenericViewBinder<T : Any>(@LayoutRes private val layout: Int,
                                      val dataClass: KClass<T>,
                                      private val bind: RecyclerView.ViewHolder.(T) -> Unit = {}) {
    fun createViewHolder(parent: ViewGroup) = object : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {}
    fun bindViewHolder(holder: RecyclerView.ViewHolder, data: T) = holder.bind(data)
}