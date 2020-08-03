import java.awt.Dimension;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Lab4Q1 implements GLEventListener {

	/**
	 * Simple3DOGL - this is a simple 3D scene views a cube from a camera that spins
	 * around the scene. Eric McCreath 2009, 2011, 2015, 2019
	 * 
	 * 
	 */

	JFrame jf;
	GLJPanel gljpanel;
	Dimension dim = new Dimension(800, 600);
	FPSAnimator animator;

	float rotation;
	float speed;

	// set up the OpenGL Panel within a JFrame
	public Lab4Q1() {
		jf = new JFrame();
		gljpanel = new GLJPanel();
		gljpanel.addGLEventListener(this);
		gljpanel.requestFocusInWindow();
		jf.getContentPane().add(gljpanel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setPreferredSize(dim);
		jf.pack();
		animator = new FPSAnimator(gljpanel, 20);
		rotation = 0.0f;
		speed = 0.1f;
		animator.start();
	}

	public static void main(String[] args) {
		new Lab4Q1();
	}

	public void init(GLAutoDrawable dr) { // set up openGL for 2D drawing
		GL2 gl2 = dr.getGL().getGL2();
		GLU glu = new GLU();
		GLUT glut = new GLUT();
		gl2.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		gl2.glEnable(GL2.GL_DEPTH_TEST);

		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glLoadIdentity();
		glu.gluPerspective(60.0, 1.0, 100.0, 800.0);

	}

	public void display(GLAutoDrawable dr) { // clear the screen and draw the box

		GL2 gl2 = dr.getGL().getGL2();
		GLU glu = new GLU();
		GLUT glut = new GLUT();

		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glLoadIdentity();
		glu.gluLookAt(500.0 * Math.sin(rotation / 10.0), 200.0, 500.0 * Math.cos(rotation / 10.0), 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0);

		gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// draw the box
		sideRotatedColorScaled(gl2, 100.0, 1.0f, 0.0f, 0.0f, 0.0, 0.0, 1.0, 0.0, 50.0);
		sideRotatedColorScaled(gl2, 100.0, 0.0f, 1.0f, 0.0f, 90.0, 0.0, 1.0, 0.0, 50.0);
		sideRotatedColorScaled(gl2, 100.0, 0.0f, 0.0f, 1.0f, 180.0, 0.0, 1.0, 0.0, 50.0);
		sideRotatedColorScaled(gl2, 100.0, 0.0f, 1.0f, 1.0f, 270.0, 0.0, 1.0, 0.0, 50.0);

		// draw the floor
		sideRotatedColorScaled(gl2, 300.0, 0.0f, 0.0f, 0.0f, 90.0, 1.0, 0.0, 0.0, 50.0);

		gl2.glFlush();
		// dr.swapBuffers();
		rotation += speed;
		if (rotation > 360.9f)
			rotation = 0.0f;
	}

	// draw a single side with a set color and orientation
	private void sideRotatedColorScaled(GL2 gl2, double s, float r, float g, float b, double a, double ax, double ay,
			double az, double zoffset) {
		gl2.glPushMatrix();
		gl2.glRotated(a, ax, ay, az);
		gl2.glColor3f(r, g, b);
		gl2.glTranslated(0.0, 0.0, zoffset);
		gl2.glScaled(s, s, s);

		side(gl2);
		gl2.glPopMatrix();
	}

	// draw a side
	private void side(GL2 gl2) {
		gl2.glBegin(GL2.GL_POLYGON);
		gl2.glVertex3d(-0.5, -0.5, 0.0);
		gl2.glVertex3d(-0.5, 0.5, 0.0);
		gl2.glVertex3d(0.5, 0.5, 0.0);
		gl2.glVertex3d(0.5, -0.5, 0.0);
		gl2.glEnd();
	}

	public void dispose(GLAutoDrawable glautodrawable) {
	}

	public void reshape(GLAutoDrawable dr, int x, int y, int width, int height) {
	}
}
