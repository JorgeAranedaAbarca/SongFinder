package com.jaranedaa.songfinder.domain.usecase

import com.jaranedaa.songfinder.data.repository.MusicRepository
import com.jaranedaa.songfinder.domain.model.Result


class GetAlbumUseCase {

    val musicRepository = MusicRepository()

    suspend fun getAlbumByCollectionId(collectionId : String) : List<Result>{
        return musicRepository.getAlbumByCollectionId(collectionId)
    }
}