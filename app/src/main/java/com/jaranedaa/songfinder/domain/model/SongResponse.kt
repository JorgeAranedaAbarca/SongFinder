package com.jaranedaa.songfinder.domain.model

import com.jaranedaa.songfinder.domain.model.Artist

data class SongResponse(
    val resultCount: Int,
    val results: List<Artist>
)