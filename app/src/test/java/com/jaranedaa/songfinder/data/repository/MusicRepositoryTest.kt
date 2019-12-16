package com.jaranedaa.songfinder.data.repository

import com.jaranedaa.songfinder.domain.model.Result
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MusicRepositoryTest {

    private lateinit var repository: MusicRepository


    @Before
    fun setUp() {
        repository = MusicRepository()

    }

    @After
    fun tearDown() {

    }

    @Test
    fun debeRetonarListaCanciones() {
        var result : List<Result> = listOf()
        runBlocking { result = repository.getSongsByName("Aerials") }

        assertTrue(result.size > 0)
    }

    @Test
    fun debeRetonarAlbum() {
        var result : List<Result> = listOf()

        runBlocking { result = repository.getAlbumByCollectionId("1072796439") }
        assertTrue(result.size > 0)

    }
}