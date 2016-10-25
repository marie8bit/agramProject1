package com.MarieErickson;
import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Deck deck = new Deck();
        System.out.println(deck);
        PlayerManager plMgmt = new PlayerManager(2, deck);
        System.out.println(deck);
        for (Player plyr:plMgmt.players){
            System.out.println(plyr.hand.toString());
        }
    }
}
