package utilz;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class LoadFile {
    public static final String PLAYER = "JK_withbg.png";
    public static final String COIN = "Coin.png";
    public static final String BG = "BG4.jpg";
    public static final String FIREBALL = "FireBalls.png";
    public static final String POTION = "Potions.png";
    public static final String HP_BAR = "HP_bar.png";
    
    
    public static BufferedImage GetSprites(String filename){
        BufferedImage img = null;
        InputStream is = LoadFile.class.getClassLoader().getResourceAsStream("ress\\" + filename);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
                
	}    
}