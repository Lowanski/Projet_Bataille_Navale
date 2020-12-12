package vue;

import Controleur.ece.*;
import com.ece.*;
import com.ece.Menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Accueil extends JPanel {

    private File nom_fichier_image = new File("./image/navirefond.jpg");
    private JButton jouer = new JButton("Jouer");
    private JButton aide = new JButton("Aide");
    private JButton quitter = new JButton("Quitter");
    private JPanel conteneur = new JPanel();
    private JLabel titre = new JLabel("Bataille Navale");

    public  Accueil() {
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(nom_fichier_image));
        } catch (IOException e) {
            // pb de chargement de l'image
            e.printStackTrace();
        }
        JLabel contentPane = new JLabel(icon) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(getIcon() != null)
                    g.drawImage(
                            ((ImageIcon)getIcon()).getImage(), 0, 0,
                            getWidth(), getHeight(), null);
            }
        };
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.setSize(500,500);
        contentPane.add(jouer);
        contentPane.add(aide);
        contentPane.add(quitter);
        //contentPane.setVisible(true);
        add(contentPane);
        //setVisible(true);


        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControllerPartie.jouer();
            }
        });
        aide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
