package com.MarieErickson;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Marie on 10/25/2016.
 */
public class PlayerManager {
    LinkedList<Player> players = new LinkedList<>();
    public final int MAX_PLAYERS = 5;
    private int starter;
    private int round = 1;
    public String again = "Y";
    public int getLastRoundWinner() {
        return lastRoundWinner;
    }
    private int lastRoundWinner;
    public PlayerManager(int num, Deck deck) {
        for (int x = 1; x <= num; x++) {
            Player ply = new Player(x);
            players.add(ply);
        }

        int dealRounds = 0;
        while (dealRounds <= num) {
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
    Scanner scan = new Scanner(System.in);
    public void round() {


        LinkedList<Card> roundCardList = new LinkedList<>();
        Card leadCard;
        Player human = players.get(0);

        //while (!human.hand.isEmpty()) {
        LinkedList<Player> roundList = new LinkedList<>(this.players);
        Player plr = roundList.get(starter);
        if (!Objects.equals(plr.playerName, human.playerName)) {
            leadCard = lead(plr);
            roundCardList.add(leadCard);
            System.out.println("Player" + plr.playerName + " played " + leadCard);
            roundList.remove(plr);
            //starter++;
        } else {

            System.out.println("Enter the number of the card you want to play");
            int x = 0;
            for (Card ob : human.hand) {
                System.out.println(x + ". " + ob);
                x++;
            }

            int select = isValidData(human.hand);
            leadCard = human.hand.get(select);
            roundCardList.add(leadCard);
            roundList.remove(human);
        }
        if (!roundList.contains(human)) {
            for (int x = 2; (roundList.size() != 0); (x)++) {
                LinkedList<Player> flwList = new LinkedList<>(roundList);
                for (Player pl : flwList) {
                    if (pl.playerName == x + starter) {
                        Card flw = follow(pl, leadCard);
                        System.out.println("Player" + pl.playerName + " played " + flw);
                        roundCardList.add(flw);
                        roundList.remove(pl);
                        //starter++;
                        if (flw.value > leadCard.value && Objects.equals(flw.suit, leadCard.suit)) {
                            leadCard = flw;
                        }
                    }
                }
                if (roundList.size() != 0) {
                    starter = 0;
                }
            }
        } else {
            LinkedList<Card> playerRoundCardList = new LinkedList<>();
            System.out.println("Enter the number of the card you want to play");
            int display = 0;
            for (Card c : human.hand) {
                if (Objects.equals(c.suit, leadCard.suit)) {
                    playerRoundCardList.add(c);
                }
            }
            for (Card c : playerRoundCardList) {
                System.out.println(display + ". " + c);
                display++;
            }
            if (playerRoundCardList.isEmpty()) {
                for (Card c : human.hand) {
                    System.out.println(display + ". " + c);
                    display++;
                    playerRoundCardList.add(c);
                }
            }
                int select = isValidData(playerRoundCardList);
                Card selection = playerRoundCardList.get(select);
                roundCardList.add(selection);
                roundList.remove(human);

            if (selection.value > leadCard.value && Objects.equals(selection.suit, leadCard.suit)) {
                leadCard = selection;
            }
        }
        int winValue = 0;
        for (Card crd : roundCardList) {
            if (Objects.equals(crd.suit, leadCard.suit)) {
                if (crd.value > winValue) {
                    winValue = crd.value;
                }
            }
        }
        for (Player o : players) {

            for (Card a : o.hand) {

                if (a.value == winValue && Objects.equals(a.suit, leadCard.suit)) {
                    System.out.println("Player" + o.playerName + " won round " + round);
                    round++;
                    this.lastRoundWinner = o.playerName;
                    this.starter = o.playerName - 1;
                }
            }
        }
        for (Player p : players) {
            for (Card cardd : roundCardList) {
                if (p.hand.contains(cardd)) {
                    p.hand.remove(cardd);
                }
            }
        }
    }
    private int isValidData(LinkedList<Card>test) {
        Scanner scanner = new Scanner(System.in);
        String message="Please choose a number in the list";
        while (true) {
            try {
                String stringInput = scanner.nextLine();
                int intInput = Integer.parseInt(stringInput);
                Card tester=test.get(intInput);
                if (intInput >= 0) {
                    return intInput;
                } else {
                    System.out.println(message);
                    continue;
                }
            } catch (NumberFormatException ime) {
                System.out.println(message);
            }
            catch(IndexOutOfBoundsException iob){
                System.out.println(message);
            }
        }
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

