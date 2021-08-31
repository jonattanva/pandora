package com.monolieta.pandora.android.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monolieta.pandora.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    gameRepository: GameRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val key: String = savedStateHandle["key"]
        ?: throw IllegalArgumentException("missing game key")

    val game = gameRepository.findByKey(key = key).asLiveData()
}