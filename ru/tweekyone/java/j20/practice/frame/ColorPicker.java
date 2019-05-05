
package ru.tweekyone.java.j20.practice.frame;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;

public class ColorPicker extends JFrame {
    
    private JLabel viewer = new JLabel();
    
    private JSlider sliderRed, sliderGreen, sliderBlue;
    
    private JPanel mainPanel = new JPanel();
    
    public ColorPicker (){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setTitle("ColorPicker");
        setSize(400, 200);
        
        
        sliderRed = createSlider();
        sliderGreen = createSlider();
        sliderBlue = createSlider();

        LayoutManager layout = new BoxLayout(mainPanel, BoxLayout.LINE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.add(viewer);
        mainPanel.add(createControlsPanel());
        
        
        add(mainPanel);
    }
    
    private JPanel createControlsPanel(){
        JPanel colors = new JPanel();
        GridLayout layout = new GridLayout(3, 1);
        colors.setLayout(layout);
        
        colors.add(sliderRed);
        colors.add(sliderGreen);
        colors.add(sliderBlue);
        
        return colors;
    }
    
    private JSlider createSlider(){
        JSlider slider = new JSlider(0, 255, 125);
        slider.setMajorTickSpacing(18);
        slider.setPaintTicks(true);
                
        Dictionary lableTable = new Hashtable();
        lableTable.put(new Integer (0), new JLabel ("0"));
        lableTable.put(new Integer (255), new JLabel ("255"));
        slider.setLabelTable(lableTable);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(this::sliderChanged);
        
        return slider;
    }
    
    //private JLabel colors(){
    //    return null;
    //}
    
    private void sliderChanged(ChangeEvent ev){
        Color color = new Color(sliderRed.getValue(), sliderGreen.getValue(), 
                sliderBlue.getValue());
        
        viewer.setBackground(color);
    }
    
}
