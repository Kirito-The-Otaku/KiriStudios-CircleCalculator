package com.UI;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Graphics {
    public static void createCircleImage(float durchmesser,float radius,float a,float u,JFrame jf){
        int size = 700;
        int circlesize = 300;
        BufferedImage bufferedImage = new BufferedImage(size, size/2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        g.fillRect(0,0,size,size);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g.setColor(Color.BLACK);
        g.drawArc(0, 0, circlesize,circlesize, 0, 360);

        g.setColor(Color.red);
        g.setFont(new Font("CircleFont",Font.PLAIN,49));
        g.drawLine(circlesize,circlesize/2,circlesize/2,circlesize/2);
        g.drawString("r",circlesize/2+44,circlesize/2+44);
        g.setColor(Color.green);
        g.drawLine(circlesize/2,0,circlesize/2,circlesize);
        g.drawString("d",circlesize/2-44,circlesize/2-44);
        g.setColor(Color.BLACK);
        g.setFont(new Font("CircleFont",Font.PLAIN,39));
        g.drawString("A=r²*π",size/2-14,size/2-304);
        g.drawString("A="+radius+ UI.einheit+"²*3,14",size/2-14,size/2-264);
        g.drawString("U=d*π",size/2-14,size/2-224);
        g.drawString("U="+durchmesser+UI.einheit+"*3,14",size/2-14,size/2-184);
        g.drawString("A="+a+UI.einheit+ UI.squared,size/2-14,size/2-84);
        g.drawString("U="+u+UI.einheit,size/2-14,size/2-48);

        g.dispose();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files","png","gif","jpeg");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("Circle.png"));
        fileChooser.setDialogTitle("Choose a location to save the Image");

        int selct = fileChooser.showSaveDialog(jf);

        if (selct == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(bufferedImage, "png", file);
                Toolkit.getDefaultToolkit().beep();
                Desktop.getDesktop().open(file);
                JOptionPane.showMessageDialog(jf, "Saved Image", UI.appTitle, JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
