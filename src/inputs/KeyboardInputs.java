package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;


public class KeyboardInputs implements KeyListener {
	private GamePanel gamePanel;
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
        @Override
	public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE -> gamePanel.getGame().setPlayings(1);
                case KeyEvent.VK_ESCAPE -> System.exit(0);
		case KeyEvent.VK_W -> gamePanel.getGame().getplayer().setUp(true);
		case KeyEvent.VK_A -> gamePanel.getGame().getplayer().setLeft(true);
		case KeyEvent.VK_S -> gamePanel.getGame().getplayer().setDown(true);
		case KeyEvent.VK_D -> gamePanel.getGame().getplayer().setRight(true);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE -> gamePanel.getGame().setPlayings(1);
                case KeyEvent.VK_ESCAPE -> System.exit(0);
		case KeyEvent.VK_W -> gamePanel.getGame().getplayer().setUp(false);
		case KeyEvent.VK_A -> gamePanel.getGame().getplayer().setLeft(false);
		case KeyEvent.VK_S -> gamePanel.getGame().getplayer().setDown(false);
		case KeyEvent.VK_D -> gamePanel.getGame().getplayer().setRight(false);
		}

	}

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
