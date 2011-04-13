public class Point3D {
	double x;
	double y;
	double z;
	Vector d;

	public Point3D(String x, String y, String z, String dx, String dy, String dz) {
		this.x = Double.valueOf(x);
		this.y = Double.valueOf(y);
		this.z = Double.valueOf(z);
		this.d = new Vector(dx, dy, dz);
	}
	
	public Point3D(double x, double y, double z, double dx, double dy, double dz) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.d = new Vector(dx, dy, dz);
	}
	
	public Point3D(double x, double y, double z, Vector d) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.d = d;
	}
	
	public String toString() {
		return "Point3D: x " + x + ", y " + y + ", z " + z + ", d " + d + " ";
	}
	
	public Point3D parametric(double t) {
		Vector td = d.scale(t);
		return new Point3D(x+td.x, y+td.y, z+td.z, d);
	}
}
