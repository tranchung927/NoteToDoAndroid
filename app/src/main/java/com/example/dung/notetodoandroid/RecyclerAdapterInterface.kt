package com.example.dung.notetodoandroid

import android.view.View

interface RecyclerAdapterInterface {
    fun didSelected(item: View, position: Int)
}