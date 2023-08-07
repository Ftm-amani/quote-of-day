package com.example.quoteofday.data.local

import com.example.quoteofday.data.models.Quotes
import com.example.quoteofday.ui.QuotesType

object ListOfQuotes {
    var allQuotes = listOf(
        Quotes(0,"MENTAL_HEALTH",QuotesType.MENTAL_HEALTH,false),
        Quotes(1,"love k",QuotesType.MENTAL_HEALTH,false),
        Quotes(2,"there is a hope",QuotesType.HOPE,false),
        Quotes(3,"MENTAL_HEALTH",QuotesType.MENTAL_HEALTH,true),
        Quotes(4,"be strong",QuotesType.MONEY,false),
        Quotes(5,"don't give up",QuotesType.HEALTHY_RELATIONSHIPS,false),
        Quotes(6,"you are cute",QuotesType.MOTIVATIONAL,false),
        Quotes(7,"love u",QuotesType.SELF_CONFIDENCE,true),
        Quotes(8,"قوی باش ادامه بده",QuotesType.MENTAL_HEALTH,false),
        Quotes(9,"love yourself",QuotesType.WORK_PROFESSION,false),
    )
}