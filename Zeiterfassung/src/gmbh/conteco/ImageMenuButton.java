package gmbh.conteco;

import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;

public class ImageMenuButton extends MenuButton implements ImageableButton{
	
	public ImageMenuButton() { super(); }
	
	public ImageMenuButton(String text) { super(text); }
	
	// TODO: What's the reason for adding these three lines?
	// TODO: Is there a difference to the ImageButton class?
	public void setImage(final Image selected, final Image unselected) {
		createImageView(this.getChildren()::add, this, selected, unselected);
	}
}