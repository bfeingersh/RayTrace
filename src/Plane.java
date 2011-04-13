import java.awt.Color;

public class Plane extends Shape {
	public Plane(int index, Color ambient, Color diffuse, Color specular, int specExponent) {
		this.index = index;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.specExponent = specExponent;
	}
	
	public String toString() {
		return  "Plane: i " + RT.VERTICES[index].toString() + 
				", ambient " + ambient.toString() + 
				", diffuse " + diffuse.toString() + 
				", specular " + specular.toString() + 
				", specExponent " + specExponent;
	}

	public boolean isSphere() {
		return false;
	}

	public boolean isPlane() {
		return true;
	}
}