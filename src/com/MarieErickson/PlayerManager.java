package com.MarieErickson;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

/**
 * !only allows for one human player and 1 computer player
 * Design an object oriented program that plays the game with you.
 * You'll need to decide how the computer will strategize and play.
 * Think about what objects there are in the game  - cards, players, hands, points...
 * what is each responsible for? How do they interact? Think about how your program will
 * interact with the user - make sure you validate input, and make the game as easy to operate as possible.
 * Created by Marie on 10/25/2016.
 */
public class PlayerManager {
    //initialize playermanager properties
    LinkedList<Player> players = new LinkedList<>();
    //not used
    public final int MAX_PLAYERS = 5;
    private int starter;
    private int round = 1;
    //not used
    public String again = "Y";
    public int getLastRoundWinner() {
        return lastRoundWinner;
    }
    private int lastRoundWinner;
    //contructor that takes number of players and deck
    public PlayerManager(int num, Deck deck) {
        for (int x = 1; x <= num; x++) {
            Player ply = new Player(x);
            players.add(ply);
        }

        int dealRounds = 0;
        //deals players hands
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
    //methods and behaviors for round code to play rounds of agram game
    public void round() {
        //initializes list of card used in round
        LinkedList<Card> roundCardList = new LinkedList<>();
        //identifies card that set suit
        Card leadCard;
        //human player is player 1
        Player human = players.get(0);

        //clones player list for round
        LinkedList<Player> roundList = new LinkedList<>(this.players);
        //identifies first player for round
        Player plr = roundList.get(starter);
        //code for if first round player is human or computer
        if (!Objects.equals(plr.playerName, human.playerName)) {
            //calls lead card method (strategy)
            leadCard = lead(plr);
            roundCardList.add(leadCard);
            System.out.println("Player" + plr.playerName + " played " + leadCard);
            //removes player from list so they only play once
            roundList.remove(plr);
            //starter++;
        } else {
            //ifhuman polayer is first player
            System.out.println("Enter the number of the card you want to play");
            int x = 0;
            //[ovides information to user about available cards
            for (Card ob : human.hand) {
                System.out.println(x + ". " + ob);
                x++;
            }
            //calls user input validation
            int select = isValidData(human.hand);
            //sets lead card for round
            leadCard = human.hand.get(select);
            roundCardList.add(leadCard);
            roundList.remove(human);
        }
        //gets computer card if it didn't start the round
        if (!roundList.contains(human)) {
            //not useful , was hoping to get more players. couldn't get the player to play in order
            for (int x = 2; (roundList.size() != 0); (x)++) {
                LinkedList<Player> flwList = new LinkedList<>(roundList);
                for (Player pl : flwList) {
                    //calls follow method
                    Card flw = follow(pl, leadCard);
                    //provide card information to user
                    System.out.println("Player" + pl.playerName + " played " + flw);
                    //add card to round list for winner evaluation
                    roundCardList.add(flw);
                    //remove player from round
                    roundList.remove(pl);

                    if (flw.value > leadCard.value && Objects.equals(flw.suit, leadCard.suit)) {
                        //changes winning card
                        leadCard = flw;
                    }
                }
            }
            //gets user card if they didn't start the round
        } else {
            LinkedList<Card> playerRoundCardList = new LinkedList<>();
            System.out.println("Enter the number of the card you want to play");
            int display = 0;
            //identifies playable cards/follows rules
            for (Card c : human.hand) {
                if (Objects.equals(c.suit, leadCard.suit)) {
                    //adds cards to list
                    playerRoundCardList.add(c);
                }
            }
            //displays playable cards
            for (Card c : playerRoundCardList) {
                System.out.println(display + ". " + c);
                display++;
            }
            //displays all cards if player doesn't have any cards of the right suit
            //player loses
            if (playerRoundCardList.isEmpty()) {
                for (Card c : human.hand) {
                    System.out.println(display + ". " + c);
                    display++;
                    //adds cards to list
                    playerRoundCardList.add(c);
                }
            }
            //gets valid data from user for card selection
            int select = isValidData(playerRoundCardList);
            //gets card from user input
            Card selection = playerRoundCardList.get(select);
            roundCardList.add(selection);
            roundList.remove(human);
            //identifies winning card
            if (selection.value > leadCard.value && Objects.equals(selection.suit, leadCard.suit)) {
                leadCard = selection;
            }
        }
        int winValue = 0;
        //reviews all cards for winning value
        for (Card crd : roundCardList) {
            if (Objects.equals(crd.suit, leadCard.suit)) {
                if (crd.value > winValue) {
                    winValue = crd.value;
                }
            }
        }
        for (Player o : players) {

            for (Card a : o.hand) {
                //finds player who has winning card in thier hand
                if (a.value == winValue && Objects.equals(a.suit, leadCard.suit)) {
                    System.out.println("Player" + o.playerName + " won round " + round);
                    //provided round count to user
                    round++;
                    //provides game winner value from final round
                    this.lastRoundWinner = o.playerName;
                    //sets starter for next round
                    this.starter = o.playerName - 1;
                }
            }
        }
        //remove played cards from player hands
        for (Player p : players) {
            for (Card cardd : roundCardList) {
                if (p.hand.contains(cardd)) {
                    p.hand.remove(cardd);
                }
            }
        }
    }
    //handle invalid data for non numeric numbers and numbbers outside of index options
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
    //strategy to get rid of low cards early in the game, sets suit
    //last round is the only round that counts, player will only have one card left
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
    //identifies card that has the same suit as round leading card
    public Card follow(Player player, Card leadCard){
        LinkedList<Card> playerRoundCardList = new LinkedList<>();
        Card flw= player.hand.get(0);
        for (Card c:player.hand) {
            if (c.suit.equals(leadCard.suit)){
                playerRoundCardList.add(c);
            }
        }
        //plays card with matching suit
        if (playerRoundCardList.size()!=0) {
            //tries to win round
            for (Card l : playerRoundCardList) {
                if (l.value > leadCard.value) {
                    flw = l;
                }
                //plays lowest value of playable cards if win is not possible
                else {
                    int min = 12;
                    for (Card n : playerRoundCardList) {
                        if (n.value < min) {
                            min = n.value;
                            flw = n;
                        }
                    }
                }
            }
        }
        else {
            //get lowest possible card value from hand to sacrifice
            for (Card m:player.hand){
                if (m.value<flw.value)
                {flw=m;}
            }
        }
        return flw;
    }
}

