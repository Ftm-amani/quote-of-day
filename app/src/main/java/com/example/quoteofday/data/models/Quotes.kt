package com.example.quoteofday.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.quoteofday.data.QuotesTypeName
import com.example.quoteofday.data.local.QuotesTypeConverter
import kotlinx.parcelize.Parcelize

@Entity(tableName = "quotes_table")
@Parcelize
data class Quotes(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "text")
    var text: String = "",

    @TypeConverters(QuotesTypeConverter::class)
    @ColumnInfo(name = "type")
    var type: QuotesType = QuotesType(0, QuotesTypeName.MENTAL_HEALTH, true),

    @ColumnInfo(name = "isFave")
    var isFave: Boolean? = false
) : Parcelable {
    constructor() : this(0, "", QuotesType(0, QuotesTypeName.MENTAL_HEALTH, true), false)
}

@Entity(tableName = "quotes_types")
@Parcelize
data class QuotesType(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "QuotesTypeName")
    var name: QuotesTypeName = QuotesTypeName.MENTAL_HEALTH,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = true
) : Parcelable