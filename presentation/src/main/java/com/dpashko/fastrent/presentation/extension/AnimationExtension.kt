/*
 * Property of Medtronic MiniMed.
 */

package com.dpashko.fastrent.presentation.extension

import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun MotionLayout.getTransitionCompletionFlow(): Flow<Unit> {
    return callbackFlow {
        val listener = object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                this@callbackFlow.trySendBlocking(Unit)
                this@callbackFlow.close()
                this@getTransitionCompletionFlow.removeTransitionListener(this)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        }
        this@getTransitionCompletionFlow.addTransitionListener(listener)
        awaitClose {
            this@callbackFlow.cancel()
        }
    }
}