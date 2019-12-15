package com.jaranedaa.songfinder.util

class Util {

    companion object {
        fun convertTimeMilesInHoursAndMinutes(timeInMilis: Int): String {
            val minutes = timeInMilis / 1000 / 60
            val seconds = timeInMilis / 1000 % 60
            return "$minutes : $seconds"

        }
    }
}