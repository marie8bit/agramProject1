package com.MarieErickson;

/**
 * Deck is not normal 52 card deck
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
//contructor for deck
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
    //removes chief/not included in deck
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
//toString method for deck/used in testing
    public String toString(){
        String message="";
        for (Card crd:deck) {
            message += "\n"+crd.name + " of " + crd.suit;

        }
        return message;
    }
    //deals random card from the cards that are left in the deck
    public Card deal(){
        int cardsLeft = this.deck.size();
        int cardpick = rng.nextInt(cardsLeft);
        return deck.remove(cardpick);

    }
}