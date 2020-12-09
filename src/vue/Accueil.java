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


public class Accueil extends JFrame {

    String nom_fichier_image = "5f783227f8cd9de65e3f11b4b1b7576e.jpg";
    private JButton jouer = new JButton("Jouer");
    private JButton aide = new JButton("Aide");
    private JButton quitter = new JButton("Quitter");
    private JPanel conteneur = new JPanel();

    private JTextArea txt = new JTextArea("Zone de Texte");

    public  Accueil() {

        setBounds(0, 0, 1500, 800);
        setTitle("Bataille Navale");
        setSize(1500, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conteneur.setLayout(null);
        jouer.setBounds(new Rectangle(100,150,80,50));
        aide.setBounds(new Rectangle(100,210,80,50));
        quitter.setBounds(new Rectangle(100,270,80,50));
        conteneur.add(txt);
        conteneur.add(aide);
        conteneur.add(quitter);
        conteneur.add(jouer);

        setContentPane(conteneur);
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
