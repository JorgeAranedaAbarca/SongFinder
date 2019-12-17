package com.jaranedaa.songfinder.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Search(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val text: String)