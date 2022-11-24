package main;

import entities.Coin;
import entities.FireBall;
import entities.Player;
import entities.Potion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;


public class Game {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
        private Player player;
        private ArrayList<Coin> coin = new ArrayList<Coin>();
        private ArrayList<FireBall> fire = new ArrayList<FireBall>();
        private Potion potion;
        private int potion_Health = 5;
        public int score = 0;
        public int yourScore = 0;
        private int check = 20;
        public int dmg = 3;
        private int playings = 0;
        private int Level = 1;
        
	public Game(){
            player = new Player(420,450,80,80);
            coin.add(new Coin(40,40));
            coin.add(new Coin(40,40));
            fire.add(new FireBall(60,60));
            potion = new Potion(40,40);
            gamePanel = new GamePanel(this);
            gameWindow = new GameWindow(gamePanel);
            gamePanel.requestFocus();

            Thread player_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            if(playings==1){
                                player.update();
                                checkPlayerGetCoin();
                                checkPlayerGetDmg();
                                checkPlayerGetpotion();
                                checkDead();
                                LevelUP();
                                gamePanel.repaint();
                                Thread.sleep(3);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            });
            Thread fireBall_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            if(playings==1){
                            fire.get(0).update();
                            gamePanel.repaint();
                            Thread.sleep(3);
                        }
                        } catch (Exception e) {
                        }   
                    }
                }
            });
            Thread coin_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            if(playings==1){
                            coin.get(0).update();
                            coin.get(1).update();
                            gamePanel.repaint();
                            Thread.sleep(3);
                        }
                        } catch (Exception e) {
                        }
                    }
                }
            });
            Thread porion_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            if(playings==1){
                                potion.update();
                                gamePanel.repaint();
                                Thread.sleep(3);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            });
                coin_thread.start();
                porion_thread.start();
                player_thread.start();
                fireBall_thread.start();
	}
        
        public void render(Graphics g){
            if(playings == 0){               
                g.setColor(new Color(0, 0, 0,150));
                g.fillRect(0, 0, 914, 987);
                g.setColor(new Color(255, 178, 102));
                g.setFont(new Font("Blackadder ITC",Font.PLAIN,120));
                g.drawString("Dungeon Odyssey", 90, 200);
                g.setFont(new Font("Agency FB",Font.BOLD,30));
                g.setColor(new Color(255, 204, 153));
                g.drawString("PRESS SPACE TO PLAY", 350, 300);
                g.setColor(Color.RED);
                g.drawString("PRESS ESCAPE TO EXIT", 350, 370);
                g.setFont(new Font("Agency FB",Font.BOLD,20));
                g.setColor(new Color(255, 178, 102));
                g.drawString("V 1.2.0.7", 840, 980);
            }
            else if(playings == 1){
                player.render(g);
                coin.get(0).render(g);
                coin.get(1).render(g);
                fire.get(0).render(g);
                potion.render(g);
         
                g.setFont(new Font("Agency FB",Font.BOLD,30));
                g.setColor(Color.WHITE); 
                g.drawString("Level : "+Level, 800, 34);
                g.drawString("Score : " + score, 800, 74);
            }
            else if(playings == 2){
                g.setColor(new Color(0, 0, 0,150));
                g.fillRect(0, 0, 914, 987);
                g.setFont(new Font("Blackadder ITC",Font.PLAIN,120));
                g.setColor(Color.RED);
                g.drawString("YOU DEAD", 90, 200);
                g.setFont(new Font("Agency FB",Font.BOLD,30));
                g.setColor(Color.WHITE);
                g.drawString("Your score is : "+yourScore, 380, 300);
                g.drawString("PRESS SPACE TO PLAY AGIAN", 330, 400);
                g.setColor(Color.RED);
                g.drawString("PRESS ESCAPE TO EXIT", 360, 470);
            }
        }

        public int getPlayings(){
            return playings;
        }

        public void setPlayings(int playings){
            this.playings = playings;
        }
        
        public void checkDead(){
            if(player.getHP()<=0){
                playings = 2;
                yourScore = score;
                
                player.setHP(50);
                player.setX(420);
                player.setY(450);
                score = 0;
                check = 20;
                dmg = 3;
                Level = 1;
                fire.get(0).setFire_speed_default(2);
                fire.get(0).setX(-100);
            }
        }
        
        public Player getplayer(){
            return player;
        }
        
        public void windowFocusLost(){
            player.resetDirBooleans();
        }
        
        public void checkPlayerGetCoin(){
            if(player.getPlayerArea().intersects(coin.get(0).getCoinArea()) && coin.get(0).isDelay() == false){
                coin.get(0).setDelay(true);
                score++;
            }
            else if(player.getPlayerArea().intersects(coin.get(1).getCoinArea()) && coin.get(1).isDelay() == false){
                coin.get(1).setDelay(true);
                score++;
            }
        }
        public void checkPlayerGetDmg(){
            if(player.getPlayerArea().intersects(fire.get(0).getCoinArea()) && fire.get(0).isCanDmg() == true){
                player.gotDMG(dmg);
                fire.get(0).setCanDmg(false);
            }
        }
        public void checkPlayerGetpotion(){
            if(player.getPlayerArea().intersects(potion.getPotionArea()) && potion.isDelay() == false){
                potion.setDelay(true);
                player.gotPotion(potion_Health,potion.potion);
            }
        }
        public void LevelUP(){
            if(score > check){
                Level++;
                dmg++;
                potion_Health+=5;
                check+=20;
                fire.get(0).setFire_speed(2);
            }
        }
}