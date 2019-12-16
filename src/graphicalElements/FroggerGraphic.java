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
	private ArrayList<IFrog> frogs;
	private JFrame frame;
	private float time;

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>(); //type de l'ArrayList pas necessaire car implicite, conservé pour une
													//meilleure lisibilité
		frogs = new ArrayList<IFrog>(); //type de l'ArrayList pas necessaire car implicite, conservé pour une
										//meilleure lisibilité

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
		sprites = new ArrayList<BufferedImage>(); //type de l'ArrayList pas necessaire car implicite, conservé pour une
												//meilleure lisibilité
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Charge tous les sprites du jeu
	 * @throws IOException Necessaire pour ne pas utiliser de try catch
	 */
	private void loadImages() throws IOException {
		String path = System.getProperty("user.dir") + "/src";
		for (int i = 1; i <= 4; i++) {
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
		sprites.add(ImageIO.read(new File(path + "/sprites/log_mid_1.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/log_front.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/eagle.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/road.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/water.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/grass.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/oil.png")));
		sprites.add(ImageIO.read(new File(path + "/sprites/lilypad.png")));
	}

	/**
	 * Cree une image tournee selon un certain angle
	 * @param image L'image a tourner
	 * @param angle L'angle de la rotation
	 * @param gc La configuration graphique du jeu
	 * @return Une image tournee
	 */
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
		g.rotate(angle, w / 2., h / 2.);
		g.drawRenderedImage(image, null);
		return result;
	}

	/**
	 * Affiche tous les elements
	 * @param g La liste de tous les elements
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int frogNb = 0;
		for (Element e : elementsToDisplay) {
			if (e.isSprite) {
				if (e.spriteID >= 6 && e.spriteID <= 14) {
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					GraphicsDevice gd = ge.getDefaultScreenDevice();
					GraphicsConfiguration gc = gd.getDefaultConfiguration();
					IFrog frog = frogs.get(frogNb);
					int dir = 0;
					if (frog.getDirection() == Direction.right) dir = 1;
					if (frog.getDirection() == Direction.down) dir = 2;
					if (frog.getDirection() == Direction.left) dir = 3;
					BufferedImage rotated = create(sprites.get(e.spriteID+3*frogNb), dir * Math.PI / 2, gc);
					g.drawImage(rotated, pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), null);
					frogNb++;
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

	/**
	 * Verifie si une touche est pressee
	 * @param e L'identifiant de la touche
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Regarde si la touche a ete relachee
	 * @param e La touche pressee
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Met a jour la direction de la grenouille en fonction de la touche recue
	 * @param e L'identifiant d'une touche
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				frogs.get(0).move(Direction.up);
				break;
			case KeyEvent.VK_DOWN:
				frogs.get(0).move(Direction.down);
				break;
			case KeyEvent.VK_LEFT:
				frogs.get(0).move(Direction.left);
				break;
			case KeyEvent.VK_RIGHT:
				frogs.get(0).move(Direction.right);
		}
		if (frogs.size() > 1) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_Z:
					frogs.get(1).move(Direction.up);
					break;
				case KeyEvent.VK_S:
					frogs.get(1).move(Direction.down);
					break;
				case KeyEvent.VK_Q:
					frogs.get(1).move(Direction.left);
					break;
				case KeyEvent.VK_D:
					frogs.get(1).move(Direction.right);
			}
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	/**
	 * Ajoute un element a afficher
	 * @param e L'element a afficher
	 */
	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	/**
	 * Setup une grenouille
	 * @param frog une grenouille
	 */
	public void setFrog(IFrog frog) {
		this.frogs.add(frog);
	}

	/**
	 * Met a jour le timer
	 * @param time le temps en secondes
	 */
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
