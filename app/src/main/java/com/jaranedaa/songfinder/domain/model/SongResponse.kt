package com.jaranedaa.songfinder.domain.model

data class SongResponse(
    val resultCount: Int,
    val results: List<Song>
)