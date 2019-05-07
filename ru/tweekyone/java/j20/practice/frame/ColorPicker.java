
package ru.tweekyone.java.j20.practice.frame;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class ColorPicker extends JFrame {
    
    private JPanel viewer = new JPanel();
    
    private JSlider sliderRed, sliderGreen, sliderBlue;
    
    private JPanel mainPanel = new JPanel();
   
    //объект для взаимодействия с буфером обмена
    private Clipboard clipboard = Toolkit
            .getDefaultToolkit()
            .getSystemClipboard();
    
    public ColorPicker (){
        //настройка параметров окна
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setTitle("ColorPicker");
        setSize(400, 200);
        
        //вызов методов создания слайдеров
        sliderRed = createSlider("Red:");
        sliderGreen = createSlider("Green:");
        sliderBlue = createSlider("Blue:");
                
        //настройка подсказки на старте программы и настройка размера элемента
        viewer.setToolTipText("#7D7D7D");
        viewer.setPreferredSize(new Dimension(100, 50));
        viewer.setName("Палитра");
               
        //соединение элементов программы в одной панели
        LayoutManager layout = new BoxLayout(mainPanel, BoxLayout.LINE_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.add(viewer, BorderLayout.CENTER);
        mainPanel.add(createColorsNamePanel());
        mainPanel.add(createControlsPanel());
        
        add(mainPanel);
    }
    
    //метод создания панели с лейблами названий цветов
    private JPanel createColorsNamePanel(){
        JPanel names = new JPanel();
        GridLayout layout = new GridLayout (3, 1);
        names.setLayout(layout);
                
        names.add(setColorName(sliderRed.getName()));
        names.add(setColorName(sliderGreen.getName()));
        names.add(setColorName(sliderBlue.getName()));
        
        return names;        
    }
    
    //метод создания и настройки лейбла с названием цвета
    private JLabel setColorName(String text){
        JLabel name = new JLabel();
        
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setText(text);
        
        return name;
    }
    
    //метод создания панели слайдеров
    private JPanel createControlsPanel(){
        JPanel colors = new JPanel();
        GridLayout layout = new GridLayout(3, 2);
        colors.setLayout(layout);
        
        colors.add(sliderRed);
        colors.add(sliderGreen);
        colors.add(sliderBlue);
        
        return colors;
    }
    
    //метод создания и настройки слайдера
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
    
    //метод создания текста всплывающей подсказки
    private String getHexColorValue(int red, int green, int blue){
        String hexColorValue = "#" + Integer.toHexString(red) 
                                    + Integer.toHexString(green) +
                                        Integer.toHexString(blue);
        
        return hexColorValue.toUpperCase(); 
    }
    
    private void copyToClipboard(String text){
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }
    
    private boolean isBlank(String text){
        return text == null || text.trim().isEmpty();
    }
    
    //листенер перемещения слайдера
    private void sliderChanged(ChangeEvent ev){
        Color color = new Color(sliderRed.getValue(), sliderGreen.getValue(), 
                sliderBlue.getValue());
        
        //получение шестнадцетеричного значения цвета
        String colorHexValue = getHexColorValue(color.getRed(),
                                    color.getGreen(), color.getBlue());
        
        //реакция панели с палитрой на изменение позиции слайдера
        viewer.setBackground(color);
        viewer.setToolTipText(colorHexValue);
        
        //копирование полученного значения цвета в буфер обмена
        if(!isBlank(colorHexValue)) copyToClipboard(colorHexValue);
    }
    
    
    
}
