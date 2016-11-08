package com.MarieErickson;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by Marie on 10/25/2016.
 */
public class PlayerManager {
    LinkedList<Player> players = new LinkedList<>();
    public final int MAX_PLAYERS = 5;
    private int starter;
    private int lastRoundWinner;

    public PlayerManager(int num, Deck deck) {
        for (int x = 1; x <= num; x++) {
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
        this.starter=0;
    }

    public int getStarter() {
        return starter;
    }

    public void setStarter(int starter) {
        this.starter = starter;
    }

    public int round() {
        int round = 1;
        LinkedList<Player>roundList = new LinkedList<>(this.players);
        LinkedList<Card> roundCardList = new LinkedList<>();
        Card leadCard;
        Player plr =roundList.get(starter);
        leadCard = lead(plr);
        roundCardList.add(leadCard);
        System.out.println("Player"+roundList.get(0).playerName+" played "+leadCard);
        roundList.remove(plr);


        for (int x=0;(roundList.size()!=0);(x)++){
            for(Player pl : roundList){
                if (pl.playerName==x+starter){
                    Card flw= follow(pl,leadCard);
                    System.out.println("Player"+roundList.get(0).playerName+" played "+flw);
                    roundCardList.add(flw);
                    roundList.remove(pl);

                    if(flw.value>leadCard.value&& Objects.equals(flw.suit, leadCard.suit)){
                        leadCard=flw;
                    }

                }
                else {
                break;}
            }
        //todo add next players action
        //for (players)
        //follow(player, lead)
        }
        int winValue = 0;
        for (Card crd: roundCardList) {
            if (Objects.equals(crd.suit, leadCard.suit)) {
                if (crd.value > winValue) {
                    winValue = crd.value;
                }
            }
        }
            for (Player o:players){
                for (Card a:o.hand){
                    if(a.value==winValue&&Objects.equals(a.suit, leadCard.suit)){
                        System.out.println("Player"+o.playerName+" won round"+round);
                        round++;
                        this.lastRoundWinner = o.playerName;
                    }
                }
            }

            for (Player p: players){
                for (Card cardd:roundCardList){
                    if (p.hand.contains(cardd)){
                        p.hand.remove(cardd);
                    }
                }

            }
            return lastRoundWinner;
        }


    public Card lead(Player player){

        int min=12;
        Card leadCard= player.hand.get(0);
        for (Card c:player.hand){
            if (c.value<min)
            {
                min=c.value;
                leadCard=c;
            }

        }
        //todo remove card from player hand
        return leadCard;
    }
    public Card follow(Player player, Card leadCard){
        LinkedList<Card> playerRoundCardList = new LinkedList<>();
        Card flw= player.hand.get(0);
        for (Card c:player.hand) {
            if (c.suit.equals(leadCard.suit)){
                playerRoundCardList.add(c);
            }
        }
        if (playerRoundCardList.size()!=0){
            for (Card l:playerRoundCardList){
                if (l.value>leadCard.value){
                    flw=l;
                }
            }
        }
        else {
            for (Card m:player.hand){
                if (m.value<flw.value)
                {flw=m;}
            }
        }
        return flw;
    }
}

