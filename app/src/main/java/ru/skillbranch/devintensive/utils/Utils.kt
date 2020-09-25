package ru.skillbranch.devintensive.utils

import android.icu.text.Transliterator
import android.os.Build
import androidx.annotation.RequiresApi
import ru.skillbranch.devintensive.models.User


object Utils {
    const val CYRILLIC_TO_LATIN = "Cyrillic-Latin"
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)
    }


    fun transliteration(payload: String, divider: String = " "): String {
        var (firstName, lastName) = parseFullName(payload)
        firstName = toLatin(firstName)
        lastName = toLatin(lastName)
        return "$firstName$divider$lastName"
    }

    fun toLatin(name: String?): String {
        var count = 0
        var transliteration = ""
        if (name != null) {
            while (count <= name.length - 1) {
                transliteration += when (name?.get(count).toString()) {
                    "а" -> "a"
                    "б" -> "b"
                    "в" -> "v"
                    "г" -> "g"
                    "д" -> "d"
                    "е" -> "e"
                    "ё" -> "e"
                    "ж" -> "zh"
                    "з" -> "z"
                    "и" -> "i"
                    "й" -> "i"
                    "к" -> "k"
                    "л" -> "l"
                    "м" -> "m"
                    "н" -> "n"
                    "о" -> "o"
                    "п" -> "p"
                    "р" -> "r"
                    "с" -> "s"
                    "т" -> "t"
                    "у" -> "u"
                    "ф" -> "f"
                    "х" -> "h"
                    "ц" -> "c"
                    "ч" -> "ch"
                    "ш" -> "sh"
                    "щ" -> "sh"
                    "ъ" -> ""
                    "ы" -> "i"
                    "ь" -> ""
                    "э" -> "e"
                    "ю" -> "yu"
                    "я" -> "ya"
                    "А" -> "A"
                    "Б" -> "B"
                    "В" -> "V"
                    "Г" -> "G"
                    "Д" -> "D"
                    "Е" -> "E"
                    "Ё" -> "E"
                    "Ж" -> "Zh"
                    "З" -> "Z"
                    "И" -> "I"
                    "Й" -> "I"
                    "К" -> "K"
                    "Л" -> "L"
                    "М" -> "M"
                    "Н" -> "N"
                    "О" -> "O"
                    "П" -> "P"
                    "Р" -> "R"
                    "С" -> "S"
                    "Т" -> "T"
                    "У" -> "U"
                    "Ф" -> "F"
                    "Х" -> "H"
                    "Ц" -> "C"
                    "Ч" -> "Ch"
                    "Ш" -> "Sh"
                    "Щ" -> "Sh"
                    "Ъ" -> ""
                    "Ы" -> "I"
                    "Ь" -> ""
                    "Э" -> "E"
                    "Ю" -> "Yu"
                    "Я" -> "Ya"
                    else -> {
                        count = name.length
                        name
                    }
                }
                count++
            }
        }
        return "$transliteration"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        var firstInitial = if (firstName != null) {
            if (firstName.isBlank()) null else firstName?.get(0).toUpperCase()
        } else null
        var secondInitial = if (lastName != null) {
            if (lastName.isBlank()) null else lastName?.get(0).toUpperCase()
        } else null
        return when {
            (firstInitial == null && secondInitial == null) -> null
            (firstInitial != null && secondInitial != null) -> "$firstInitial$secondInitial"
            firstInitial != null -> "$firstInitial"
            else -> "$secondInitial"
        }
    }
}
