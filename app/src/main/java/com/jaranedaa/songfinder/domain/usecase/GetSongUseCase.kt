package com.jaranedaa.songfinder.domain.usecase

import com.jaranedaa.songfinder.data.repository.MusicRepository
import com.jaranedaa.songfinder.domain.model.Song

class GetSongUseCase {

    val musicRepository = MusicRepository()

    suspend fun getSongByName(name : String) : List<Song>{
        return musicRepository.getSongsByName(name)
    }
}