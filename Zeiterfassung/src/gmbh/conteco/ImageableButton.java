package gmbh.conteco;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

// TODO: This interface can be optimized further - how?
public interface ImageableButton {
	// TODO: What is missing here - what's a good coding style?
	interface Addable<E> {
		boolean add(E e);
	}
	
	public default <E extends ButtonBase> void createImageView(Addable<Node> addable, 
			E button, final Image selected, final Image unselected) {
        final ImageView imageView = new ImageView(selected);
        
        // TODO: Describe what this line does
        addable.add(imageView);
        
        // TODO: Use a lambda expression instead
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                imageView.setImage(unselected);
            }
        });
        
        // TODO: Use a lambda expression instead
        imageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                imageView.setImage(selected);
            }
        });
        
        button.setGraphic(imageView);
    }
	
	public void setImage(final Image selected, final Image unselected);
	
	public default void setImage(final Image image) {
		setImage(image, image);
	}
}