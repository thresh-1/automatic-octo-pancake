package com.example.appy.main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Text(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("text") val text: String
)
