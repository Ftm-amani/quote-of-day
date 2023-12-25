package com.example.quoteofday.data.local

import com.example.quoteofday.data.models.Quotes
import com.example.quoteofday.data.QuotesTypeName
import com.example.quoteofday.data.models.QuotesType

object ListOfQuotes {
    var allQuotes = listOf(
        Quotes(0,"MENTAL_HEALTH", QuotesType(0,QuotesTypeName.MENTAL_HEALTH,true),false),
        Quotes(1,"love k",QuotesType(1,QuotesTypeName.MENTAL_HEALTH,true),false),
        Quotes(2,"there is a hope",QuotesType(2,QuotesTypeName.HOPE,true),false),
        Quotes(3,"MENTAL_HEALTH",QuotesType(3,QuotesTypeName.MENTAL_HEALTH,true),true),
        Quotes(4,"be strong",QuotesType(4,QuotesTypeName.MONEY,true),false),
        Quotes(5,"don't give up",QuotesType(5,QuotesTypeName.HEALTHY_RELATIONSHIPS,true),false),
        Quotes(6,"you are cute",QuotesType(6,QuotesTypeName.MOTIVATIONAL,true),false),
        Quotes(7,"love u",QuotesType(7,QuotesTypeName.SELF_CONFIDENCE,true),true),
        Quotes(8,"قوی باش ادامه بده",QuotesType(8,QuotesTypeName.MENTAL_HEALTH,true),false),
        Quotes(9,"love yourself",QuotesType(9,QuotesTypeName.WORK_PROFESSION,true),false),
    )
}