import java.awt.Color;

public abstract class Shape {
	int index;
	Color ambient;
	Color diffuse;
	Color specular;
	int specExponent;
	
	public abstract String toString();
	
	public abstract boolean isSphere();
	public abstract boolean isPlane();
}