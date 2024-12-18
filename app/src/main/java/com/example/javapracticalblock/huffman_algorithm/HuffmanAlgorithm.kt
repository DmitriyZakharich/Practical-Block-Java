package com.example.javapracticalblock.huffman_algorithm

import java.util.PriorityQueue

sealed class Node(val freq: Int) {
    class Leaf(val char: Char, freq: Int) : Node(freq)
    class Internal(val left: Node, val right: Node, freq: Int) : Node(freq)
}

object HuffmanAlgorithm {

    fun calculateFrequency(input: String): Map<Char, Int> {
        val frequencyMap = mutableMapOf<Char, Int>()
        for (char in input) {
            frequencyMap[char] = frequencyMap.getOrDefault(char, 0) + 1
        }
        return frequencyMap
    }

    fun buildHuffmanTree(frequencies: Map<Char, Int>): Node {
        val priorityQueue = PriorityQueue<Node>(compareBy { it.freq })

        for ((char, freq) in frequencies) {
            priorityQueue.add(Node.Leaf(char, freq))
        }

        while (priorityQueue.size > 1) {
            val left = priorityQueue.poll()
            val right = priorityQueue.poll()
            priorityQueue.add(Node.Internal(left, right, left.freq + right.freq))
        }

        return priorityQueue.poll()
    }

    private fun generateCodes(node: Node, code: String, codes: MutableMap<Char, String>) {
        when (node) {
            is Node.Leaf -> codes[node.char] = code
            is Node.Internal -> {
                generateCodes(node.left, code + "0", codes)
                generateCodes(node.right, code + "1", codes)
            }
        }
    }

    fun encode(input: String): Pair<String, Map<Char, String>> {
        val frequencies = calculateFrequency(input)
        val root = buildHuffmanTree(frequencies)
        val codes = mutableMapOf<Char, String>()

        generateCodes(root, "", codes)

        val encodedString = input.map { codes[it] }.joinToString("")

        return Pair(encodedString, codes)
    }

    fun decode(encoded: String, root: Node): String {
        val result = StringBuilder()
        var currentNode = root

        for (bit in encoded) {
            currentNode = when (bit) {
                '0' -> (currentNode as Node.Internal).left
                '1' -> (currentNode as Node.Internal).right
                else -> throw IllegalArgumentException("Invalid bit in encoded string")
            }

            if (currentNode is Node.Leaf) {
                result.append(currentNode.char)
                currentNode = root
            }
        }

        return result.toString()
    }
}
