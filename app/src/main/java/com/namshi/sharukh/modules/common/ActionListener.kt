package com.namshi.sharukh.modules.common

import com.namshi.sharukh.dataModels.Image

interface ActionListener {
    fun onItemClick(image: Image)
}