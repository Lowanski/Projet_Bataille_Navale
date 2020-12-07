package vue;

import Controleur.ece.*;
import com.ece.*;
import com.ece.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {

    private JButton jouer = new JButton("Jouer");
    private JPanel conteneur = new JPanel();
    private JTextArea txt = new JTextArea("Zone de Texte");

    public  Accueil() {
        JFrame accueil = new JFrame("Bataille Navale");
        setTitle("Bataille Navale");
        setSize(1500, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conteneur.setLayout(null);
        jouer.setBounds(new Rectangle(100,150,80,50));
        conteneur.add(txt);
        conteneur.add(jouer);
        setContentPane(conteneur);
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.main(new String[]{"text"});
            }
        });
    }

}