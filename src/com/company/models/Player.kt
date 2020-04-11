package com.company.models

import com.company.models.Dice
import com.company.models.Person

class Player(name:String, id:Int):Person(name, id) {
    override fun moveToPosition(dice: Dice, value:Int):Int {
                return dice.position + value
    }
}