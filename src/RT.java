import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * RT: Ray Tracer
 */

public class RT extends JFrame {
	static Color AMBIENT_LIGHT;
	static Color DIFFUSE_LIGHT;
	static Color SPECULAR_LIGHT;
	static Camera CAMERA = new Camera();
	static int SPECULAR_EXPT;
	static boolean DIFFUSE = true;
	static boolean SPECULAR = true;
	static boolean SHADOWS = true;
	static int MIRROR = 1;
	static double AMBIENT = 1.0;
	static ArrayList<Point3D> VERTICES_L = new ArrayList<Point3D>();
	static Point3D[] VERTICES = new Point3D[0];
	static ArrayList<Shape> SHAPES = new ArrayList<Shape>();
	static ArrayList<Light> LIGHTS = new ArrayList<Light>();
	final static int WINDOW_WIDTH = 512;
	final static int WINDOW_HEIGHT = 512;
	static BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
	static boolean INITIALIZED = false;
	
	public RT() {
		super("Homework 6: RAYYYYYY TRACERRRR");
		
		System.out.println("***** constructing canvas");
		panel.setBackground(Color.red);
		panel.setVisible(true);
		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.add(panel);
//		f.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.pack();
		this.setResizable(false);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		image.setRGB(256, 256, 25);
		image.setRGB(256, 257, 50);
		image.setRGB(257, 257, 75);
		image.setRGB(257, 256, 100);
		System.out.println("***** done constructing");
	}
	
	public void constructCanvas() {
		System.out.println("***** constructing canvas");
		//super("Homework 6: RAYYYYYY TRACERRRR");
		panel.setBackground(Color.red);
		panel.setVisible(true);
		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.add(panel);
//		f.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.pack();
		this.setResizable(false);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		System.out.println("***** done constructing");
		try { Thread.sleep(1000); } catch (Exception e) {}
	}
	
	/* once the file has been read, set the camera to the index it is referring to and set axes */
	public static void setCameraFrame() {
		CAMERA.src = VERTICES[CAMERA.v];
		Vector zc = CAMERA.src.d.scale(-1).normalized();
		CAMERA.zAxis = zc;
		Vector xc = MathUtils.cross(CAMERA.src.d, new Vector(0, 1, 0)).normalized();
		CAMERA.xAxis = xc;
		Vector yc = MathUtils.cross(zc, xc).normalized();
		CAMERA.yAxis = yc;
		
		System.out.println("***** just set camera: " + CAMERA.toString());
	}
	
	public static void setDefaults() {
		AMBIENT_LIGHT = new Color((float).2, (float).2, (float).2);
		DIFFUSE_LIGHT = new Color((float)1, (float)1, (float)1);
		SPECULAR_LIGHT = new Color((float)1, (float)1, (float)1);
		SPECULAR_EXPT = 64;
	}
	
	public static void parse(String path) {
		try {
			FileInputStream file = new FileInputStream(path);
			DataInputStream in = new DataInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			while((line = br.readLine()) != null) {
				if(!line.startsWith("#")) {
					String[] args = line.trim().split(" ");
					
					String c = args[0];
					if(c.equals("vv")) { 
						VERTICES_L.add(new Point3D(args[1], args[2], args[3], args[4], args[5], args[6]));
					}
					
					/*
					else if(c.equals("am")) { 
						setAmbient(args[1], args[2], args[3]);
					}
					else if(c.equals("dm")) { 
						setDiffuse(args[1], args[2], args[3]);
					} 
					else if(c.equals("sm")) { 
						setSpecular(args[1], args[2], args[3]);
					} 
					else if(c.equals("ts")) { System.out.println("X: triangle not implemented"); } 
					*/
					else if(c.equals("ss")) { 
						addSphere(args[1]);
					} 
					/*
					else if(c.equals("ps")) { 
						addPlane(args[1]);
					} 
					else if(c.equals("se")) { 
						settings(args[1], args[2], args[3], args[4], args[5]);
					} 
					else if(c.equals("pl")) { 
						addLight(args[1], args[2], args[3]);
					} 
					else if(c.equals("dl")) { 
						addLight(args[1], args[2], args[3]);
					} 
					*/
					else if(c.equals("cc")) { 
						setCamera(args[1]);
					} 
					/*
					else if(c.equals("tx")) { System.out.println("X: translation not implemented"); } 
					else if(c.equals("rx")) { System.out.println("X: rotation not implemented"); } 
					else if(c.equals("hx")) { System.out.println("X: homogenous xform not implemented"); } 
					else if(c.equals("ux")) { System.out.println("X: undo transform not implemented"); } 
					else if(c.equals("ix")) { System.out.println("X: clear transform not implemented"); } 
					else if(c.equals("so")) { System.out.println("X: start model not implemented"); } 
					else if(c.equals("eo")) { System.out.println("X: end model not implemented"); } 
					else if(c.equals("io")) { System.out.println("X: instance model not implemented"); } 
					else if(c.equals("rv")) { System.out.println("X: rebase indexing not implemented"); } 
					*/
					else { System.out.println("some other input: " + c); }
					
					
					
					System.out.println("\n\n\n"); /** DUMB LINE SPACER **/
				}
			}
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	private static void setCamera(String i) {
		int index = Integer.valueOf(i);
		
		CAMERA.v = index;
	}
	
	public static Vector toWorldFrame(Vector v) {
		return new Vector((CAMERA.xAxis.x * v.x) + (CAMERA.yAxis.x * v.y) + (CAMERA.zAxis.x * v.z),
						  (CAMERA.xAxis.y * v.x) + (CAMERA.yAxis.y * v.y) + (CAMERA.zAxis.y * v.z),
						  (CAMERA.xAxis.z * v.x) + (CAMERA.yAxis.z * v.y) + (CAMERA.zAxis.z * v.z));
	}
	
	private static void setAmbient(String r, String g, String b) {
		float rf = Float.valueOf(r);
		float gf = Float.valueOf(g);
		float bf = Float.valueOf(b);
		rf = MathUtils.clamp(0, 1, rf);
		gf = MathUtils.clamp(0, 1, gf);
		bf = MathUtils.clamp(0, 1, bf);
		
		AMBIENT_LIGHT = new Color(rf, gf, bf);
	}
	
	private static void setDiffuse(String r, String g, String b) {
		float rf = Float.valueOf(r);
		float gf = Float.valueOf(g);
		float bf = Float.valueOf(b);
		rf = MathUtils.clamp(0, 1, rf);
		gf = MathUtils.clamp(0, 1, gf);
		bf = MathUtils.clamp(0, 1, bf);
		
		DIFFUSE_LIGHT = new Color(rf, gf, bf);
	}
	
	private static void setSpecular(String r, String g, String b) {
		float rf = Float.valueOf(r);
		float gf = Float.valueOf(g);
		float bf = Float.valueOf(b);
		rf = MathUtils.clamp(0, 1, rf);
		gf = MathUtils.clamp(0, 1, gf);
		bf = MathUtils.clamp(0, 1, bf);
		
		SPECULAR_LIGHT = new Color(rf, gf, bf);
	}
	
	private static void addLight(String i, String intensity, String m) {
		int index = Integer.valueOf(i);
		float intensityF = Float.valueOf(intensity);
		intensityF = MathUtils.clamp(0, 1, intensityF);
		int mode = Integer.valueOf(m);
		if(mode != 0 || mode != 1) { System.out.println("***** invalid mode in addLight: " + m); return; }
		LIGHTS.add(new Light(index, intensityF, mode));
	}
	
	private static void addSphere(String i) {
		int index = Integer.valueOf(i);
		SHAPES.add(new Sphere(index, AMBIENT_LIGHT, DIFFUSE_LIGHT, SPECULAR_LIGHT, SPECULAR_EXPT));
	}
	
	private static void addPlane(String i) {
		int index = Integer.valueOf(i);
		System.out.println("***** adding plane: i = " + i + ", VERTICES.length = " + VERTICES.length);
		SHAPES.add(new Plane(index, AMBIENT_LIGHT, DIFFUSE_LIGHT, SPECULAR_LIGHT, SPECULAR_EXPT));
	}
	
	private static void settings(String d, String s, String a, String m, String i) {
		DIFFUSE = Boolean.valueOf(d);
		SPECULAR = Boolean.valueOf(s);
		SHADOWS = Boolean.valueOf(a);
		MIRROR = (Integer.valueOf(m) < 0 ? 0 : Integer.valueOf(m));
		AMBIENT = (Double.valueOf(i) > 1 ? 1 : (Double.valueOf(i) < 0 ? 0 : Double.valueOf(i)));
		System.out.println("***** settings done");
	}

	public static void traceRays() {
		Point3D cameraLoc = CAMERA.src;
		for(int i = 0; i <= WINDOW_WIDTH; i++) {
			for(int j = 0; j <= WINDOW_HEIGHT; j++) {
				//System.out.println("***** at point: (" + i + "," + j + ")");
				float xp = (float) (i/WINDOW_WIDTH - .5);
				float yp = (float) ((-WINDOW_HEIGHT/WINDOW_WIDTH)*(j/WINDOW_HEIGHT - .5));
				float zp = (float) (-Math.sqrt(3)/2);
				Vector p = new Vector(xp, yp, zp);
				Vector pPrime = RT.toWorldFrame(p);
				
				Vector ray = MathUtils.vecSub(CAMERA.src.d, pPrime.normalized());
				
				Vector cameraDirectionToIJ = MathUtils.vecSub(new Vector(cameraLoc.x, cameraLoc.y, cameraLoc.z), pPrime);
				
				for(Shape s : SHAPES) {
					// if s intersects the vector from E(p) to S(e)
					if(s.isSphere()) {
						double t = MathUtils.raySphereIntersect(new Point3D(cameraLoc.x,
																			cameraLoc.y,
																			cameraLoc.z,
																			cameraDirectionToIJ), (Sphere)s);
						
						
//						int c = 50;
//						if(t < Double.POSITIVE_INFINITY) image.setRGB(i, j, c);
					}
				}
			}
		}
	}
	
	private JPanel panel = new JPanel() {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println("***** trying to paint component");
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.yellow);
			g2d.drawRect(200, 200, 50, 100);
			g2d.drawRenderedImage(image, new AffineTransform());
		};
	};
	
	
	
	
	/**
	 ************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 */
	public static void main(String[] args) {
		run(args[0]);
	}
	
	public static void run(String path) {
		RT rt = new RT();
		parse(path);

		setDefaults();
	
		VERTICES = VERTICES_L.toArray(VERTICES);
		
		System.out.println("done reading");
		System.out.println("vertices are:");
		for(Point3D p : VERTICES) {
			System.out.println(p.toString());
		}
		System.out.println("vertices length = " + VERTICES.length);
		System.out.println("shapes are:");
		for(Shape s : SHAPES) {
			System.out.println("***** if you see this, shapes != null");
			System.out.println(s.toString());
		}
		
		setCameraFrame();
		traceRays();
	}
	/**
	  ************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 *************************************************************************************************************
	 */
}