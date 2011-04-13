public class Camera {
	public int v;
	public Vector xAxis, yAxis, zAxis;
	public Point3D src;
	
	public Camera() {
		xAxis = new Vector(1, 0, 0);
		yAxis = new Vector(0, 1, 0);
		zAxis = new Vector(0, 0, 1);	
	}
	
	public Camera(int v) {
		this.v = v;
		xAxis = new Vector(1, 0, 0);
		yAxis = new Vector(0, 1, 0);
		zAxis = new Vector(0, 0, 1);	
	}
	
	public String toString() {
		return "Camera: v " + v + ", X Axis " + xAxis + ", Y Axis " + yAxis + ", Z Axis " + zAxis + ", src " + src + " ";
	}
}