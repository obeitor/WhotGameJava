/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softobt.whot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Abdul
 */
public class CardGame{

    /**
     * @param args the command line arguments
     */
    
    public Card_class current;
    public void changeCurrent(Card_class card)throws IllegalArgumentException
    {
        if((this.current.getShape().equalsIgnoreCase(card.getShape()))||(this.current.getNumber()==card.getNumber())){
            this.playedCards.add(this.current);//send the current card to the market
            this.current = card;//replace the current with the one dropped
        }
        
        else {throw new IllegalArgumentException("WRONG CARD!!!");}
    }
    public clsPlayer[] players ;
    public void newGame(){
        this.market = new ArrayList();
        this.playedCards = new ArrayList();
        for(Card_class card : Card_class.getCards()){
            market.add(card);
        }
        this.Shuffle();
        this.noPlayers = 4;
        this.players = new clsPlayer[this.noPlayers];
        this.share(5);
        this.current = this.market.get(0);
        this.market.remove(this.current);
        this.Current = this.players[1];
    }
    public void share(int noCards){
        for (int i=0; i<this.noPlayers; i++){
            this.players[i] = new clsPlayer("Player"+(i+1));//create new player
            for(int j=0; j<noCards; j++){
            this.players[i].pickCard(this);
            }
        }
    }
    public  ArrayList<Card_class> getShuffledCards(){
        return this.market;
    }
    public void Shuffle(){
        Collections.shuffle(market);
    }
    public void suspend(){
        
    }
    public void pick2(){
        
    }
    public void GeneralMarket(){
        
    }
    public void next(){
        int j=0;
        for (int i=0;i<this.players.length;i++) {
            if(this.players[i].equals(this.Current)){
              j=i;  
            }
        }
        j++;
        if(j>=this.players.length)j=0;
        this.Current = this.players[j];
    }
    protected  ArrayList<Card_class> market;
    protected ArrayList<Card_class> playedCards;
    private int noPlayers;
    private clsPlayer Current;
    protected clsPlayer getCurrentPlayer(){
        return this.Current;
    }
}
