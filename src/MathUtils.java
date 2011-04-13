
/**
 * Geom is a class with helper methods for use in other mathematical functions
 * @author bfeingersh
 *
 */
public class MathUtils {
	/**
	 * min: helper function to find smallest of 3 double values
	 */
	public static double min(double a, double b, double c) {
		return Math.min(Math.min(a, b), c);
	}
	
	/**
	 * max: helper function to find largest of 3 double values
	 */
	public static double max(double a, double b, double c) {
		return Math.max(Math.max(a, b), c);
	}
	
	/**
	 * cross: computes the cross product vector of two vectors
	 */
	public static Vector cross(Vector v0, Vector v1) {
		return new Vector(v0.y * v1.z - v0.z * v1.y,
						  v0.z * v1.x - v0.x * v1.z,
						  v0.x * v1.y - v0.y * v1.x);
	}
	
	/**
	 * magnitude: computes the magnitude of a vector v
	 */
	public static double magnitude(double[] v) {
		return Math.sqrt(v[0]*v[0] + v[1]*v[1] + v[2]*v[2]);
	}
	
	/**
	 * clamp: restricts a value to between two bounds
	 */
	public static float clamp(float l, float h, float val) {
		if(val > l) {
			if(val < h)
				return val;
			else return h;
		} else return l;
	}
	
	public static Vector vecSub(Point3D s, Point3D e) {
		return new Vector(e.x - s.x,
						  e.y - s.y,
						  e.z - s.z);
	}
	
	public static Vector vecSub(Vector s, Vector e) {
		return new Vector(e.x - s.x,
						  e.y - s.y,
						  e.z - s.z);
	}
	
	public static Vector vecAdd(Vector s, Vector e) {
		return new Vector(e.x + s.x,
						  e.y + s.y,
						  e.z + s.z);
	}
	
	public static double dot(Vector a, Vector b) {
		return (a.x * b.x) + (a.y * b.y) + (a.z * b.z);
	}
	
	public static double raySphereIntersect(Point3D p, Sphere s) {
		
		Vector q = new Vector(p.x, p.y, p.z);
		Vector d = p.d;
		Vector c = s.center();
		
		double qa = dot(d, d);
		double qb = dot(d.scale(2), vecSub(c, q));
		Vector qtoc = vecSub(c, q); double r = s.radius();
		double qc = dot(qtoc, qtoc) - (r * r);
		
		double discriminant = (qb * qb) - (4 * qa * qc);
		
		if(discriminant == 0) {
			System.out.println("***** discriminant = 0, no intersection");
		}
		
		return Double.POSITIVE_INFINITY;
	}
}
