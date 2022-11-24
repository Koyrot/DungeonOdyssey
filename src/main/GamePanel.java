package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import java.awt.image.BufferedImage;
import utilz.LoadFile;


public class GamePanel extends JPanel{
        
        private Game game;
        private BufferedImage bg;
        
	public GamePanel(Game game){
            bg = LoadFile.GetSprites(LoadFile.BG);
            this.game = game;
            setPreferredSize(new Dimension(914, 987));
            addKeyListener(new KeyboardInputs(this));
	}

        
        @Override
	public void paintComponent(Graphics g) {
            super.paintComponent(g);
                g.drawImage(bg, 0, 0,914,987,null);
                game.render(g);
	}
        
        public Game getGame(){
            return game;
        }
}
