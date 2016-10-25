package com.MarieErickson;

/**
 * Created by Marie on 10/24/2016.
 */


public class Card {


    String suit;
    int value;
    String name;

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Card(String s, int v, String n){
        this.value = v;
        this.suit = s;
        this.name = n;

    }

    public String toString(){

        return (this.name + " of " + this.suit);


    }


}
