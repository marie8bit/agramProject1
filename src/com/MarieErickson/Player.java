package com.MarieErickson;

import java.util.LinkedList;

/**
 * Created by Marie on 10/25/2016.
 */
public class Player {
    int playerName;
    LinkedList<Card> hand;
    public Player(int name){
        this.playerName= name;;
        this.hand= new LinkedList<>();
    }
}
