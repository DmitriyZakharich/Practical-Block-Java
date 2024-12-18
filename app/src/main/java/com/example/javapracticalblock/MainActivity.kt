package com.example.javapracticalblock

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.javapracticalblock.huffman_algorithm.HuffmanAlgorithm
import java.util.Collections

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        startHuffmanAlgorithm()
    }

    private fun startHuffmanAlgorithm() {
        val input = "huffman coding example"

        val (encodedString, codes) = HuffmanAlgorithm.encode(input)
        println("Original String: $input")
        println("Encoded String: $encodedString")
        println("Huffman Codes: $codes")

        val root = HuffmanAlgorithm.buildHuffmanTree(HuffmanAlgorithm.calculateFrequency(input))

        val decodedString = HuffmanAlgorithm.decode(encodedString, root)
        println("Decoded String: $decodedString")
    }
}
