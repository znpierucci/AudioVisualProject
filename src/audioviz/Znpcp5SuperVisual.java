/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import static java.lang.Integer.min;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author zpierucci
 */
public class Znpcp5SuperVisual implements Visualizer {
    
    private final String name = "Znpcp5 Super Visual";
    
    private Integer numBands;
    private AnchorPane vizPane;
    
    private String vizPaneInitialStyle = "";
    
    private final Double bandHeightPercentage = 1.3;
    private final Double minEllipseRadius = 10.0;  // 10.0
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    
    private final Double startHue = 260.0;
    
    private Ellipse[] ellipses;

    @Override
    public void start(Integer numBands, AnchorPane vizPane) 
    {
        end();
        
        vizPaneInitialStyle = vizPane.getStyle();
        
        this.numBands = numBands;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        //System.out.println(height);
        width = vizPane.getWidth();
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        ellipses = new Ellipse[numBands];
        
        vizPane.setStyle("-fx-background-color: hsb(" + 100 + ", 100%, 100%)" );
        
        for (int i = 0; i < numBands; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(bandWidth / 2 + bandWidth * i);
            ellipse.setCenterY(height - 50);
            ellipse.setRadiusX(bandWidth / 2);
            ellipse.setRadiusY(minEllipseRadius);
            ellipse.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            vizPane.getChildren().add(ellipse);
            ellipses[i] = ellipse;
            i+=3;
        }
        
        for (int i = 1; i < numBands; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(bandWidth / 2 + bandWidth * i);
            ellipse.setCenterY(height - 150);
            ellipse.setRadiusX(bandWidth / 2);
            ellipse.setRadiusY(minEllipseRadius);
            ellipse.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            vizPane.getChildren().add(ellipse);
            ellipses[i] = ellipse;
            i+=3;
        }
        
        for (int i = 2; i < numBands; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(bandWidth / 2 + bandWidth * i);
            ellipse.setCenterY(height - 250);
            ellipse.setRadiusX(bandWidth / 2);
            ellipse.setRadiusY(minEllipseRadius);
            ellipse.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            vizPane.getChildren().add(ellipse);
            ellipses[i] = ellipse;
            i+=3;
        }
        
        for (int i = 3; i < numBands; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(bandWidth / 2 + bandWidth * i);
            ellipse.setCenterY(height - 350);
            ellipse.setRadiusX(bandWidth / 2);
            ellipse.setRadiusY(minEllipseRadius);
            ellipse.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            vizPane.getChildren().add(ellipse);
            ellipses[i] = ellipse;
            i+=3;
        }
    }

    @Override
    public void end() 
    {
        if (ellipses != null) {
             for (Ellipse ellipse : ellipses) {
                 vizPane.getChildren().remove(ellipse);
             }
            ellipses = null;
            vizPane.setStyle(vizPaneInitialStyle);
        }
    }

    @Override
    public String getName() 
    {
        return name;
    }

    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) 
    {
        
        if (ellipses == null) {
            return;
        }
        
        Integer num = min(ellipses.length, magnitudes.length);
        
        for (int i = 0; i < num; i++) {
        
            if(magnitudes.length >= 4 && phases.length >= 4)
            {   
                    //ellipses[i].setRadiusX(60 + magnitudes[i] + minEllipseRadius);
                    //ellipses[i].setRadiusY(60 + magnitudes[i] + minEllipseRadius);
                
                    ellipses[i].setTranslateY(-.25*((Math.abs(magnitudes[0]) * 5) - 100));
                    
                    ellipses[i].setFill(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
                    //ellipses[i].setFill(Color.hsb(0, 0, 0));
            }
            i+=3;
        }
        
        for (int i = 1; i < num; i++) {
        
            if(magnitudes.length >= 4 && phases.length >= 4)
                {   
                    //ellipses[i].setRadiusX(65 + magnitudes[i] + minEllipseRadius);
                    //ellipses[i].setRadiusY(65 + magnitudes[i] + minEllipseRadius);
                    
                    ellipses[i].setTranslateY(.25*((Math.abs(magnitudes[0]) * 5) - 100));
                
                    ellipses[i].setFill(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
                }
            i+=3;
        }
        
        for (int i = 2; i < num; i++) {
        
            if(magnitudes.length >= 4 && phases.length >= 4)
            {   
                    ellipses[i].setRadiusX(70 + magnitudes[i] + minEllipseRadius);
                    ellipses[i].setRadiusY(70 + magnitudes[i] + minEllipseRadius);
                
                    //ellipses[i].setTranslateY((Math.abs(magnitudes[0]) * 5) - 100);
                    
                    ellipses[i].setFill(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
            }
            i+=3;
        }
        
        for (int i = 3; i < num; i++) {
        
            if(magnitudes.length >= 4 && phases.length >= 4)
            {   
                    ellipses[i].setRadiusX(75 + magnitudes[i] + minEllipseRadius);
                    ellipses[i].setRadiusY(75 + magnitudes[i] + minEllipseRadius);
                
                    //ellipses[i].setTranslateY((Math.abs(magnitudes[0]) * 5) - 100);
                    
                    ellipses[i].setFill(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
            }
            i+=3;
        }
        
            Double hue = ((60.0 + magnitudes[0])/60.0) * 360;
            vizPane.setStyle("-fx-background-color: hsb(" + hue + ", 100%, 100%)" );
    } 
}