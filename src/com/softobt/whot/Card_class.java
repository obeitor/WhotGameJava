/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softobt.whot;

import java.awt.Image;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;


/**
 *
 * @author Abdul
 */
public class Card_class {
    private final int number ;
    private final String shape;
    public int getNumber(){return number;}
    public String getShape(){return shape;}
    public String displayCard(){
        return this.shape+" : "+this.number;
    }
    protected final URL url;//= getClass().getResource("/res/whot.png");
    public Icon card;// = new ImageIcon(url);
    protected Card_class(int i,String Shape ,String url){
        this.number = i;
        this.shape = Shape;
        this.url= getClass().getResource(url);
        this.card = new ImageIcon(this.url);
    }
    private static Card_class[] pack;
    
    public static void setCards(){
        Card_class cards[] = new Card_class[49];
        int j = 0;
        for (int i=1; i<=14; i++,j++){//for angles
            if((i==6)||(i==9))j--;
            else{ cards[j] = new Card_class(i,"ANGLE","/res/angle"+i+".png");
            }
        }
        for (int i=1; i<=14; i++,j++){//for boxes
            if((i==4)||(i==6)||(i==8)||(i==9)||(i==12))j--;
            else {cards[j] = new Card_class(i,"BOX","/res/box"+i+".png");
            }
        }
        for (int i=1; i<=14; i++,j++){//for circle
            if((i==6)||(i==9))j--;
            else {cards[j] = new Card_class(i,"CIRCLE","/res/circle"+i+".png");
            }
        }
        for (int i=1; i<=14; i++,j++){//for cross
            if((i==4)||(i==6)||(i==8)||(i==9)||(i==12))j--;
            else {cards[j] = new Card_class(i,"CROSS","/res/cross"+i+".png");
            }
        }
        for (int i=1; i<=8; i++,j++){
            if(i==6)j--;
            else {cards[j] = new Card_class(i,"STAR","/res/star"+i+".png");
            }
        }
        Card_class.pack = cards;
    }
    public static Card_class[] getCards(){
        return pack;
    }
    
}
