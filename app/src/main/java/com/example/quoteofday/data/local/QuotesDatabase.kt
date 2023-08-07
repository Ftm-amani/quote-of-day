package com.example.quoteofday.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quoteofday.data.models.Quotes
import com.example.quoteofday.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Quotes::class] , version = 1, exportSchema = false )
abstract class QuotesDatabase : RoomDatabase(){
    
    abstract fun quotesDao() : QuoteDao
    
    class Callback @Inject constructor(
        private val database: Provider<QuotesDatabase>,
        @ApplicationScope private val applicationScope : CoroutineScope
    
    ): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            
            //db operations
            val dao = database.get().quotesDao()
            
            applicationScope.launch {
                ListOfQuotes.allQuotes.forEach { dao.insert(it) }
                val list = dao.getAllQuotes()
            }
        }
    }
}