package com.jaranedaa.songfinder.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val searchId: Int,
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionViewUrl: String,
    val country: String,
    val currency: String,
    val discCount: Int,
    val discNumber: Int,
    val isStreamable: Boolean,
    val kind: String,
    val previewUrl: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCensoredName: String,
    val trackCount: Int,
    val trackExplicitness: String,
    val trackId: Int,
    val trackName: String,
    val trackNumber: Int,
    val trackPrice: Double,
    val trackTimeMillis: Int,
    val trackViewUrl: String,
    val wrapperType: String
)
