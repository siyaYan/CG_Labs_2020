import java.awt.Dimension;import javax.swing.JFrame;//import com.opengl.GL;import com.jogamp.opengl.GL;import com.jogamp.opengl.GL2;import com.jogamp.opengl.GLAutoDrawable;import com.jogamp.opengl.GLEventListener;import com.jogamp.opengl.awt.GLJPanel;import com.jogamp.opengl.glu.GLU;import com.jogamp.opengl.util.FPSAnimator;import com.jogamp.opengl.util.gl2.GLUT;public class Lab4Q2 implements GLEventListener {	/**	 * Simple3DOGL - this is a simple 3D scene views a cube from a camera that spins	 * around the scene. Eric McCreath 2009, 2011, 2015, 2019	 * Xiran Yan	 * u7167582	 */	JFrame jf;	GLJPanel gljpanel;	Dimension dim = new Dimension(800, 600);	FPSAnimator animator;	float rotation;	float speed;	// set up the OpenGL Panel within a JFrame	public Lab4Q2() {		jf = new JFrame();		gljpanel = new GLJPanel();		gljpanel.addGLEventListener(this);		gljpanel.requestFocusInWindow();		jf.getContentPane().add(gljpanel);		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		jf.setVisible(true);		jf.setPreferredSize(dim);		jf.pack();		animator = new FPSAnimator(gljpanel, 20);		rotation = 0.0f;		speed = 0.8f;		animator.start();	}	public static void main(String[] args) {		new Lab4Q2();	}	public void init(GLAutoDrawable dr) { // set up openGL for 2D drawing		GL2 gl2 = dr.getGL().getGL2();		GLU glu = new GLU();		GLUT glut = new GLUT();		gl2.glClearColor(1.0f, 1.0f, 1.0f, 0f);		gl2.glEnable(GL2.GL_DEPTH_TEST);		gl2.glMatrixMode(GL2.GL_PROJECTION);		gl2.glLoadIdentity();		glu.gluPerspective(60.0, 1.0, 100.0, 1600.0);	}	public void display(GLAutoDrawable dr) { // clear the screen and draw the box		GL2 gl2 = dr.getGL().getGL2();		GLU glu = new GLU();		GLUT glut = new GLUT();		gl2.glMatrixMode(GL2.GL_MODELVIEW);		gl2.glLoadIdentity();		glu.gluLookAt(0 , 300, 600 , 0.0, 0.0, 0.0, 0.0,1.0, 0.0);		gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);		// draw the spining top		for (float i = 0,j=0;i < 10&&j<=1; j+=0.1,i+=1) {			sideRotatedColorScaled(gl2, 1, 1.0f, 1f-j, 1f-j, i*36,rotation);		}		// draw the floor(rotate=-1 means stop rotation,zoffset need to be the same as height of ractangular)		floor(gl2, 1, 1, 0.0f, 0.0f,0.0f, 0.0, -1);		rotation += speed;		if (rotation > 360.0f)			rotation = 0.0f;		gl2.glFlush();	}	// draw a single ractangular with a set color and orientation	private void sideRotatedColorScaled(GL2 gl2, double scale, float r, float g, float b, double a,double rotate) {		gl2.glPushMatrix();		gl2.glColor3f(r, g, b);		gl2.glRotated(rotate+a,0, 1,0);		gl2.glRotated(18,1, 0, 0);		gl2.glScaled(scale, scale, scale);		triangular(gl2);		gl2.glPopMatrix();	}	//draw the floor with a set color and orientation	private void floor(GL2 gl2, double scale1, double scale2 ,float r, float g, float b, double zoffset,double rotate) {		gl2.glPushMatrix();		gl2.glColor3f(r,g, b);		gl2.glRotated(90,1, 0, 0);		if(rotate>=0)			gl2.glRotated(rotate,0, 0, 1);		gl2.glTranslated(0.0, 0.0, zoffset);		gl2.glScaled(scale1, scale2, 0);		gl2.glBegin(GL2.GL_POLYGON);		gl2.glVertex3d(-200, -200, 0.0);		gl2.glVertex3d(-200, 200, 0.0);		gl2.glVertex3d(200, 200, 0.0);		gl2.glVertex3d(200, -200, 0.0);		gl2.glEnd();		gl2.glPopMatrix();	}	// draw a triangular	private void triangular(GL2 gl2) {		gl2.glBegin(GL2.GL_POLYGON);		gl2.glVertex3d(5*Math.PI, 150, 0.0);		gl2.glVertex3d(-5*Math.PI, 150, 0.0);		gl2.glVertex3d(0, 0, 0.0);		gl2.glEnd();	}	public void dispose(GLAutoDrawable glautodrawable) {	}	public void reshape(GLAutoDrawable dr, int x, int y, int width, int height) {	}}