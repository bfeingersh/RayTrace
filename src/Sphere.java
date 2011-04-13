import java.awt.Color;

public class Sphere extends Shape {
	public Sphere(int index, Color ambient, Color diffuse, Color specular, int specExponent) {
		this.index = index;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.specExponent = specExponent;
	}
	
	public double radius() {
		return RT.VERTICES[index].d.magnitude();
	}
	
	public Vector center() {
		Point3D p = RT.VERTICES[index];
		return new Vector(p.x, p.y, p.z);
	}
	
	public String toString() {
		System.out.println("***** in some shape");
		return  "Sphere: i " + RT.VERTICES[index].toString() + 
				", ambient " + ambient.toString() + 
				", diffuse " + diffuse.toString() + 
				", specular " + specular.toString() + 
				", specExponent " + specExponent;
	}

	public boolean isSphere() {
		return true;
	}

	public boolean isPlane() {
		return false;
	}
}