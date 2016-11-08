package com.MarieErickson;

import java.util.LinkedList;

/**player class needs a name and a hand of cards
 * Created by Marie on 10/25/2016.
 */
public class Player {
    int playerName;
    LinkedList<Card> hand;
    //cnstructor that takes an interger as a playername
    //and initializes player hand
    public Player(int name){
        this.playerName= name;;
        this.hand= new LinkedList<>();
    }
}
