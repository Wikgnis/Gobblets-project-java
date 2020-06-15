package gobblets.IHM.Fx.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class jeuListener implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) onDrag(event);
        else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) onRelease(event);
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) onEnter(event);
    }
    
    private void onDrag(MouseEvent event) {
        System.out.println("Drag detected");
    }

    private void onRelease(MouseEvent event) {
        System.out.println("release Detected");
    }

    private void onEnter(MouseEvent event) {
        System.out.println("Enter detected " + event.getTarget());
    }
}