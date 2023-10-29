package com.shyly;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String BerserkFirstUrl = "https://readberserk.com/chapter/berserk-chapter-a0/";
        String BerserkLastUrl = "https://readberserk.com/chapter/berserk-chapter-374/";
        String BerserkRandomUrl = "https://readberserk.com/chapter/berserk-chapter-";

        String OnePunchFirstUrl = "https://chapmanganato.com/manga-wd951838/chapter-1";
        String OnePunchLastUrl = "https://chapmanganato.com/manga-wd951838/chapter-194";
        String OnePunchRandomUrl = "https://chapmanganato.com/manga-wd951838/chapter-";

        String MashleFirstUrl = "https://chapmanganato.com/manga-hu985203/chapter-1";
        String MashleLastUrl = "https://chapmanganato.com/manga-hu985203/chapter-162/";
        String MashleRandomUrl = "https://chapmanganato.com/manga-hu985203/chapter-";

        JFrame jFrame = new JFrame();
        Container container;

        jFrame.setTitle("Random Reader");
        ImageIcon img = new ImageIcon("src/main/java/com/shyly/icon/IMG_2348.JPG");
        jFrame.setIconImage(img.getImage());

        jFrame.setBounds(300, 90, 470, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = jFrame.getContentPane();
        jFrame.setResizable(false);
        container.setLayout(null);

        JLabel opener = new JLabel("Pick Random Manga");
        opener.setFont(new Font("Arial", Font.BOLD, 15));
        opener.setSize(190, 20);
        opener.setLocation(1, 10);
        container.add(opener);

        labelDisplay(container,"Berserk",40);
        labelDisplay(container,"OnePunch",180);
        labelDisplay(container,"Mashle",320);

        handleFirstButton(container,40,BerserkFirstUrl);
        handleLastButton(container,40,BerserkLastUrl);
        handleRandomButton(container,40,BerserkRandomUrl,374);

        handleFirstButton(container,180,OnePunchFirstUrl);
        handleLastButton(container,180,OnePunchLastUrl);
        handleRandomButton(container,180,OnePunchRandomUrl,194);

        handleFirstButton(container,320,MashleFirstUrl);
        handleLastButton(container,320,MashleLastUrl);
        handleRandomButton(container,320,MashleRandomUrl,162);

        jFrame.setVisible(true);

    }

    private static void handleFirstButton(Container container, int x, String Url) {
        JButton button = new JButton("First");
        jButton(button,x,100);
        container.add(button);

        button.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(URI.create(Url));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static void handleLastButton(Container container, int x, String Url) {
        JButton button = new JButton("Last");
        jButton(button,x,150);
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

    private static void labelDisplay(Container container, String name, int x){
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setSize(90, 20);
        label.setLocation(x, 50);
        container.add(label);
    }

    private static void jButton(JButton button, int x, int y){
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setSize(100, 20);
        button.setLocation(x, y);
    }
}