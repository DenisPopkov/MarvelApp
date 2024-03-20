package ru.popkov.marvelapp.features.main.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.popkov.marvelapp.features.main.data.local.AppDatabase
import ru.popkov.marvelapp.features.main.data.local.daos.HeroDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun database(@ApplicationContext context: Context): AppDatabase =
        Room
            .databaseBuilder(
                context,
                AppDatabase::class.java, "marvel-database"
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun heroDao(database: AppDatabase): HeroDao =
        database.heroDao()
}
