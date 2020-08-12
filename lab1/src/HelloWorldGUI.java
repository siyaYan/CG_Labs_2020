import java.awt.*;import javax.swing.JComponent;import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.JSlider;import javax.swing.SwingUtilities;import javax.swing.event.ChangeEvent;import javax.swing.event.ChangeListener;public class HelloWorldGUI extends JComponent implements Runnable, ChangeListener {	/**	 * Slider2GUI - a simple swing GUI with a slider. Eric McCreath 2019	 */	static final int gap = 0;	JFrame jframe;	JSlider jslider;	public HelloWorldGUI() {		SwingUtilities.invokeLater(this);	}  //ready for use,reduce the waiting time	//entry	public static void main(String[] args) {		new HelloWorldGUI();	}	public Dimension getPreferredSize() {		return new Dimension(800, 400);	}	//a common process to set up all components	public void run() {		jframe = new JFrame("HelloWorldGUI"); // setup windows' name		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setup close method		// set up the slider		jslider = new JSlider(); //new slider object		jslider.setMaximum(100); //set max num of slider		jslider.setMinimum(1);  //set min num of slider		jslider.addChangeListener(this); //listen the slider's action		jslider.setValue(50);		// make the main panel		JPanel mainpanel = new JPanel(); //new panel object		mainpanel.setLayout(new BorderLayout()); // a default Jframe layout - divide into 5 parts		mainpanel.add(this, BorderLayout.CENTER); // add a center panel		mainpanel.add(jslider, BorderLayout.PAGE_END); // add the slider into the panel		// add panel to jframe and make viewable		jframe.getContentPane().add(mainpanel); // add the panel into the frame		jframe.setVisible(true); // set visible		jframe.pack(); // adjust the window size suitable for all your components	}	@Override	public void stateChanged(ChangeEvent e) {		repaint();	}	@Override	/*	 * Xiran Yan (Siya)	 * 2020/8/10	 */	protected void paintComponent(Graphics graph) {		super.paintComponent(graph);		Graphics2D g = (Graphics2D) graph;		Dimension dim = this.getSize(); //800*400		// fill the background		g.setColor(Color.white);		g.fillRect(0, 0, dim.width, dim.height);		//draw text		g.setColor(Color.black);		String str="Hello World";		int high=jslider.getValue();		g.setFont(new Font(Font.MONOSPACED, Font.BOLD,high)); //set size by slider		int width =g.getFontMetrics().stringWidth(str);//string width		g.drawString(str,(dim.width/2)-(width/2),(dim.height/2)+(high/2));		// draw rect		g.drawRect((dim.width/2)-(width/2),(dim.height/2)-(high/2),width,high);		//not fix case		if(dim.width<width || dim.height<high){			g.setColor(Color.red);			g.fillRect(0, 0, dim.width, dim.height);		}	}}