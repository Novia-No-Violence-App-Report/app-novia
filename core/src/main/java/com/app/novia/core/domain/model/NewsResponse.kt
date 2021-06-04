package com.app.novia.core.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    @field:SerializedName("articles")
    val articles: List<Article>? = null
)

data class Article(

    @field:SerializedName("source")
    val source: Source? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("urlToImage")
    val imageUrl: String? = null,

    @field:SerializedName("publishedAt")
    val date: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    @field:SerializedName("description")
    val description: String? = null
) : Serializable

data class Source(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null
) : Serializable