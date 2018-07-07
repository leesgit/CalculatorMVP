package com.lbc.practice.calculator.util

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

class MusicManager {

    private val mediaPlayer = arrayOfNulls<MediaPlayer>(10)
    private var correctSound: MediaPlayer? =null
    private var inCorrectSound: MediaPlayer? = null

    private var mainsong: MediaPlayer? = null
    private var logosong: MediaPlayer? = null

    fun numberSound(ctx: Context, res: Int, num: Int) {
        if (mediaPlayer[num] == null) {
            mediaPlayer[num] = MediaPlayer.create(ctx, res)
        }
        mediaPlayer[num]?.start()

    }

    fun answerSound(ctx: Context, res: Int, answer: Boolean) {

        if(answer) {
            if (correctSound == null) {
                correctSound = MediaPlayer.create(ctx, res)
            }
            correctSound?.start()
        }

        else {
            if (inCorrectSound == null) {
                inCorrectSound = MediaPlayer.create(ctx, res)
            }
            inCorrectSound?.start()

        }

    }

    fun calSongStart(ctx: Context, res: Int) {
        if (logosong == null) {
            logosong = MediaPlayer.create(ctx, res)
        } else {
            logosong!!.prepare()
        }
        logosong!!.start()
        logosong!!.isLooping = true
    }

    fun calSongStop() {
        if (logosong != null) {
            logosong!!.stop()
        }
    }

    fun mainsongStart(ctx: Context, res: Int) {
        if (mainsong == null) {
            mainsong = MediaPlayer.create(ctx, res)
        } else {
            mainsong!!.prepare()
        }
        mainsong!!.start()
        mainsong!!.isLooping = true
    }

    fun mainsongStop() {
        if (mainsong != null) {
            mainsong!!.stop()
        }
    }

}
