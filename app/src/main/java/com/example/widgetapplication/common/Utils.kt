package com.example.widgetapplication.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

typealias Time = String
typealias Date = LocalDate

object Utils {

    fun getTeamCrest(team: String) = when(team) {
        "Arsenal" -> "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/" +
                "Arsenal_FC.svg/1920px-Arsenal_FC.svg.png"

        "Aston Villa" -> "https://upload.wikimedia.org/wikipedia/en/a/a8/Aston_Villa_FC_logo.png"

        "Bournemouth" -> "https://upload.wikimedia.org/wikipedia/en/thumb/e/e5/" +
                "AFC_Bournemouth_(2013).svg/300px-AFC_Bournemouth_(2013).svg.png"

        "Brentford" -> "https://upload.wikimedia.org/wikipedia/en/thumb/2/2a/" +
                "Brentford_FC_crest.svg/1920px-Brentford_FC_crest.svg.png"

        "Newcastle" -> "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/" +
                "Newcastle_United_Logo.svg/400px-Newcastle_United_Logo.svg.png"

        "Chelsea" -> "https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/" +
                "Chelsea_FC.svg/1024px-Chelsea_FC.svg.png"

        "Tottenham" -> "https://upload.wikimedia.org/wikipedia/en/thumb/b/b4/" +
                "Tottenham_Hotspur.svg/1024px-Tottenham_Hotspur.svg.png"

        "Liverpool" -> "https://upload.wikimedia.org/wikipedia/en/thumb/0/0c/" +
                "Liverpool_FC.svg/1280px-Liverpool_FC.svg.png"

        "Manchester City" -> "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/" +
                "Manchester_City_FC_badge.svg/380px-Manchester_City_FC_badge.svg.png"

        "Burnley" -> "https://upload.wikimedia.org/wikipedia/en/thumb/6/6d/" +
                "Burnley_FC_Logo.svg/360px-Burnley_FC_Logo.svg.png"

        "Fulham" -> "https://upload.wikimedia.org/wikipedia/en/thumb/e/eb/" +
                "Fulham_FC_(shield).svg/300px-Fulham_FC_(shield).svg.png"

        "Everton" -> "https://upload.wikimedia.org/wikipedia/en/thumb/7/7c/" +
                "Everton_FC_logo.svg/1920px-Everton_FC_logo.svg.png"

        "Luton" -> "https://upload.wikimedia.org/wikipedia/en/thumb/9/9d/" +
                "Luton_Town_logo.svg/360px-Luton_Town_logo.svg.png"

        "Brighton" -> "https://upload.wikimedia.org/wikipedia/en/thumb/f/fd/" +
                "Brighton_&_Hove_Albion_logo.svg/1920px-Brighton_&_Hove_Albion_logo.svg.png"

        "Nottingham Forest" -> "https://upload.wikimedia.org/wikipedia/en/thumb/e/e5/" +
                "Nottingham_Forest_F.C._logo.svg/800px-Nottingham_Forest_F.C._logo.svg.png"

        "Crystal Palace" -> "https://upload.wikimedia.org/wikipedia/en/thumb/a/a2/" +
                "Crystal_Palace_FC_logo_(2022).svg/350px-Crystal_Palace_FC_logo_(2022).svg.png"

        "Sheffield United" -> "https://upload.wikimedia.org/wikipedia/en/thumb/9/9c/" +
                "Sheffield_United_FC_logo.svg/380px-Sheffield_United_FC_logo.svg.png"

        "West Ham" -> "https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/" +
                "West_Ham_United_FC_logo.svg/1920px-West_Ham_United_FC_logo.svg.png"

        "Wolves" -> "https://upload.wikimedia.org/wikipedia/en/thumb/f/fc/" +
                "Wolverhampton_Wanderers.svg/1920px-Wolverhampton_Wanderers.svg.png"

        else -> "https://upload.wikimedia.org/wikipedia/en/thumb/7/7a/" +
                "Manchester_United_FC_crest.svg/1920px-Manchester_United_FC_crest.svg.png"
    }

    fun getRound(round: String): String {
        return when (round.last().toString()) {
            "1" -> round + "st"
            "2" -> round + "nd"
            "3" -> round + "rd"
            else -> round + "th"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeAndDate(dateTime: String): Pair<Time, Date> {
        val bridge = dateTime.indexOf("T")
        val time = dateTime.substring(
            startIndex = bridge + 1,
            endIndex = 16
        )
        val date = dateTime.substring(
            startIndex = 0,
            endIndex = bridge
        )
        return (time to LocalDate.parse(date))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayDateAndMonth(localDate: LocalDate): String {
        val day = localDate.dayOfWeek.name.lowercase().replaceFirstChar { firstChar ->
            firstChar.uppercase()
        }
        val date = localDate.dayOfMonth
        val month = localDate.month.name.lowercase().replaceFirstChar { firstChar ->
            firstChar.uppercase()
        }

        return "$day, $date $month"
    }
}