package com.MarieErickson;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        //create game deck
        Deck deck = new Deck();
        //creates players
        PlayerManager plMgmt = new PlayerManager(2, deck);
        //calls round method 6 times per agram rules
        for (int z= 0;z<6;z++){
        plMgmt.round();
        }
        //provides user with information
        System.out.println("Player"+plMgmt.getLastRoundWinner()+" won the game");
    }
}
