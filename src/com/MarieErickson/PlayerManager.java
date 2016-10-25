package com.MarieErickson;

import java.util.LinkedList;

/**
 * Created by Marie on 10/25/2016.
 */
public class PlayerManager {
    LinkedList<Player> players = new LinkedList<>();
    public final int MAX_PLAYERS = 5;
    public PlayerManager(int num, Deck deck){
        for (int x= 0; x<num; x++){
            Player ply = new Player(x);
            players.add(ply);
        }
        int deal=1;
        while(deal<=2){
        for (Player player:players) {
            player.hand.add(deck.deal());
            player.hand.add(deck.deal());
            player.hand.add(deck.deal());
            deal++;
        }
        }
    }
}
