package trashgame;

import trashgame.Modelo.Enemies;
import trashgame.Modelo.Fase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Container extends JFrame {

    public Container() {
        setTitle("Tela Inicial");
        setSize(700, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon imagemFundo = new ImageIcon("res\\telainicial1.png");
        JLabel labelFundo = new JLabel(imagemFundo);
        add(labelFundo);

        labelFundo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 280 && x <= 430 && y >= 620 && y <= 677) {
                    iniciarJogo();
                }
            }
        });

        setVisible(true);
    }

    public void iniciarJogo() {
        Fase fase = new Fase();
        fase.setPreferredSize(new Dimension(1280, 920));
        getContentPane().removeAll();
        getContentPane().add(fase);
        validate();
        repaint();
        fase.requestFocusInWindow();
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Container::new);
    }

}
