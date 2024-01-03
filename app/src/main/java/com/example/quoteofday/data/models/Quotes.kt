package com.example.quoteofday.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "quotes_table")
@Parcelize
data class Quotes(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "quote_text")
    var quoteText: String = "",

    @ColumnInfo(name = "quote_name")
    var quoteName: String = "",

    @ColumnInfo(name = "is_fave")
    var isFave: Boolean = false,

    @ColumnInfo(name = "is_selected")
    var isSelected: Boolean = true
) : Parcelable {
    constructor() : this(0, "", "", false, true)
}


data class QuoteNameAndSelection(
    val quote_name: String,
    val is_selected: Boolean
)