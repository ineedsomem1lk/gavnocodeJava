package com.company;

public class Game {
 public static void StartGame() {
     Side.ChooseSide();
     while(!Board.isGameOver()){
         if(Side.Human == 'X') {
             Board.getPlayerMove();
             Ai.aiMove();
         }
         else {
             Ai.aiMove();
             Board.getPlayerMove();
         }
         Board.printGame();
     }
 }
}
