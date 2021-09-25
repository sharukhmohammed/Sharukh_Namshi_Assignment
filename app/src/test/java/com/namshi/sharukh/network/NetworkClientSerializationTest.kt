package com.namshi.sharukh.network

import com.google.common.truth.Truth.assertThat
import com.namshi.sharukh.dataModels.Image
import com.namshi.sharukh.dataModels.NamshiWidget
import com.namshi.sharukh.misc.json
import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.network.response.HomeContent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import org.junit.Test


class NetworkClientSerializationTest {

    @ExperimentalSerializationApi
    @Test
    fun deSerialiseContentApiEmptyJson_returnsTrue() {
        val emptyObj = HomeContent()
        val parsedObject = json.decodeFromString<HomeContent>("{}")
        assertThat(emptyObj).isEqualTo(parsedObject)
    }

    @ExperimentalSerializationApi
    @Test
    fun deSerialiseContentApiInvalidResponse_returnsTrue() {
        val response = """
            <head>404 not found</head>
            <body>unavailable</body>
        """.trimIndent()
        try {
            json.decodeFromString<HomeContent>(response)
        } catch (e: Exception) {
            assertThat(e is SerializationException).isTrue()
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun deSerialiseContentApiContainingNull_returnsTrue() {
        val response = """{
                "content": [
                    {
                        "type": null,
                        "cols": 1,
                        "images": [
                            {
                                "url": "https://a.namshicdn.com/cms/small/search/20180219/generic/module_01_en.jpg",
                                "width": 640,
                                "height": 152,
                                "format": "image"
                            }
                        ]
                    }	
                ]
            }
        """.trimIndent()
        val parsedObject = json.decodeFromString<HomeContent>(response)
        assertThat(parsedObject.content[0].type).isNotNull()
    }


    @ExperimentalSerializationApi
    @Test
    fun deSerialiseCarouselApiEmptyJson_returnsTrue() {
        val emptyObj = CarouselContent()
        val parsedObject = json.decodeFromString<CarouselContent>("{}")
        assertThat(emptyObj).isEqualTo(parsedObject)
    }

    @ExperimentalSerializationApi
    @Test
    fun deSerialiseCarouselApiInvalidResponse_returnsTrue() {
        val response = """
            <head>401 not authorized</head>
            <body>unavailable</body>
        """.trimIndent()
        try {
            json.decodeFromString<CarouselContent>(response)
        } catch (e: Exception) {
            assertThat(e is SerializationException).isTrue()
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun deSerialiseCarouselApiContainingNull_returnsTrue() {
        val response = """{
                "images": [
                            {
                                "url": "https://a.namshicdn.com/cms/small/search/20180219/generic/module_01_en.jpg",
                                "width": 640,
                                "height": 152,
                                "format": null
                            }
                        ]
            }
        """.trimIndent()
        val parsedObject = json.decodeFromString<CarouselContent>(response)
        assertThat(parsedObject.images[0].format.name).isEqualTo(NamshiWidget.Type.unknown.name)
    }
}