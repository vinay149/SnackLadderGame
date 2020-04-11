package com.company.models

abstract class Person(val name: String, val id:Int){
     val dicePosition: Int ?= null
    abstract fun moveToPosition(dice: Dice, value:Int):Int
}