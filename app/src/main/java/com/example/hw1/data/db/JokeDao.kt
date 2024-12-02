package com.example.hw1.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {
    //user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalJoke(localJoke: LocalJoke)

    @Query("SELECT * FROM local_jokes")
    suspend fun getAllLocalJokes(): List<LocalJoke>

    @Query("SELECT * FROM local_jokes WHERE id = :jokeId")
    suspend fun getLocalJokeById(jokeId: Int): LocalJoke?

    //cache
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedJoke(joke: CachedJoke)

    @Query("SELECT * FROM cached_jokes")
    suspend fun getCachedJokes(): List<CachedJoke>

    @Query("SELECT * FROM cached_jokes WHERE id = :jokeId")
    suspend fun getCachedJokeById(jokeId: Int): CachedJoke?

    @Query("DELETE FROM cached_jokes WHERE timestamp < :maxTime")
    suspend fun deleteOldCache(maxTime: Long)
}
