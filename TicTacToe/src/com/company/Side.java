package com.company;

import java.util.Scanner;

public class Side {
    public static char Ai;
    public static char Human;

    public Side(char ai , char human){
        Ai = ai;
        Human = human;
    }

    public static Side ChooseSide(){
        char ai, human;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose side : 1 - X , 2 - O");
        int side = sc.nextInt();
        if(side == 1){
            human = 'X';
            ai='O';
        }
        else{
            human = 'O';
            ai='X';
        }
        return new Side(ai,human);
    }

}
