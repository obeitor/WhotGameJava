/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softobt.whot;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Abdulgafar Obeitor
 */
public class whotGame extends JFrame{
    private final URL url = getClass().getResource("/res/whot.png");
    protected final Image favicon = new ImageIcon(url).getImage();
    private static final int w=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-10,
            h = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-20;
    //
    public static void main(String[] args){whotGame start = new whotGame();}
    
    public whotGame(){
          try{
            EventQueue.invokeAndWait(new Runnable(){
                @Override
                public void run(){
                    Card_class.setCards();
                setSize(w,h);
                setResizable(false);
                setLayout(null);//3 rows and 2 columns for the outer frame
                System.setProperty("bg", "0xA6E4AF");
                System.setProperty("drkGrn", "0xB0F88E");
                initComponents();
                getContentPane().setBackground(Color.CYAN);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("WHOT | WHOT | WHOT | WHOT");
                setIconImage(favicon);
                setVisible(true);
                temp();
                }
            });
        }
        catch(InterruptedException | InvocationTargetException exp){JOptionPane.showMessageDialog(null, exp.getMessage());
        }
    }
    private void initComponents(){
        URL s_url = getClass().getResource("/res/whot.png");
        ImageIcon gen_card = new ImageIcon(s_url);
        Game = new CardGame();Game.newGame();   
        CPU1Panel =  new JPanel(new FlowLayout(FlowLayout.TRAILING));CPU1Panel.setBackground(Color.CYAN);
        CPU2Panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));CPU2Panel.setBackground(Color.CYAN);
        CPU3Panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));CPU3Panel.setBackground(Color.CYAN);
        PlayerPanel = new JPanel(new GridLayout(1,2,5,0));PlayerPanel.setBackground(Color.CYAN);
        Market = new JLabel(""); nextPlayer = new JLabel("");
        playing_card = new JLabel("");
        CPU1 = new JLabel("CPU1");CPU2 = new JLabel("CPU2");CPU3 = new JLabel("CPU3");
        CPU1.setFont(new Font("Serif", Font.BOLD, 20));CPU2.setFont(new Font("Serif", Font.BOLD, 20));
        CPU3.setFont(new Font("Serif", Font.BOLD, 20));
        Market.setIcon(gen_card);UpdatePlayingCard();
        CPU1.setIcon(gen_card);CPU2.setIcon(gen_card);CPU3.setIcon(gen_card);
        CPU1.setIconTextGap(100);CPU2.setIconTextGap(100);CPU3.setIconTextGap(100);
        CPU1Panel.add(CPU1);CPU2Panel.add(CPU2);CPU3Panel.add(CPU3);
        CPU2Panel.setBounds(0,50,(w/2)-150,(h/2)-150);CPU1Panel.setBounds((w/2)+0,50,(w/2)-150,(h/2)-150);
        CPU3Panel.setBounds(0,(h/2)+150,(w/2)-150,(h/2)-150);PlayerPanel.setBounds((w/2)+50,(h/2)+150,(w/2)-150,(h/2)-150);
        Market.setBounds(w/5, (h/2)-100, 150, 150);playing_card.setBounds((w/2)-50, (h/2)-100, 150, 150);
        nextPlayer.setBounds((w/2)+200, (h/2)-100, 150, 200);nextPlayer.setFont(new Font("Serif", Font.BOLD,25));
        //player panel settings
        JPanel subPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));subPanel1.setBackground(Color.CYAN);
        JPanel subPanel2 = new JPanel(new GridLayout(5,1,0,5));subPanel2.setBackground(Color.CYAN);
        Player = new JLabel("");Player.setIcon(Game.players[0].getCard().get(0).card);subPanel1.add(Player);
        player_cards = new JComboBox();
        play = new JButton("Play Card");goToMarket = new JButton("Go to Market");sayLastCard = new JButton("Say LastCard"); 
        subPanel2.add(player_cards);subPanel2.add(play);subPanel2.add(goToMarket);subPanel2.add(sayLastCard);
        PlayerPanel.add(subPanel1);PlayerPanel.add(subPanel2);
        add(CPU1Panel);add(CPU2Panel);
        add(Market);add(playing_card);add(nextPlayer);
        add(CPU3Panel);add(PlayerPanel);
        
        //actionlisteners
        player_cards.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                selCard();
                }catch(Exception ex){}
        }
        });
        play.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    playCard();
                }
                catch(IllegalArgumentException exp){JOptionPane.showMessageDialog(null, exp.getMessage());}
            }
        });
        goToMarket.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    pickMarket();
                }
                catch(Exception exp){}
            }
        });
        
    }
    private void temp(){UpdatePlayerCards();selCard();extras();nextPlayer();
    }
    private void UpdatePlayerCards(){//update array of cards held by player
        player_cards.removeAllItems();//first remove all then readd
        for (Card_class card : Game.players[0].getCard()){
            player_cards.addItem(card.displayCard());
        }player_cards.setSelectedIndex(Game.players[0].getCard().size()-1);
    }
    private void UpdatePlayingCard(){//update playing card
        playing_card.setIcon(Game.current.card);
    }
    private void playCard()throws IllegalArgumentException
    { //first player drops card then playing card is updated with dropped card
        Game.players[0].dropCard(Game.players[0].getCard().get(player_cards.getSelectedIndex()), Game);
        UpdatePlayingCard();UpdatePlayerCards();Game.next();extras();nextPlayer();
    }
    private void selCard(){//show card selected by player
        Player.setIcon(Game.players[0].getCard().get(player_cards.getSelectedIndex()).card);
    }
    private void pickMarket(){
        Game.players[0].pickCard(Game);UpdatePlayerCards();Game.next();nextPlayer();
    }
    private void nextPlayer(){
        if(Game.getCurrentPlayer().getName().equalsIgnoreCase("Player1"))nextPlayer.setText("Next: YOU");
        else if(Game.getCurrentPlayer().getName().equalsIgnoreCase("Player2"))nextPlayer.setText("Next: CPU1");
        else if(Game.getCurrentPlayer().getName().equalsIgnoreCase("Player3"))nextPlayer.setText("Next: CPU2");
        else if(Game.getCurrentPlayer().getName().equalsIgnoreCase("Player4"))nextPlayer.setText("Next: CPU3");
        CPU1.setText("CPU1 : "+Game.players[1].getCard().size());
        CPU2.setText("CPU2 : "+Game.players[2].getCard().size());
        CPU3.setText("CPU3 : "+Game.players[3].getCard().size());
        cpuPlay();
    }
    private void extras(){//pick two, suspend, gen 
        if(Game.current.getNumber()==2){//pick two
            Game.getCurrentPlayer().pickCard(Game);
            Game.getCurrentPlayer().pickCard(Game);
        }
        else if(Game.current.getNumber()==8){//suspend
            Game.next();
        }
        else if(Game.current.getNumber()==14)
                {//gen, more work to do
            int i = 0;
            while(i<Game.players.length-1){//rotate round everyother person and back to the initial person
                Game.getCurrentPlayer().pickCard(Game);
                Game.next();i++;
            }
        }
    }
    private void cpuPlay()
    {UpdatePlayingCard();Winner();
        if(!Game.getCurrentPlayer().equals(Game.players[0])){//as long as its not player
//     try{
//            Thread.sleep(2000);//delay 2s
//        }
//       catch(InterruptedException e){//Thread.currentThread().interrupt();
//        JOptionPane.showMessageDialog(null, "Something Went Wrong");}
        Boolean played = false;
            for(Card_class card : Game.getCurrentPlayer().getCard()){
                if((card.getNumber()==14)&&
                        ((card.getNumber()==Game.current.getNumber())||(card.getShape().equalsIgnoreCase(Game.current.getShape())))){
                    Game.getCurrentPlayer().dropCard(card, Game);//drop the card
                    played=true;break;//go to next, check if pick two or gen, print the nextplayer
                } 
            }
            if(!played)
                for(Card_class card : Game.getCurrentPlayer().getCard()){
                    if(((card.getNumber()==2)||(card.getNumber()==8))&&
                        ((card.getNumber()==Game.current.getNumber())||(card.getShape().equalsIgnoreCase(Game.current.getShape())))){
                    Game.getCurrentPlayer().dropCard(card, Game);//drop the card
                    played=true;break;//go to next, check if pick two or gen, print the nextplayer
                    }
                }
            if(!played)
                for(Card_class card : Game.getCurrentPlayer().getCard()){
                    if ((card.getNumber()==Game.current.getNumber())||(card.getShape().equalsIgnoreCase(Game.current.getShape()))){
                        Game.getCurrentPlayer().dropCard(card, Game);//drop the card
                    played=true;break;//go to next, check if pick two or gen, print the nextplayer
                    }
                }
            if(!played){
                Game.getCurrentPlayer().pickCard(Game);
            }
            else {extras();UpdatePlayerCards();}Game.next();nextPlayer();
        }
    }
    private void Winner(){
        for(clsPlayer player : Game.players){
            if(player.getCard().isEmpty()){
                String winner="";
                if(player.getName().equalsIgnoreCase("Player1"))winner="YOU";
                else if(player.getName().equalsIgnoreCase("Player2"))winner="CP1";
                else if(player.getName().equalsIgnoreCase("Player3"))winner="CPU2";
                else if(player.getName().equalsIgnoreCase("Player4"))winner="CPU3";
                JOptionPane.showMessageDialog(null, "The WINNER is "+winner);
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
    //declare components
    CardGame Game;
    JPanel CPU1Panel ,CPU2Panel, CPU3Panel,PlayerPanel;
    JLabel Market;
    JLabel playing_card;
    JLabel CPU1,CPU2,CPU3,Player;
    JComboBox player_cards;
    JButton play;
    JButton goToMarket;
    JButton sayLastCard;
    JLabel LastCard;
    JLabel nextPlayer;
}
