package com.example.javapracticalblock.task_1_quick_sort

class QuickSort {

     fun execute(array: IntArray){
         quickSort(array, 0, array.size - 1)
     }

    private fun quickSort(array: IntArray, low: Int, high: Int){
        if (low < high){
            val pi = partition(array, low, high)
            quickSort(array, low, pi - 1)
            quickSort(array, pi + 1, high)
        }
    }

    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (arr[j] < pivot) {
                i++

                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }


        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp

        return i + 1
    }
}
