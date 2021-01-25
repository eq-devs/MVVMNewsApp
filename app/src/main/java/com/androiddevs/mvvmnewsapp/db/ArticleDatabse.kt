package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.*
import com.androiddevs.mvvmnewsapp.models.Article


@Database(
    entities = [Article::class],
    version = 1
)


@TypeConverters(Converters::class)
abstract class ArticleDatabse:RoomDatabase(){

        abstract  fun getArticleDao():ArticleDao
        companion object{
            @Volatile
            private var instance:ArticleDatabse?=null
            private val LOCK = Any()


        operator fun invoke(context:Context)= instance?: synchronized(LOCK){
            instance?:createDatabase(context).also{instance=it}
        }

        private fun createDatabase(context: Context)=Room.databaseBuilder(context.applicationContext,ArticleDatabse::class.java
            ,"article_db.db").build()

    }

}