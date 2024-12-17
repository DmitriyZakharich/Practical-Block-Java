package com.example.javapracticalblock.task_2_workerthread

import java.util.concurrent.LinkedBlockingQueue

class WorkerThread : Thread() {

    private val taskQueue = LinkedBlockingQueue<() -> Unit>()

    @Volatile
    private var isRunning = true

    override fun run() {
        while (isRunning) {
            try {
                val task = taskQueue.take()
                task.invoke()
            } catch (e: InterruptedException) {
                isRunning = false
            }
        }
    }

    fun addTask(task: () -> Unit) {
        taskQueue.put(task)
    }

    fun shutdown() {
        isRunning = false
        this.interrupt()
    }
}

fun executeWorkerThread() {
    val worker = WorkerThread()
    worker.start()

    worker.addTask { println("Task 1") }
    worker.addTask { println("Task 2") }

    Thread.sleep(1000)

    worker.addTask { println("Task 3") }

    Thread.sleep(1000)
    worker.shutdown()
    println("Finish")
}
