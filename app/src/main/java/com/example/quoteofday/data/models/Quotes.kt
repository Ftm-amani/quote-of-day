package com.example.quoteofday.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quoteofday.data.QuotesType
import kotlinx.parcelize.Parcelize

@Entity(tableName = "quotes_table")
@Parcelize
data class Quotes(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    
    @ColumnInfo(name ="text")
    var text :String = "",
    
    @ColumnInfo(name ="type")
    var type : QuotesType = QuotesType.MENTAL_HEALTH,
    
    @ColumnInfo(name ="isFave")
    var isFave :Boolean? = false
): Parcelable {
    constructor(): this(0,"",QuotesType.MENTAL_HEALTH,false)
}