
package ru.tweekyone.java.j20.practice.frame;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;

public class ColorPicker extends JFrame {
    
    private JPanel viewer = new JPanel();
    
    private JSlider sliderRed, sliderGreen, sliderBlue;
    
    private JPanel mainPanel = new JPanel();
    
    public ColorPicker (){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setTitle("ColorPicker");
        setSize(400, 200);
                
        sliderRed = createSlider("Red:");
        sliderGreen = createSlider("Green:");
        sliderBlue = createSlider("Blue:");
        
        viewer.setToolTipText("#7D7D7D");

        LayoutManager layout = new BoxLayout(mainPanel, BoxLayout.LINE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.add(viewer);
        mainPanel.add(createColorsNamePanel());
        mainPanel.add(createControlsPanel());
        
        add(mainPanel);
    }
        
    private JPanel createColorsNamePanel(){
        JPanel names = new JPanel();
        GridLayout layout = new GridLayout (3, 1);
        names.setLayout(layout);
                
        names.add(setColorName(sliderRed.getName()));
        names.add(setColorName(sliderGreen.getName()));
        names.add(setColorName(sliderBlue.getName()));
        
        return names;        
    }
    
    private JLabel setColorName(String text){
        JLabel name = new JLabel();
        
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setText(text);
        
        return name;
    }
    
    private JPanel createControlsPanel(){
        JPanel colors = new JPanel();
        GridLayout layout = new GridLayout(3, 2);
        colors.setLayout(layout);
        
        colors.add(sliderRed);
        colors.add(sliderGreen);
        colors.add(sliderBlue);
        
        return colors;
    }
    
    private JSlider createSlider(String name){
        JSlider slider = new JSlider(0, 255, 125);
        slider.setMajorTickSpacing(18);
        slider.setPaintTicks(true);
                
        Dictionary lableTable = new Hashtable();
        lableTable.put(new Integer (0), new JLabel ("0"));
        lableTable.put(new Integer (255), new JLabel ("255"));
        slider.setLabelTable(lableTable);
        slider.setPaintLabels(true);
        slider.setName(name);
        
        slider.addChangeListener(this::sliderChanged);
        
        return slider;
    }
    
    private String getHexColorValue(int red, int green, int blue){
        String hexColorValue = "#" + Integer.toHexString(red) 
                                    + Integer.toHexString(green) +
                                        Integer.toHexString(blue);
        
        return hexColorValue.toUpperCase(); 
    }
    
    private void sliderChanged(ChangeEvent ev){
        Color color = new Color(sliderRed.getValue(), sliderGreen.getValue(), 
                sliderBlue.getValue());
                      
        viewer.setBackground(color);
        viewer.setToolTipText(getHexColorValue(color.getRed(),
                                    color.getGreen(), color.getBlue()));
    }
    
}
