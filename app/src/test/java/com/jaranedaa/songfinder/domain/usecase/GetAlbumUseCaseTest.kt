package com.jaranedaa.songfinder.domain.usecase

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAlbumUseCaseTest {
    private lateinit var getAlbumUseCase: GetAlbumUseCase


    @Before
    fun setUp() {
        getAlbumUseCase = GetAlbumUseCase()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun debeRetornarAlbum() {
        runBlocking {

            val result = getAlbumUseCase.getAlbumByCollectionId("1072796439")

            assertTrue(result.size > 0)

        }

    }
}