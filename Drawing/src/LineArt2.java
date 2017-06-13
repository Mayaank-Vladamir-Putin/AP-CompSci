import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LineArt2 extends JFrame {

	public static void main(String[] args) {
		LineArt2 app = new LineArt2();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void pause(int milliseconds) {
		try {
			Thread.currentThread();
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public LineArt2() {
		super("Lineart 2");
		setSize(1000, 1000);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 1000, 1000);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		int a = 460, b = 10;
		int gap = 11;

		for (int i = 0; i <= gap; i++) {
			g2.setColor(Color.BLUE);
			a += 40;
			b += 40;
			g2.drawLine(a, 50, 950, b);
			pause(10);
		}
		a = 460;
		b = 950;
		for (int i = 0; i <= (gap + 1); i++) {
			g2.setColor(Color.GREEN);
			g2.drawLine(950, a, b, 950);
			a += 40;
			b -= 40;
			pause(10);
		}
		a = 540;
		b = 990;
		for (int i = 0; i <= gap; i++) {
			g2.setColor(Color.YELLOW);
			a -= 40;
			b -= 40;
			g2.drawLine(a, 950, 50, b);
			pause(10);
		}
		a = 500;
		b = 50;
		for (int i = 0; i <= gap; i++) {
			g2.setColor(Color.PINK);
			g2.drawLine(50, a, b, 50);
			a -= 40;
			b += 40;
			pause(10);
		}

	}

}