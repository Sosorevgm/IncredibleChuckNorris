package com.incredible.chuck.norris.utils

import android.content.Context
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.models.FactModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet

class ProfanityFilter(
    private val context: Context
) {

    private val profanitySet: HashSet<String> = HashSet()

    init {
        val bufferedReader =
            BufferedReader(InputStreamReader(context.resources.openRawResource(R.raw.badwords)))
        while (bufferedReader.ready()) {
            profanitySet.add(bufferedReader.readLine())
        }
        bufferedReader.close()
    }

    private fun checkProfanityContent(fact: FactModel): Boolean {
        var result = false
        val content = fact.fact
        for (i in 0 until profanitySet.size) {
            if (content.contains(profanitySet.elementAt(i), true)) {
                result = true
            }
        }
        return result
    }

    fun applyFilter(fact: FactModel): FactModel {
        if (checkProfanityContent(fact)) {
            val filteredFact = StringBuilder(fact.fact)
            val factCharArray = fact.fact.toCharArray()
            val word = StringBuilder()
            for (i in factCharArray.indices) {
                if (factCharArray[i].isLetter()) {
                    word.append(factCharArray[i])
                } else {
                    for (j in profanitySet.indices) {
                        if (word.toString().toLowerCase(Locale.ROOT) == profanitySet.elementAt(j)) {
                            val substitute = StringBuilder()
                            for (k in word.indices - 1) {
                                substitute.append("*")
                            }
                            filteredFact.replace(i - word.length + 1, i, substitute.toString())
                        }
                    }
                    word.clear()
                }
            }
            fact.fact = filteredFact.toString()
            return fact
        } else {
            return fact
        }
    }
}