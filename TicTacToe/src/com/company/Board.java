package com.company;

import java.util.Scanner;

public class Board implements Printable {

    public static char [][] gameBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};


    public static boolean isGameOver()
    {
        return winGameX() || winGameO() || isFull();
    }
    public static void printGame()
    {
        System.out.println(gameBoard[0][0] + "|" + gameBoard[0][1] + "|" + gameBoard[0][2] +  "\n-+-+-\n"
                + gameBoard[1][0] + "|" + gameBoard[1][1] + "|" + gameBoard[1][2] +  "\n-+-+-\n"
                + gameBoard[2][0] + "|" + gameBoard[2][1] + "|" + gameBoard[2][2]);
    }
    public static boolean winGameX()
    {
        return (gameBoard[0][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][2] == 'X')
                || (gameBoard[0][0] == 'X' && gameBoard[1][0] == 'X' && gameBoard[2][0] == 'X')
                || (gameBoard[0][1] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][1] == 'X')
                || (gameBoard[0][2] == 'X' && gameBoard[1][2] == 'X' && gameBoard[2][2] == 'X')
                || (gameBoard[0][2] == 'X' && gameBoard[1][1] == 'X' && gameBoard[2][0] == 'X')
                || (gameBoard[0][0] == 'X' && gameBoard[0][1] == 'X' && gameBoard[0][2] == 'X')
                || (gameBoard[1][0] == 'X' && gameBoard[1][1] == 'X' && gameBoard[1][2] == 'X')
                || (gameBoard[2][0] == 'X' && gameBoard[2][1] == 'X' && gameBoard[2][2] == 'X');
    }
    public static boolean winGameO()
    {
        return (gameBoard[0][0] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][2] == 'O')
                || (gameBoard[0][0] == 'O' && gameBoard[1][0] == 'O' && gameBoard[2][0] == 'O')
                || (gameBoard[0][1] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][1] == 'O')
                || (gameBoard[0][2] == 'O' && gameBoard[1][2] == 'O' && gameBoard[2][2] == 'O')
                || (gameBoard[0][2] == 'O' && gameBoard[1][1] == 'O' && gameBoard[2][0] == 'O')
                || (gameBoard[0][0] == 'O' && gameBoard[0][1] == 'O' && gameBoard[0][2] == 'O')
                || (gameBoard[1][0] == 'O' && gameBoard[1][1] == 'O' && gameBoard[1][2] == 'O')
                || (gameBoard[2][0] == 'O' && gameBoard[2][1] == 'O' && gameBoard[2][2] == 'O');
    }
    public static boolean isFull()
    {
        for(int i = 0; i < 3; i++) {
            for(int g = 0; g < 3; g++) {
                if(gameBoard[i][g]== ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static void getPlayerMove(){
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        Board.gameBoard[x][y]=Side.Human;
    }
}
