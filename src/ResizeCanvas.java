//Aleander Urbanyak
//CS 1302
//Simple paint app in FX
//3 width options, 3 colors, and eraser 
//Button text is changed based on settings
//Eraser is on until clicked again
//Using counts for button clicks
//Canvas clears out when resize by the user
//ResizeCanvas redraws the canvas whenever the size is changed

package assignment_5;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ResizeCanvas extends Canvas{
    public ResizeCanvas() {
      widthProperty().addListener(e -> reDraw());
      heightProperty().addListener(e -> reDraw());
    }
    private void reDraw() {
      double width = getWidth();
      double height = getHeight();

      GraphicsContext gc = getGraphicsContext2D();
      gc.setFill(Color.WHITE);
      gc.fillRect(0, 0, width, height);
    }
}
