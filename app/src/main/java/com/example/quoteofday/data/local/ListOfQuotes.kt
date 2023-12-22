package com.example.quoteofday.data.local

import com.example.quoteofday.data.models.Quotes
import com.example.quoteofday.data.QuotesTypeName
import com.example.quoteofday.data.models.QuotesType

object ListOfQuotes {
    var allQuotes = listOf(
        Quotes(0,"MENTAL_HEALTH", QuotesType(QuotesTypeName.MENTAL_HEALTH,true),false),
        Quotes(1,"love k",QuotesType(QuotesTypeName.MENTAL_HEALTH,true),false),
        Quotes(2,"there is a hope",QuotesType(QuotesTypeName.HOPE,true),false),
        Quotes(3,"MENTAL_HEALTH",QuotesType(QuotesTypeName.MENTAL_HEALTH,true),true),
        Quotes(4,"be strong",QuotesType(QuotesTypeName.MONEY,true),false),
        Quotes(5,"don't give up",QuotesType(QuotesTypeName.HEALTHY_RELATIONSHIPS,true),false),
        Quotes(6,"you are cute",QuotesType(QuotesTypeName.MOTIVATIONAL,true),false),
        Quotes(7,"love u",QuotesType(QuotesTypeName.SELF_CONFIDENCE,true),true),
        Quotes(8,"قوی باش ادامه بده",QuotesType(QuotesTypeName.MENTAL_HEALTH,true),false),
        Quotes(9,"love yourself",QuotesType(QuotesTypeName.WORK_PROFESSION,true),false),
    )
}