package com.hftopto.genericadapter.sample

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hftopfo.genericadapter.GenericViewBinder
import com.hftopfo.genericadapter.sample.R
import com.hftopto.genericadapter.GenericAdapter
import kotlinx.android.synthetic.main.activity_sample.*
import kotlinx.android.synthetic.main.li_header.view.*
import kotlinx.android.synthetic.main.li_text.view.*


/**
 * Created by maciek on 29.05.2017.
 */

class GenericAdapterSampleActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val adapter = GenericAdapter<Any>(
                GenericViewBinder(R.layout.li_text, TextData::class) { data ->
                    itemView.text.text = data.text
                },
                GenericViewBinder(R.layout.li_header, HeaderData::class) { data ->
                    itemView.header.text = data.header
                })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.addData(listOf(HeaderData("HEADER"),
                TextData("data1"),
                TextData("data2"),
                HeaderData("HEADER2"),
                TextData("data3"),
                TextData("data4"),
                TextData("data5")))
    }
}