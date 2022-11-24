package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow extends JFrame{
	public GameWindow(GamePanel gamePanel) {
            setTitle("Dungeon odyssey");
            setSize(1000,1025);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            add(gamePanel);
            pack();
            addWindowFocusListener(new WindowFocusListener() {
                    @Override
                    public void windowGainedFocus(WindowEvent e) {
                    }

                    @Override
                    public void windowLostFocus(WindowEvent e) {
                        gamePanel.getGame().windowFocusLost();
                    }
                });
	}

}
