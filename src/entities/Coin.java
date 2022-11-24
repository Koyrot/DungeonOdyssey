/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import utilz.LoadFile;

/**
 *
 * @author koyrot
 */
public class Coin extends Entity{
    private boolean delay = true;
    public int counter = 0;
    private Random random;
    public int score = 0;
    
    public Coin(int sX,int sY){
        super(0,0,sX,sY);
        loadAnimations();
    }
    
    @Override
    public void loadAnimations() {
        BufferedImage img = LoadFile.GetSprites(LoadFile.COIN);

        animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage((i*32)+50, (j*32)+8, 32, 32);
    }
    
    @Override
    public void update(){
        if(delay == true){
            Delay();
        }
        else if(delay == false){
            updateAnimationTick();
            CoinMiss();
        }
    }
    
    @Override
    public void render(Graphics g){
        if(delay == false){
            g.drawImage(animations[0][aniIndex],(int)x,(int)y,scaleX,scaleY, null);
            //g.drawRect((int)x,(int)y,scaleX,scaleY);
        }
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 4){
                aniIndex = 0;
            }
        }
    }
    
    public void Delay(){
        counter++;
        if(counter > 280){
            delay = false;
            counter = 0;
            random = new Random();
            x =  random.nextInt(840);
            y =  random.nextInt(336) + 300;
        }
    }
    public void CoinMiss(){
        counter++;
        if(counter > 400){
            //System.out.println("Coin miSs1");
            delay = true;
            counter = 0;
        }
    }
    
    public Rectangle getCoinArea(){
        return new Rectangle(x,y,scaleX,scaleY);
    }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
