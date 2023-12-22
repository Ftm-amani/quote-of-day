package com.example.quoteofday.data.local

import androidx.room.TypeConverter
import com.example.quoteofday.data.QuotesTypeName
import com.example.quoteofday.data.models.QuotesType

class QuotesTypeConverter {
    @TypeConverter
    fun fromQuotesType(quotesType: QuotesType): String {
        return "${quotesType.name.name},${quotesType.isSelected}"
    }

    @TypeConverter
    fun toQuotesType(data: String): QuotesType {
        val parts = data.split(",")
        return QuotesType(QuotesTypeName.valueOf(parts[0]), parts[1].toBoolean())
    }
}