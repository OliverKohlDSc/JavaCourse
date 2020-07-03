package gmbh.conteco;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class ImageButton extends Button implements ImageableButton{
	
	public ImageButton() { super(); }
	
	public ImageButton(String text) { super(text); }
	
	// TODO: What's the reason for adding these three lines?
	// TODO: Is there a difference to the ImageMenuButton class?
	public void setImage(final Image selected, final Image unselected) {
		createImageView(this.getChildren()::add, this, selected, unselected);
	}
}