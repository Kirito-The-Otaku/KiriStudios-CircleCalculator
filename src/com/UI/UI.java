package com.UI;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class UI extends  javax.swing.JFrame{
    public static boolean changed = false;
    public static float U,A=0;
    public static float d,r=0;
    public static String einheit = "cm";
    public static String squared = " ";
    public static String appTitle = "Circle Calculator";
    public static double rounded;
    public static double rounded2;
    public static void calcCircle(float r,float d, JTextField Result){
        if(!einheit.equals("")){
            squared = "² ";
        }else{
            squared = " ";
        }
        A = r * r * 3.14F;
        U = d * 3.14F;
        rounded = Math.round(A * 100.0) / 100.0;
        rounded2 = Math.round(U * 100.0) / 100.0;
        Result.setText("A=" + rounded+einheit+squared+ "\n" + "U=" + rounded2+einheit);
    }
    public static void calcDandR(String type,JTextField radius,JTextField Result,JTextField durchmesser){
        try {
            if(!changed){
                changed = true;
                String size;
                switch (type){
                    case "radius":
                        size = radius.getText();
                        einheit = size.replaceAll("[0-9]","");
                        einheit = einheit.replaceAll("[.,:;]", "");
                        size = size.replaceAll("[.,:;]", ".");
                        size = size.replaceAll("[^\\d.]", "");
                        r = Float.parseFloat(size);
                        d = r*2;
                        durchmesser.setText("" +  Math.round(d * 100.0) / 100.0 +einheit);
                        break;
                    case "diameter":
                        size = durchmesser.getText();
                        einheit = size.replaceAll("[0-9]","");
                        einheit = einheit.replaceAll("[.,:;]", "");
                        size = size.replaceAll("[.,:;]", ".");
                        size = size.replaceAll("[^\\d.]", "");
                        d = Float.parseFloat(size);
                        r = d/2;
                        radius.setText("" + Math.round(r * 100.0) / 100.0+einheit);
                        break;
                }

                if(einheit.equals("")){
                    squared = " ";
                }else{
                    squared = "² ";
                }
                calcCircle(r,d,Result);
                changed=false;
            }
        } catch (NumberFormatException e2) {
            changed=false;

        }

    }
    public static void main(String[] args) {

        JFrame frame= new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.setTitle(appTitle);
        URL resource = UI.class.getResource("/com/Images/ico.png");
        URL bgurl = UI.class.getResource("/com/Images/bg.gif");

        ImageIcon i = new ImageIcon(resource);
        frame.setIconImage(i.getImage());

        JLabel bg = new JLabel(new ImageIcon(bgurl));
        bg.setBounds(0,0,352,370);
        frame.setSize(352, 370);
        frame.setResizable(false);
        frame.setLocation(50, 50);
        JLabel label = new JLabel("Radius:");
        label.setBounds(102,50,100,30);
        label.setForeground(Color.ORANGE);
        JLabel label2 = new JLabel("Diameter:");
        label2.setBounds(102,100,100,30);
        label2.setForeground(Color.ORANGE);

        JTextField radius = new JTextField("0",15);
        radius.setBounds(102,75,150,30);
        JTextField durchmesser = new JTextField("0",15);
        durchmesser.setBounds(102,125,150,30);
        JButton bnt = new JButton("Copy Result");
        bnt.setBackground(Color.ORANGE);
        bnt.setBounds(102,215,150,30);
        JButton bnt2 = new JButton("Generate Picture");
        bnt2.setBackground(Color.ORANGE);
        bnt2.setBounds(102,255,150,30);
        JLabel label3 = new JLabel("Result:");
        label3.setBounds(102,150,100,30);
        label3.setForeground(Color.GREEN);
        JButton bnt3 = new JButton("Website");
        bnt3.setBackground(Color.white);
        bnt3.setBounds(235,310,100,20);
        JTextField Result = new JTextField("Result",15);
        Result.setEditable(false);
        Result.setBounds(102,175,150,30);
        bnt2.addActionListener(e1 -> {

            Toolkit.getDefaultToolkit().beep();
            if(Result.getText().equals("Result")){
                JOptionPane.showMessageDialog(frame, "there is neither radius or diameter", appTitle, JOptionPane.INFORMATION_MESSAGE);
            }else {
                CompletableFuture.runAsync(()-> Graphics.createCircleImage(d, r, A, U, frame));
            }
        });
        bnt3.addActionListener(e1 -> CompletableFuture.runAsync(()->{
        try {
            Desktop.getDesktop().browse(new URL("https://kiritools.000webhostapp.com/").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        }));
        bnt.addActionListener(e1 -> {

            Toolkit.getDefaultToolkit().beep();
            if(Result.getText().equals("Result")){
                JOptionPane.showMessageDialog(frame, "there is neither radius or diameter", appTitle, JOptionPane.INFORMATION_MESSAGE);
            }else {
                StringSelection selection = new StringSelection(Result.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
                JOptionPane.showMessageDialog(frame, "Copied Result", appTitle, JOptionPane.INFORMATION_MESSAGE);
            }
        });
        radius.getDocument().addDocumentListener(new DocumentListener() {


            @Override
            public void insertUpdate(DocumentEvent e) {
                calcDandR("radius",radius,Result,durchmesser);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calcDandR("radius",radius,Result,durchmesser);

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        durchmesser.getDocument().addDocumentListener(new DocumentListener() {


            @Override
            public void insertUpdate(DocumentEvent e) {
                calcDandR("diameter",radius,Result,durchmesser);
            }



            @Override
            public void removeUpdate(DocumentEvent e) {
                calcDandR("diameter",radius,Result,durchmesser);

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        radius.setForeground(Color.BLUE);
        radius.setBackground(Color.ORANGE);
        durchmesser.setForeground(Color.BLUE);
        durchmesser.setBackground(Color.ORANGE);
        Result.setBackground(Color.gray);
        Result.setForeground(Color.GREEN);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        //panel.add(credits);
        panel.add(Result);
        panel.add(radius);
        panel.add(durchmesser);
        panel.add(bnt);
        panel.add(bnt2);
        panel.add(bnt3);
        panel.add(bg);
        frame.add(panel);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "you don't need radius and diameter only one of them is important", appTitle, JOptionPane.INFORMATION_MESSAGE);
    }
}
