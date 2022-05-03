package com.monolieta.pandora.android.ui.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monolieta.pandora.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val key: String = savedStateHandle["key"]
        ?: throw IllegalArgumentException("missing game key")

    val game = repository.findByKey(key).asLiveData()
}