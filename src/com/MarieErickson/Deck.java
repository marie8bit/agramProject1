package com.MarieErickson;

/**
 * Created by Marie on 10/24/2016.
 */
import java.util.*;
public class Deck {
    String[] suites = {"Hearts", "Spades", "Clubs", "Diamonds"};
    String[] numsNames = {"Ace", "3", "4", "5", "6", "7", "8", "9", "10"};
    int[] nums = {11, 3, 4, 5, 6, 7, 8, 9, 10};
    //store cards in a hashmap to allow for repetive suites and values
    LinkedList<Card> deck = new LinkedList<>();
    protected Random rng;

    public void setRng(Random rng) {
        this.rng = rng;
    }

    public Deck() {
        rng = new Random();
        //variable to count 52 cards
    int x = 0;
    //identify suite of card
        for(int y = 0; y<suites.length;y++) {
        //identify value of card
        for (int z = 0; z < nums.length; z++) {
            Card card = new Card(suites[y], nums[z], numsNames[z]);
            //add card to linkedlist
            deck.add(card);
            x++;

        }
    }
    Card chief= null;
    for (Card cd:deck) {
        if (cd.suit.equals("Spades") && cd.value == 11) {
            chief = cd;
        }

    }
    if(chief!=null) {
        deck.remove(chief);
    }
}
    public String toString(){
        String message="";
        for (Card crd:deck) {
            message += "\n"+crd.name + " of " + crd.suit;

        }
        return message;
    }
    public Card deal(){
        int cardsLeft = this.deck.size();
        int cardpick = rng.nextInt(cardsLeft);
        return deck.remove(cardpick);

    }
}