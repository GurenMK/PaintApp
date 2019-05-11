//Aleander Urbanyak
//CS 1302
//Simple paint app in FX
//3 width options, 3 colors, and eraser 
//Button text is changed based on settings
//Eraser is on until clicked again
//Using counts for button clicks
//Canvas clears out when resize by the user
//ResizeCanvas redraws the canvas whenever the size is changed

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

public class SimplePaint extends Application {
  //count for the width button clicked
    int count1 = 0;
  //count for the color button clicked
    int count2 = 0;
  //count for the erase button clicked
    int count3 = 0;
  //Width for lines
    int width1 = 2;
    int width2 = 7;
    int width3 = 12;
  //Colors
    Color color1 = Color.BLUE;
    Color color2 = Color.RED;
    Color color3 = Color.GREEN;
              
    @Override
    public void start(Stage primaryStage){
        ResizeCanvas canvas = new ResizeCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        BorderPane root = new BorderPane();
      //minimum window size
        root.setMinSize(500, 300);
      //bind canvas size changing to the border pane since canvas will be placed into it
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
      //put all the buttons in HBox
        HBox hb = new HBox(getHBox1(root));//(int-sets padding, b1, b2,b3)
      //put canvas into BorderPane set in the center
        root.setCenter(canvas);
       //change color of the canvas by setting background of the borderpane 
        root.setStyle("-fx-background-color: white");
      //Assign the buttons to the top of the BorderPane
        root.setTop(hb);
      //Title of the window
        primaryStage.setTitle("Assignemt_5_FX");
      //create a scene
        primaryStage.setScene(new Scene(root));
      //show the window
        primaryStage.show();
      //Draw using mouse pointer  
      //add event to the canvas
      //moused pressed is needed otherwise everything drawn is connected as one line
            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    gc.beginPath();
                    gc.moveTo(e.getX(), e.getY());
                    gc.stroke();
                    gc.closePath();
                }
            });
            //use .stroke width, colors and eraser
            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                   //set line width based on count
                    if (count1 == 0)
                        gc.setLineWidth(width1);
                    if (count1 == 1)
                        gc.setLineWidth(width2);
                    if (count1 == 2)
                        gc.setLineWidth(width3);
                   //set color based on count
                    if (count2 == 0) 
                        gc.setStroke(color1);
                    if (count2 == 1)
                        gc.setStroke(color2);
                    if (count2 == 2)
                        gc.setStroke(color3);
                   //eraser on or off
                    if (count3 == 1) {
                        gc.setStroke(Color.WHITE);
                        gc.setLineWidth(50);
                    }
                    //draw the line
                    gc.lineTo(e.getX(), e.getY());
                    gc.stroke();
                }
            });
    }
    public HBox getHBox1(BorderPane root) {
        //create buttons
        Button button1 = new Button("Line-Thin");
        Button button2 = new Button("Color");
        Button button3 = new Button("Eraser-Off");
        //Color button color, changed accordingly later
        button2.setTextFill(Color.BLUE);
        //set buttons in horizontal box
        HBox box = new HBox(button1, button2, button3);
        //set font and size of button text
        box.setStyle("-fx-font: 30 arial;");
        //remove size restrictions for buttons and the horizontal box
        box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //allows the buttons and horizontal box to change size with the window
        HBox.setHgrow(box, Priority.ALWAYS);
        HBox.setHgrow(button1, Priority.ALWAYS);
        HBox.setHgrow(button2, Priority.ALWAYS);
        HBox.setHgrow(button3, Priority.ALWAYS);
        //center the buttons
        box.setAlignment(Pos.CENTER);
        //add button events that use counters
        button1.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
            count1++;
            //System.out.println("Width button clicked: " + count1);
            if (count1 == 3)
                count1 = 0;
            //set button text based on count, width is changed in mouse event
                    if (count1 == 0)
                        button1.setText("Line-Thin");
                    if (count1 == 1)
                        button1.setText("Line-Medium");
                    if (count1 == 2)
                        button1.setText("Line-Thick");
            }
        });
        button2.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
            count2++;
            //System.out.println("Color button clicked: " + count2);
            if (count2 == 3)
                count2 = 0;
            //set button color based on count
                    if (count2 == 0)
                        button2.setTextFill(color1);
                    if (count2 == 1)
                        button2.setTextFill(color2);
                    if (count2 == 2)
                        button2.setTextFill(color3);
            }
        });
        button3.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
            count3++;
            //System.out.println("Eraser button clicked: " + count3);
            if (count3 == 2)
                count3 = 0;
            //set button text based on count 
                if (count3 == 0)
                    button3.setText("Eraser-Off");
                if (count3 == 1)
                    button3.setText("Eraser-On");
            
            }
        });
        return box;
    }
    public static void main(String[] args) {
    //launches the arguments 
    //FX automatically terminates the program once the last stage is closed (exiting the window),
    //so there is no need for a close operation setting (and there is no equivalent)
        Application.launch(args);
    }
    
}

