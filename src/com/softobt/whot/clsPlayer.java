/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softobt.whot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Abdul
 */
public class clsPlayer {
    private ArrayList<Card_class> hand;
    private String name;
    public clsPlayer(String name){
        this.name = name; 
        hand = new ArrayList();
    }
    public ArrayList<Card_class> getCard(){
        return hand;
    }
    public String getName(){
        return this.name;
    }
    public void pickCard(CardGame game){
        if(game.market.isEmpty()){
            game.market = game.playedCards;
            game.Shuffle();
        }
        hand.add(game.market.get(0));
        game.market.remove(0);
    }
    public void dropCard(Card_class card, CardGame game)throws IllegalArgumentException
    {
        game.changeCurrent(card);
        hand.remove(card);
    }
    public String showCards(){
        String cards = "";
        for (Card_class card : hand)
            cards+= card.displayCard()+"\n";
        return cards;
    }
    
}
