package com.example.bharatnews.db

import android.content.Context
import androidx.room.*
import com.example.bharatnews.models.Article

@Database(entities = [Article::class],version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase() {
    abstract fun getArticleDao():ArticleDao

    companion object{
        @Volatile
      private var INSTANCE:ArticleDatabase?=null
        private val LOCK=Any()
        operator fun invoke(context: Context)= INSTANCE?: synchronized(LOCK){
            INSTANCE?: createDatabase(context).also{ INSTANCE=it}
        }

        private fun createDatabase(context: Context)= Room.databaseBuilder(
                context.applicationContext,ArticleDatabase::class.java,"article_db.db"

        ) .build()





    }
}