package com.namshi.sharukh.modules.common

import com.namshi.sharukh.dataModels.Image

/**
 * To Communicate between adapters/fragments
* */
interface ActionListener {
    fun onItemClick(image: Image)
}