package vue;

import Controleur.ece.ControllerPartie;
import com.ece.*;
import com.ece.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Jeu extends JPanel {
    private JButton[][] buttonjoueur = new JButton[15][15];
    private JButton[][] buttonenemi = new JButton[15][15];
    String[] cons = {" 0 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", "10 ", "11 ", "12 ", "13 ", "14 "};
    String[] let = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
    Icon icon = new ImageIcon("./image/eau2.png");

    public Jeu() {
    }


    public JPanel dessiner(Grille g, Grille o) {
        Navire[][] tableau1 = g.getTableau();

        JPanel fenetre = new JPanel(new BorderLayout());

        setSize(1800, 900);
        setLayout(new BorderLayout(0, 0));
        JPanel panel = new JPanel(new GridLayout(15, 15));
        JPanel lettres = new JPanel(new GridLayout(1, 16));
        JPanel chiffres = new JPanel(new GridLayout(15, 1));
        JPanel commandes = new JPanel(new GridLayout(3, 3));

        lettres.setPreferredSize(new Dimension(640, 40));
        panel.setPreferredSize(new Dimension(600, 600));
        chiffres.setPreferredSize(new Dimension(40, 640));
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        chiffres.setBorder(new EmptyBorder(0, 0, 0, 0));
        lettres.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        fenetre.setSize(480, 480);
        fenetre.setMaximumSize(new Dimension(480, 480));

        Border whiteline = BorderFactory.createLineBorder(Color.WHITE, 3);
        Border blackline = BorderFactory.createLineBorder(Color.black, 1);

        JButton v = new JButton("Tirer");
        v.setBorder(whiteline);
        v.setPreferredSize(new Dimension(40, 40));
        v.setOpaque(true);
        v.setBackground(Color.BLACK);
        v.setForeground(Color.WHITE);
        v.addActionListener(ControllerPartie::actionMenu);
        commandes.add(v);

        JButton c = new JButton("Déplacer");
        c.setBorder(whiteline);
        c.setPreferredSize(new Dimension(40, 40));
        c.setOpaque(true);
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.addActionListener(ControllerPartie::actionMenu);
        commandes.add(c);

        JButton d = new JButton("Sauver et quitter");
        d.setBorder(whiteline);
        d.setPreferredSize(new Dimension(40, 40));
        d.setOpaque(true);
        d.setBackground(Color.BLACK);
        d.setForeground(Color.WHITE);
        d.addActionListener(ControllerPartie::actionMenu);
        commandes.add(d);



        for (int i = 0; i < let.length; i++) {
            JButton button = new JButton(let[i]);
            button.setPreferredSize(new Dimension(40, 40));
            button.setBorder(blackline);
            button.setOpaque(true);
            button.setBackground(Color.GRAY);
            button.setForeground(Color.BLACK);
            lettres.add(button);
        }
        for (int i = 0; i < 15; i++) {
            JButton b = new JButton("" + cons[i]);
            b.setBorder(blackline);
            b.setPreferredSize(new Dimension(40, 40));

            b.setOpaque(true);
            b.setBackground(Color.GRAY);
            b.setForeground(Color.BLACK);

            chiffres.add(b);
            for (int j = 0; j < 15; j++) {
                JButtonmod button = new JButtonmod();

                if (tableau1[j][i] != null) {
                    button.setText("" + tableau1[j][i].getId());
                    button.setPreferredSize(new Dimension(40, 40));
                    button.setIntx(j);
                    button.setInty(i);
                    button.addActionListener(ControllerPartie::actionPerformed);
                    button.setBorder(blackline);
                    button.setBackground(Color.LIGHT_GRAY);
                    panel.add(button);
                    button.setOpaque(true);
                    buttonjoueur[j][i] = button;
                } else {
                    button.setBorder(blackline);
                    button.setText("---");
                    button.setPreferredSize(new Dimension(40, 40));
                    panel.add(button);
                    //button.setOpaque(true);
                    button.setIcon(icon);
                    buttonjoueur[j][i] = button;
                }

            }
        }
        panel.setBorder(blackline);
        fenetre.add(panel, BorderLayout.CENTER);
        fenetre.add(lettres, BorderLayout.NORTH);
        fenetre.add(chiffres, BorderLayout.WEST);
        add(fenetre, BorderLayout.WEST);
        add(commandes, BorderLayout.SOUTH);
        add(dessinerE(o), BorderLayout.EAST);
        return (this);
    }

    public JPanel dessinerE(Grille g) {
        Navire[][] tableau1 = g.getTableau();
        g.dessinerenemi();
        g.dessiner();
        JPanel fenetre = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(15, 15));
        JPanel lettres = new JPanel(new GridLayout(1, 16));
        JPanel chiffres = new JPanel(new GridLayout(15, 1));

        lettres.setPreferredSize(new Dimension(640, 40));
        panel.setPreferredSize(new Dimension(600, 600));
        chiffres.setPreferredSize(new Dimension(40, 640));
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        chiffres.setBorder(new EmptyBorder(0, 0, 0, 0));
        lettres.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        fenetre.setSize(480, 480);
        fenetre.setMaximumSize(new Dimension(480, 480));

        Border blackline = BorderFactory.createLineBorder(Color.black, 1);


        for (int i = 0; i < let.length; i++) {
            JButton button = new JButton(let[i]);
            button.setPreferredSize(new Dimension(40, 40));
            button.setBorder(blackline);
            button.setOpaque(true);
            button.setBackground(Color.GRAY);
            button.setForeground(Color.BLACK);
            lettres.add(button);
        }
        for (int i = 0; i < 15; i++) {
            JButton b = new JButton("" + cons[i]);
            b.setBorder(blackline);
            b.setPreferredSize(new Dimension(40, 40));

            b.setOpaque(true);
            b.setBackground(Color.GRAY);
            b.setForeground(Color.BLACK);

            chiffres.add(b);
            for (int j = 0; j < 15; j++) {
                JButton button = new JButton("---");
                if (tableau1[j][i] != null) {
                    int temp = casetouche(tableau1[j][i], i, j);
                    if (temp == 1) {
                        button.setText("000");
                        button.setPreferredSize(new Dimension(40, 40));
                        button.setBorder(blackline);
                        panel.add(button);
                        button.setOpaque(true);
                        button.setBackground(Color.LIGHT_GRAY);
                    } else if (temp == 2) {
                        button.setText("" + tableau1[j][i].getId());
                        button.setPreferredSize(new Dimension(40, 40));
                        button.setBorder(blackline);
                        panel.add(button);
                        button.setOpaque(true);
                        button.setBackground(Color.YELLOW);
                    } else {
                        button.setText("---");
                        button.setPreferredSize(new Dimension(40, 40));
                        button.setBorder(blackline);
                        panel.add(button);
                        button.setIcon(icon);
                        button.setOpaque(true);

                    }
                } else {
                    button.setBorder(blackline);
                    button.setIcon(icon);
                    button.setPreferredSize(new Dimension(40, 40));

                    panel.add(button);
                    button.setOpaque(true);
                    button.setBackground(Color.CYAN);
                }
            }
        }
        panel.setBorder(blackline);
        fenetre.add(panel, BorderLayout.CENTER);
        fenetre.add(lettres, BorderLayout.NORTH);
        fenetre.add(chiffres, BorderLayout.WEST);
        return (fenetre);
    }

    public int casetouche(Navire n, int i, int j) {
        int[] tempo = n.getToucherTab();
        if (n.getOrientation() == "verticale") {
            if ((tempo[i - n.getCoord().y]) == 1) {
                return 1;
            } else if ((tempo[i - n.getCoord().y]) == 2) {
                return 2;
            } else return 0;
        } else {
            if ((tempo[j - n.getCoord().x]) == 1) {
                return 1;
            } else if ((tempo[j - n.getCoord().x]) == 2) {
                return 2;
            } else {
                return 0;
            }
        }
    }

}
