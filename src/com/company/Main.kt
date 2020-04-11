package com.company

import com.company.models.Ladder
import com.company.models.Player
import com.company.models.Snake
import com.company.service.SnackLadderBoardService
import java.util.*
import kotlin.collections.ArrayList

object Main {
    @JvmStatic
    fun main(args: Array<String>) {


        val reader = Scanner(System.`in`)
        val numberOfLadder = reader.nextInt()
        val listOfLadder: MutableList<Ladder> = ArrayList()
        for (i in 0 until numberOfLadder) {
            val head = reader.nextInt()
            val tail = reader.nextInt()
            val ladder = Ladder(head, tail)
            listOfLadder.add(ladder)
        }

        val numberOfSnack = reader.nextInt()
        val listOfSnack:MutableList<Snake> = ArrayList()
        for (i in 0 until numberOfSnack){
            val head = reader.nextInt()
            val tail = reader.nextInt()
            val snack = Snake(head, tail)
            listOfSnack.add(snack)
        }

        val numberOfPlayer = reader.nextInt()
        val listOfPlayer:MutableList<Player> = ArrayList()
        for( i in 0 until numberOfPlayer){
            val name = reader.nextLine()
            var id = 0;
            for( j in name.indices){
                id += (name[j] - '0') % 256
            }
            val player = Player(name, id)
            listOfPlayer.add(player)
        }

        println("Total Number of Player in the game are:: ${listOfPlayer.size}")

        val snackLadderBoardService = SnackLadderBoardService()
        snackLadderBoardService.addPlayer(listOfPlayer)
        snackLadderBoardService.setAllLadder(listOfLadder)
        snackLadderBoardService.setAllSnack(listOfSnack)
        snackLadderBoardService.startTheGame()
    }
}