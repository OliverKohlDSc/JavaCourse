import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageDemo {
	private MyPanel contentPane;
	
	public void displayUI() {
		JFrame frame = new JFrame("JMR-Image");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new MyPanel();
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	private class MyPanel extends JFrame {
		private BufferedImage image;
		
		public MyPanel() {
			try {
				image = ImageIO.read(MyPanel.class.getResource("/images/myimage.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public Dimension getDimension() {
			return image == null
					? new Dimension(400, 300) 
					: new Dimension(image.getWidth(), image.getHeight());	
		}

		@Override
		public void paintComponents(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponents(g);
			g.drawImage(image, 0, 0, this);
		}
		
	}
}
