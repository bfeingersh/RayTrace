public class Light {
	int index;
	float intensity;
	static final int POINT = 0;
	static final int DIRECTIONAL = 1;
	int mode; /* either 0 or 1 */
	
	public Light(int i, float intensity, int mode) {
		this.index = i;
		this.intensity = intensity;
		this.mode = mode;
	}
	
	public String toString() {
		return  "Light: mode " + mode +
				", source @ " + RT.VERTICES[index].toString() +
				", intensity " + intensity;
	}
}