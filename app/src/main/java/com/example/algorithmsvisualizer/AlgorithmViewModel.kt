package com.example.algorithmsvisualizer

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.InsertionSort
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random

class AlgorithmViewModel(
    private val insertionSort: InsertionSort
): ViewModel() {

    var arr = mutableStateOf(
        intArrayOf(
            50, 42, 165, 400, 244, 126, 54, 65, 2, 4, 509, 52, 76, 533, 46, 659, 42, 534, 128, 87, 162, 25, 428, 38, 9, 26
        )
    )

    val isPlaying = mutableStateOf(false)
    val onSortingFinish = mutableStateOf(false)
    private var delay = 150L
    private var pause = false

    private var next = 1
    private var previous = 0

    private var sortedArrayLevels = mutableListOf<List<Int>>()

    init {
        viewModelScope.launch {
            insertionSort.sort(
                arr.value.clone()
            ) {modifiedArray ->
                sortedArrayLevels.add(modifiedArray.toMutableList())

            }
        }
    }

    fun onEvent(events: AlgorithmEvents){
        when (events){
            is AlgorithmEvents.PlayPause -> {
                playPauseAlgorithm()
            }
            is AlgorithmEvents.SlowDown -> {
                slowDown()
            }
            is AlgorithmEvents.SpeedUp -> {
                speedUp()
            }
            is AlgorithmEvents.Previous -> {
                previousAlgorithm()
            }
            is AlgorithmEvents.Next -> {
                nextAlgorithm()
            }
        }
    }

    private fun nextAlgorithm() {

        if (next < sortedArrayLevels.size){
            arr.value = sortedArrayLevels[next].toIntArray()
            next++
            previous++
        }
    }

    private fun previousAlgorithm() {
        if (previous >= 0){
            arr.value = sortedArrayLevels[previous].toIntArray()
            next--
            previous--
        }
    }

    private fun speedUp() {
        delay = -50
    }

    private fun slowDown() {
        if (delay >= 100){
            delay += 50
        }
    }

    private fun playPauseAlgorithm() {
        if (isPlaying.value){
            pause()
        }else {
            play()
        }
    }

    private var sortingState = 0
    private fun play() = viewModelScope.launch {
        pause = false
        for (i in sortingState until sortedArrayLevels.size){
            if (!pause){
                delay(delay)
                arr.value = sortedArrayLevels[i].toIntArray()
            }else {
                sortingState = 1
                next = i+1
                previous = i
                return@launch
            }
        }

        onSortingFinish.value = true
    }

    private fun pause() {
        pause = true
    }
}