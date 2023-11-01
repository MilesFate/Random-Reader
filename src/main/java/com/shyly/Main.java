package com.shyly;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

public class Main {
    private static final String BerserkFirstUrl = "https://readberserk.com/chapter/berserk-chapter-a0/";
    private static final String BerserkLastUrl = "https://readberserk.com/chapter/berserk-chapter-374/";
    private static final String BerserkRandomUrl = "https://readberserk.com/chapter/berserk-chapter-";
    private static final String OnePunchFirstUrl = "https://chapmanganato.com/manga-wd951838/chapter-1";
    private static final String OnePunchLastUrl = "https://chapmanganato.com/manga-wd951838/chapter-194";
    private static final String OnePunchRandomUrl = "https://chapmanganato.com/manga-wd951838/chapter-";
    private static final String MashleFirstUrl = "https://chapmanganato.com/manga-hu985203/chapter-1";
    private static final String MashleLastUrl = "https://chapmanganato.com/manga-hu985203/chapter-162/";
    private static final String MashleRandomUrl = "https://chapmanganato.com/manga-hu985203/chapter-";
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        Container container;

        jFrame.setTitle("Random Reader");
        ImageIcon img = new ImageIcon("src/main/resources/icon/IMG_2348.JPG");
        jFrame.setIconImage(img.getImage());

        Color background = new Color(255, 225, 204);
        Color foreground = new Color(153, 0, 76);
        Color onepunchColour = new Color(255, 128, 0);
        Color mashleColour = new Color(153, 51, 225);

        jFrame.setBounds(300, 90, 470, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = jFrame.getContentPane();
        jFrame.getContentPane().setBackground(background);
        jFrame.setResizable(false);
        container.setLayout(null);

        JLabel opener = new JLabel("Pick Random Manga");
        opener.setFont(new Font("Arial", Font.BOLD, 15));
        opener.setForeground(foreground);
        opener.setSize(190, 20);
        opener.setLocation(150, 10);
        container.add(opener);

        labelDisplay(container,Color.BLACK,"Berserk",40);
        labelDisplay(container,onepunchColour,"OnePunch",180);
        labelDisplay(container,mashleColour,"Mashle",320);

        handleButton(container,"First",40,100,BerserkFirstUrl);
        handleButton(container,"Last",40,150,BerserkLastUrl);
        handleRandomButton(container,40,BerserkRandomUrl,374);

        handleButton(container,"First",180,100,OnePunchFirstUrl);
        handleButton(container,"Last",180,150,OnePunchLastUrl);
        handleRandomButton(container,180,OnePunchRandomUrl,194);

        handleButton(container,"First",320,100,MashleFirstUrl);
        handleButton(container,"Last",320,150,MashleLastUrl);
        handleRandomButton(container,320,MashleRandomUrl,162);

        playSong(container);
        jFrame.setVisible(true);
    }

    public static void playSong(Container container){
        try {
            // This is not mine, this is from helluva boss
            // https://www.youtube.com/@SpindleHorse
            // https://www.patreon.com/VivienneMedrano
            // its good show watch it
            String musicLocation = "src/main/resources/music/Stolas Speaks.wav";

            File musicPath = new File(musicLocation);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);

                musicButton(container, clip);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void musicButton(Container container, Clip clip) {
        var ref = new Object() {
            int musicStatus = 0;
        };

        JButton button = new JButton("music");
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setSize(80, 30);
        button.setLocation(350, 10);
        container.add(button);

        button.addActionListener(e -> {
            if(ref.musicStatus == 1){
                ref.musicStatus = 0;
                clip.stop();
            }else{
                ref.musicStatus = 1;
                clip.start();
            }
        });
    }

    private static void handleButton(Container container,String type, int x, int y, String Url) {
        JButton button = new JButton(type);
        jButton(button,x,y);
        container.add(button);

        button.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(URI.create(Url));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static void handleRandomButton(Container container, int x, String Url, int bound) {
        JButton button = new JButton("Random");
        jButton(button,x,200);
        container.add(button);

        button.addActionListener(e -> {
            try {
                Random r = new Random();
                int end = r.nextInt(1, bound);
                String temp = Url+ end;
                Desktop.getDesktop().browse(URI.create(temp));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static void labelDisplay(Container container,Color colour, String name, int x){
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setSize(90, 20);
        label.setForeground(colour);
        label.setLocation(x, 50);
        container.add(label);
    }

    private static void jButton(JButton button, int x, int y){
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setSize(100, 40);
        button.setLocation(x, y);
    }
}