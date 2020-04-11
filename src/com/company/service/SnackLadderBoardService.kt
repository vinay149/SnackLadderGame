package com.company.service

import com.company.models.Ladder
import com.company.models.Player
import com.company.models.Snake
import com.company.models.SnakeAndLadderBoard
import java.util.*
import kotlin.collections.ArrayList

class SnackLadderBoardService{
    private val  snakeAndLadderBoard: SnakeAndLadderBoard
    private lateinit var numberOfPlayer:MutableList<Player>
    private val playerQueue:Queue<Player>  = LinkedList()
    private var isGameOver:Boolean = false
    companion object{
        private val GAME_BOARD_SIZE = 100
    }
    init {
        snakeAndLadderBoard = SnakeAndLadderBoard(GAME_BOARD_SIZE)
        init()
    }
    private fun init(){
        numberOfPlayer = ArrayList()
    }

    fun addPlayer(player: List<Player>){
        for(element in player) {
            numberOfPlayer.add(element)
        }
    }
    fun setAllSnack(snack:List<Snake>) {
        for (element in snack) {
            snakeAndLadderBoard.snakes.add(element)
        }
    }

    private fun hasSomeOneWon(position:Int):Boolean{
        if(position == GAME_BOARD_SIZE){
            return true
        }
        return false
    }

    private fun isGameOver():Boolean{
        return isGameOver
    }

    fun setAllLadder(ladder:List<Ladder>) {
        for (element in ladder) {
            snakeAndLadderBoard.ladders.add(element)
        }
    }

    private fun initTheAllPlayerPosition(player: List<Player>, startPosition:Int){
           for( i in player.indices) {
               player[i].name.let {name->
                   snakeAndLadderBoard.playerPieces.put(name, startPosition)
               }
           }
    }

    fun setAllPlayer(player: List<Player>){
        for(element in player) {
            playerQueue.add(element)
        }
    }

    private fun moveThePositionOfPlayer(player: Player, newPosition:Int):Int?{

        val oldPosition = snakeAndLadderBoard.playerPieces[player.name]
        var finalPosition = oldPosition?.plus(newPosition)
        var repeat = true
        while (repeat) {
            repeat = false
            if (finalPosition != null) {
                if (finalPosition > GAME_BOARD_SIZE) {
                    finalPosition = oldPosition
                }
                for (i in 0 until snakeAndLadderBoard.ladders.size) {
                    if (snakeAndLadderBoard.ladders[i].head == finalPosition) {
                        finalPosition = snakeAndLadderBoard.ladders[i].tail
                        repeat = true
                    }
                }
                for (i in 0 until snakeAndLadderBoard.snakes.size) {
                    if (snakeAndLadderBoard.snakes[i].head == finalPosition) {
                        finalPosition = snakeAndLadderBoard.snakes[i].tail
                        repeat = true
                    }
                }
                println("${player.name} move to position $oldPosition to $finalPosition")
            }
        }
        return finalPosition
    }

    private fun setThePlayerPosition(player:String, position: Int){
        snakeAndLadderBoard.playerPieces.put(player,position)
    }

    var  winningPosition:Int = 0
    fun startTheGame(){
        print("Lets start the game!")
        initTheAllPlayerPosition(numberOfPlayer,0)
        setAllPlayer(numberOfPlayer)
        while(!isGameOver()){
            val playerChance = playerQueue.poll()
            val number = DiceService.roll()
            print("${playerChance.name} roll the dice and got $number")
            val newPosition = moveThePositionOfPlayer(playerChance,number)
            if (newPosition != null) {
                setThePlayerPosition(playerChance.name,newPosition)
            }
            newPosition?.let {
                if (hasSomeOneWon(newPosition)){
                    winningPosition +=1
                    if(playerQueue.size==0){
                        isGameOver = true
                    }
                    println("${playerChance.name} Got the $winningPosition position")
                }else{
                    playerQueue.add(playerChance)
                }
            }
        }
    }
}