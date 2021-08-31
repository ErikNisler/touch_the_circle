package com.hibernate.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard extends JPanel{
    static int count = 0;
    public static JFrame jFrame = new JFrame("Touch the circle");
    public static JPanel jPanel = new JPanel(null);
    int secs = 60;
    boolean isRunning = false;

    static Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
            public void run() {
                --secs;
                displayTime.setText(String.valueOf(secs));
                if (secs == 0){
                    JOptionPane.showMessageDialog(jPanel,"Konec hry!","Skore",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        };

    JLabel displayTime = new JLabel();

    public void setGameBoard() {

        displayTime.setText(String.valueOf(secs));
        displayTime.setBounds(400,20,20,20);

        Random x = new Random();
        Random y = new Random();

        int max = 800;
        int min = 1;

        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setResizable(true);

        jFrame.addWindowListener(new WindowAdapter() {
                                     @Override
                                     public void windowClosing(WindowEvent e) {
                                         secs = 0;
                                         System.exit(0);
                                     }
                                 });

        JLabel counter = new JLabel("Points: ");
        JLabel num = new JLabel(String.valueOf(count));
        JLabel circle = new JLabel("0");
        circle.setFont(new Font("Serif",Font.PLAIN,30));
        JLabel circle2 = new JLabel("0");
        circle2.setFont(new Font("Serif",Font.PLAIN,30));

        counter.setBounds(10,10,200,20);

        num.setBounds(70,10,200,20);

        Dimension size = circle.getPreferredSize();
        circle.setBounds(x.nextInt(max-80),y.nextInt(max-80), size.width, size.height);

        Dimension size2 = circle2.getPreferredSize();

        jFrame.add(jPanel);
        jPanel.add(circle);
        jPanel.add(circle2);
        jPanel.add(counter);
        jPanel.add(num);
        jPanel.add(displayTime);

        jFrame.setSize(max,max);
        jFrame.setVisible(true);

        circle.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!isRunning){
                    timerTask.run();
                    timer.scheduleAtFixedRate(timerTask, 1000, 1000);
                    isRunning = true;
                } else {

                    circle.setVisible(false);
                    if (count > 10) {
                        circle2.setFont(new Font("Serif", Font.PLAIN, 20));
                    }
                    if (count > 25) {
                        circle2.setFont(new Font("Serif", Font.PLAIN, 10));
                    }
                    if (count > 40) {
                        circle2.setFont(new Font("Serif", Font.PLAIN, 5));
                    }

                    circle2.setBounds(x.nextInt(max - 80), y.nextInt(max - 80), size.width, size.height);
                    circle2.setVisible(true);
                    count++;
                    num.setText(String.valueOf(count));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        circle2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                circle2.setVisible(false);

                if (count > 10){
                    circle.setFont(new Font("Serif",Font.PLAIN,20));

                }
                if (count > 25){
                    circle.setFont(new Font("Serif",Font.PLAIN,10));
                }
                if (count > 40){
                    circle.setFont(new Font("Serif",Font.PLAIN,5));
                }


                circle.setBounds(x.nextInt(max-80),y.nextInt(max-80), size2.width, size2.height);
                circle.setVisible(true);
                count++;
                num.setText(String.valueOf(count));

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

}
