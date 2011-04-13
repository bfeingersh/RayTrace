public class Vector {
	double x;
	double y;
	double z;
	
	public Vector(double x, double y, double z) {
		this.x = x; this.y = y; this.z = z;
	}
	
	public Vector(String x, String y, String z) {
		this.x = Double.valueOf(x);
		this.y = Double.valueOf(y);
		this.z = Double.valueOf(z);
	}
	
	public Vector scale(double amt) {
		return new Vector(x*amt, y*amt, z*amt);
	}
	
	public double magnitude() {
		return Math.sqrt((x * x) + (y * y) + (z * z)); 
	}
	
	public Vector normalized() {
		double mag = magnitude();
		return new Vector(x/mag, y/mag, z/mag);
	}
	
	public String toString() {
		return "Vector: x " + x + ", y " + y + ", z " + z + " ";
	}
}