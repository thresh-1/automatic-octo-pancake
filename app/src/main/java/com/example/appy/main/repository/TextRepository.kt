package com.example.appy.main.repository

import com.example.appy.main.model.Text
import com.example.appy.main.room.TextDao
import kotlinx.coroutines.flow.distinctUntilChanged

class TextRepository(
    private val textDao: TextDao
) {
    fun getText(id: Int) = textDao.getText(id = id).distinctUntilChanged()

    suspend fun insertText(text: Text) = textDao.insertText(text)

    suspend fun clear() = textDao.clear()
}