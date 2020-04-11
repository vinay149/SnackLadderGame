package com.company.service

import kotlin.random.Random

class DiceService{
    companion object{
        fun roll():Int{
            return Random.nextInt(1,6)
        }
    }
}