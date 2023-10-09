package com.example.appy.main.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appy.main.model.Text
import kotlinx.coroutines.flow.Flow

@Dao
interface TextDao {

    @Query("SELECT * FROM text WHERE id  == :id")
    fun getText(id: Int): Flow<Text>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertText(vararg text: Text)

    @Query("DELETE FROM text")
    suspend fun clear()
}