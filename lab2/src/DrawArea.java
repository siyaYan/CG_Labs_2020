import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/*
 * DrawArea - a simple JComponent for drawing.  The "offscreen" BufferedImage is 
 * used to draw to,  this image is then used to paint the component.
 * Eric McCreath 2009 2015, 2017, 2019
 */

public class DrawArea extends JComponent implements MouseMotionListener, MouseListener {

	private BufferedImage offscreen;
	Dimension dim;
	DrawIt drawit;
	Point2D.Double previousPos;

	public DrawArea(Dimension dim, DrawIt drawit) {
		this.setPreferredSize(dim);
		offscreen = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		this.dim = dim;
		this.drawit = drawit;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		previousPos = null;
		clearOffscreen();
	}

	public void clearOffscreen() {
		Graphics2D g = offscreen.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		repaint();
	}

	public Graphics2D getOffscreenGraphics() {
		return offscreen.createGraphics();
	}

	public void drawOffscreen() {
		repaint();
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(offscreen, 0, 0, null);
	}

	public void mouseDragged(MouseEvent m) {

		Graphics2D g = offscreen.createGraphics();
		Point2D.Double pos = new Point2D.Double(m.getX(), m.getY());
		if (drawit.penToolbar.getSelectCommand() == Pens.PEN) {
			g.setColor((Color) drawit.colorToolbar.getSelectCommand());
			g.setStroke(new BasicStroke((float) drawit.thicknessSlider.getValue()));
			g.draw(new Line2D.Double(previousPos, pos));
		} else if (drawit.penToolbar.getSelectCommand() == Pens.SMUDGE) {
			smudge(pos, previousPos, offscreen,  drawit.thicknessSlider.getValue());
		}  else if (drawit.penToolbar.getSelectCommand() == Pens.SPRAY) {
			g.setColor((Color) drawit.colorToolbar.getSelectCommand());
			spray(pos, g,(float) drawit.thicknessSlider.getValue());
			
		}
		previousPos = pos;
		drawOffscreen();
	}
	
	// spray - spray point onto "g" at position "pos" with approximate radius "value" 
	Random r = new Random();
	private void spray(Point2D pos, Graphics2D g, float value) {
	     // place code here for your spray paint solution
		
		
	}

	
	// smudge - this creates a smudge on the offescreen image by mixing the colours around the "previous" point with colours around "pos".  
	// w gives width and height of the pen that is doing the smudging.  
	private void smudge(Point2D.Double pos, Point2D.Double previous, BufferedImage offscreen, int w) {
		// place your code here for your smudge solution 
		
		
	}

	public void mouseMoved(MouseEvent m) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		previousPos = new Point2D.Double(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void export(File file) {
		try {
			ImageIO.write(offscreen, "png", file);
		} catch (IOException e) {
			System.out.println("problem saving file");
		}
	}
}
