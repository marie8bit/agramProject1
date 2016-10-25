package com.MarieErickson;

import java.util.LinkedList;

/**
 * Created by Marie on 10/25/2016.
 */
public class PlayerManager {
    LinkedList<Player> players = new LinkedList<>();
    public final int MAX_PLAYERS = 5;
    private int starter;

    public PlayerManager(int num, Deck deck) {
        for (int x = 1; x-1 < num; x++) {
            Player ply = new Player(x);
            players.add(ply);
        }

        int dealRounds = 0;
        while (dealRounds <= 2) {
            for (Player player : players) {
                player.hand.add(deck.deal());
                player.hand.add(deck.deal());
                player.hand.add(deck.deal());
                dealRounds++;
            }
        }
        this.starter=1;
    }

    public int getStarter() {
        return starter;
    }

    public void setStarter(int starter) {
        this.starter = starter;
    }

    public void round() {
        for (Player plr:players){
            if (plr.playerName == starter) {
                Card lead = lead(plr);
            }
        }
        //todo add next players action
        //for (players)
        //follow(player, lead)
    }
    public Card lead(Player player){

        int min=12;
        for (Card c:player.hand){
            if (c.value<min)
            {min=c.value;
            }
        }
        //todo remove card from player hand
        return player.hand.get(min);
    }
    public Card follow(Player player, Card lead){
        int flw= 0;
        return player.hand.get(flw);
    }
}

