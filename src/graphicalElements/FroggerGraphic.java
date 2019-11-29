package graphicalElements;

import javax.imageio.ImageIO;
import javax.swing.*;

import gameCommons.IFrog;
import gameCommons.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private ArrayList<BufferedImage> sprites;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	private float time;

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(new Color(6, 78, 7));
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		JLabel emptyLabel = new JLabel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emptyLabel.setPreferredSize(new Dimension(16, 14));
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);
		sprites = new ArrayList<BufferedImage>();
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadImages() throws IOException {
		String path = System.getProperty("user.dir") + "/src";
		for (int i = 1; i <= 4; i++) {
			System.out.println(path + "/sprites/car_1_" + i + ".png");
			sprites.add(ImageIO.read(new File(path + "/sprites/car_1_" + i + ".png")));
		}
		sprites.add(ImageIO.read(new File(path + "/sprites/car_2_1.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/car_2_2.png")));
		for (int i = 1; i <= 3; i++) {
			sprites.add(ImageIO.read(new File(path + "/sprites/frog_idle_" + i + ".png")));
			sprites.add(ImageIO.read(new File(path + "/sprites/frog_jump_1_" + i + ".png")));
			sprites.add(ImageIO.read(new File(path + "/sprites/frog_jump_2_" + i + ".png")));
		}
		sprites.add(ImageIO.read(new File(path + "/sprites/log_end.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/log_mid.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/log_front.png")));
	}

	private static BufferedImage create(BufferedImage image, double angle,
	                                   GraphicsConfiguration gc) {
		double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
		int w = image.getWidth(), h = image.getHeight();
		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
				* cos + w * sin);
		int transparency = image.getColorModel().getTransparency();
		BufferedImage result = gc.createCompatibleImage(neww, newh, transparency);
		Graphics2D g = result.createGraphics();
		g.translate((neww - w) / 2, (newh - h) / 2);
		g.rotate(angle, w / 2, h / 2);
		g.drawRenderedImage(image, null);
		return result;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			if (e.isSprite) {
				if (e.spriteID >= 6 && e.spriteID <= 14) {
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					GraphicsDevice gd = ge.getDefaultScreenDevice();
					GraphicsConfiguration gc = gd.getDefaultConfiguration();

					int dir = 0;
					if (frog.getDirection() == Direction.right) dir = 1;
					if (frog.getDirection() == Direction.down) dir = 2;
					if (frog.getDirection() == Direction.left) dir = 3;
					BufferedImage rotated = create(sprites.get(e.spriteID), dir*Math.PI/2, gc);
					g.drawImage(rotated, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
				} else {
					g.drawImage(sprites.get(e.spriteID), pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
				}
			} else {
				g.setColor(e.color);
				g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
			}
		}
		g.drawString(Float.toString(this.time), width-5, height-5);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void timer(float time) {
		this.time = time;
	}

	public void endGameScreen(String s) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}

}
