package ir.kaaveh.localdatasource.dto

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "news", primaryKeys = ["title", "source"])
data class LocalNewsDto(
    val title: String,
    val author: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val url: String,
    val urlToImage: String,
    @ColumnInfo(defaultValue = "false") val isFavorite: Boolean,
)