import java.io.Serializable;

public class MeinObjekt implements Serializable {
	private String meinText;
	private int x;
	
	public MeinObjekt(String meinText, int x) {
		this.meinText = meinText;
		this.x = x;
	}
	
	public String getMeinText() {
		return this.meinText;
	}
	
	public void setMeinText(String meinText) {
		this.meinText = meinText;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
}
