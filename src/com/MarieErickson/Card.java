package com.MarieErickson;

/**Card has a int value which matched string name (except Ace)
 *  but allows for use in string without parsing. also has String suit
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
//constructor takes 3 arguements
    public Card(String s, int v, String n){
        this.value = v;
        this.suit = s;
        this.name = n;

    }
//toString method for card/ used for user friendly experience
    public String toString(){

        return (this.name + " of " + this.suit);


    }


}
