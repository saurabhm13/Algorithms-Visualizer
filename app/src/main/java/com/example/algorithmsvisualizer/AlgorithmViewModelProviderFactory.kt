package com.example.algorithmsvisualizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.algorithmsvisualizer.algorithms.InsertionSort

class AlgorithmViewModelProviderFactory(
    private var insertionSort: InsertionSort
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlgorithmViewModel(insertionSort) as T
    }
}