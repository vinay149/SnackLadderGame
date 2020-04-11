package com.company.models

import com.company.models.Ladder
import com.company.models.Snake
import java.util.*

class SnakeAndLadderBoard(val size: Int) {
    var snakes : MutableList<Snake> = ArrayList()
    var ladders: MutableList<Ladder> = ArrayList()
    var playerPieces: MutableMap<String, Int> = HashMap()
}
