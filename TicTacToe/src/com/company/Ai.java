package com.company;

import java.util.Random;

public class Ai {
    public static void aiMove()
{
    int rand1,rand2;
    //Horizontally
    for(int i = 0; i<3; i++)
    {
        if(Board.gameBoard[0][i] == Board.gameBoard[1][i] && Board.gameBoard[0][i] == Side.Ai)
        {

            if(Board.gameBoard[2][i] == ' ')
            {
                Board.gameBoard[2][i] = Side.Ai;
                return;
            }
        }
    }
    for(int i=0;i<3;i++)
    {
        if(Board.gameBoard[2][i] == Board.gameBoard[1][i] && Board.gameBoard [2][i] == Side.Ai)
        {
            if(Board.gameBoard[0][i] == ' ')
            {
                Board.gameBoard[0][i] = Side.Ai;
                return;
            }
        }
    }

    //Vertically
    for(int i=0;i<3;i++)
    {
        if(Board.gameBoard[i][0] == Board.gameBoard[i][1] && Board.gameBoard [i][0] == Side.Ai)
        {
            if(Board.gameBoard[i][2] == ' ')
            {
                Board.gameBoard[i][2] = Side.Ai;
                return;
            }
        }
    }
    for(int i=0;i<3;i++)
    {
        if(Board.gameBoard[i][2] == Board.gameBoard[i][1] && Board.gameBoard [i][2] == Side.Ai)
        {
            if(Board.gameBoard[i][0] == ' ')
            {
                Board.gameBoard[i][0] = Side.Ai;
                return;
            }
        }
    }
    //Diagonally
    if(Board.gameBoard[0][0] == Board.gameBoard[1][1] && Board.gameBoard [0][0] == Side.Ai)
    {
        if(Board.gameBoard[2][2] == ' ')
        {
            Board.gameBoard[2][2] = Side.Ai;
            return;
        }
    }
    if(Board.gameBoard[2][2] == Board.gameBoard[1][1] && Board.gameBoard [2][2] == Side.Ai)
    {
        if(Board.gameBoard[0][0] == ' ')
        {
            Board.gameBoard[0][0] = Side.Ai;
            return;
        }
    }
    if(Board.gameBoard[0][0] == Board.gameBoard[1][1] && Board.gameBoard [0][0] == Side.Ai)
    {
        if(Board.gameBoard[2][2] != Side.Human)
        {
            Board.gameBoard[2][2] = Side.Ai;
            return;
        }
    }
    if(Board.gameBoard[0][2] == Board.gameBoard[1][1] && Board.gameBoard [0][2] == Side.Ai)
    {
        if(Board.gameBoard[2][0] == ' ')
        {
            Board.gameBoard[2][0] = Side.Ai;
            return;
        }
    }
    if(Board.gameBoard[2][0] == Board.gameBoard[1][1] && Board.gameBoard [2][0] == Side.Ai)
    {
        if(Board.gameBoard[0][2] == ' ')
        {
            Board.gameBoard[0][2] = Side.Ai;
            return;
        }
    }
    //Defends
    for(int i=0;i<3;i++)
    {
        if(Board.gameBoard[0][i] == Board.gameBoard[1][i] && Board.gameBoard [0][i] == Side.Human)
        {
            if(Board.gameBoard[2][i] == ' ')
            {
                Board.gameBoard[2][i] = Side.Ai;
                return;
            }
        }
    }
    for(int i=0;i<3;i++)
    {
        if(Board.gameBoard[2][i] == Board.gameBoard[1][i] && Board.gameBoard [0][i] == Side.Human)
        {
            if(Board.gameBoard[0][i] == ' ')
            {
                Board.gameBoard[0][i] = Side.Ai;
                return;
            }
        }
    }
    for(int i=0;i<3;i++)
    {
        if(Board.gameBoard[i][0] == Board.gameBoard[i][1] && Board.gameBoard [i][0] == Side.Human)
        {
            if(Board.gameBoard[i][2] == ' ')
            {
                Board.gameBoard[i][2] = Side.Ai;
                return;
            }
        }
    }
    for(int i=0;i<3;i++)
    {
        if(Board.gameBoard[i][2] == Board.gameBoard[i][1] && Board.gameBoard [i][2] == Side.Human)
        {
            if(Board.gameBoard[i][0] == ' ')
            {
                Board.gameBoard[i][0] = Side.Ai;
                return;
            }
        }
    }
    //Defends diagonally
    if(Board.gameBoard[0][0] == Board.gameBoard[1][1] && Board.gameBoard [0][0] == Side.Human)
    {
        if(Board.gameBoard[2][2] == ' ')
        {
            Board.gameBoard[2][2] = Side.Ai;
            return;
        }
    }
    if(Board.gameBoard[2][2] == Board.gameBoard[1][1] && Board.gameBoard [2][2] == Side.Human)
    {
        if(Board.gameBoard[0][0] == ' ')
        {
            Board.gameBoard[0][0] = Side.Ai;
            return;
        }
    }
    if(Board.gameBoard[0][2] == Board.gameBoard[1][1] && Board.gameBoard [0][2] == Side.Human)
    {
        if(Board.gameBoard[2][0] == ' ')
        {
            Board.gameBoard[2][0] = Side.Ai;
            return;
        }
    }
    if(Board.gameBoard[2][0] == Board.gameBoard[1][1] && Board.gameBoard [2][0] == Side.Human)
    {
        if(Board.gameBoard[0][2] == ' ')
        {
            Board.gameBoard[0][2] = Side.Ai;
            return;
        }
    }
    Random rand = new Random();
    while(!Board.isFull())
    {
        rand1 = rand.nextInt(3);
        rand2 = rand.nextInt(3);
        if(Board.gameBoard[rand1][rand2] == ' ')
        {
            Board.gameBoard[rand1][rand2] = Side.Ai;
            Board.printGame();
            return;
        }
    }
}
}
