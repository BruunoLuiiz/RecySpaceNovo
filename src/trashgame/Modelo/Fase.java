package trashgame.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Player player;
    private List<Enemies.Enemy1> enemy1;
    private List<Enemies.Enemy2> enemy2;
    private List<Enemies.Enemy3> enemy3;
    private List<Enemies.Enemy4> enemy4;
    private List<Stars> stars;
    private boolean emJogo;
    private Score score;
    private boolean gameRestart;
    private boolean somGameOverReproduzido = false;
    private Image coracao;

    public Fase() {
        try {
            File soundFile = new File("res\\musicafundo.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clipMusica = AudioSystem.getClip();
            clipMusica.open(audioInputStream);
            clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
            clipMusica.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("res\\telapreta.png");
        fundo = referencia.getImage();

        ImageIcon coracaoIcon = new ImageIcon("res\\coracao.png");
        coracao = coracaoIcon.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        Timer timer = new Timer(5, this);
        timer.start();

        inicializaInimigos();
        inicializaStars();
        emJogo = true;
        gameRestart = false;

        score = new Score();
    }

    public void inicializaInimigos() {

        enemy1 = new ArrayList<>();
        enemy2 = new ArrayList<>();
        enemy3 = new ArrayList<>();
        enemy4 = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * 20000 + 1920);
            int y = (int) (Math.random() * 850 + 10);
            enemy1.add(new Enemies.Enemy1(x, y));
        }

        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * 30000 + 1920);
            int y = (int) (Math.random() * 850 + 10);
            enemy2.add(new Enemies.Enemy2(x, y));
        }

        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * 40000 + 1920);
            int y = (int) (Math.random() * 850 + 10);
            enemy3.add(new Enemies.Enemy3(x, y));
        }

        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * 50000 + 1920);
            int y = (int) (Math.random() * 850 + 10);
            enemy4.add(new Enemies.Enemy4(x, y));
        }

    }

    public void updateInimigos() {
        for (Enemies.Enemy1 inimigo : enemy1) {
            inimigo.update();
        }

        for (Enemies.Enemy2 inimigo : enemy2) {
            inimigo.update();
        }

        for (Enemies.Enemy3 inimigo : enemy3) {
            inimigo.update();
        }

        for (Enemies.Enemy4 inimigo : enemy4) {
            inimigo.update();
        }
    }

    public void inicializaStars() {
        int[] coordenadas = new int[300];
        stars = new ArrayList<>();

        for (int i = 0; i < coordenadas.length; i++) {
            int x = (int) (Math.random() * 1920 + 0);
            int y = (int) (Math.random() * 1536 + 0);
            stars.add(new Stars(x, y));
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);

            graficos.setColor(Color.WHITE);
            graficos.setFont(new Font("Arial", Font.BOLD, 20));
            graficos.drawString("Score: " + score.getScore(), 10, 30);

            for (int i = 0; i < player.getVida(); i++) {
                int coracaoX = 10;
                int coracaoY = 50;
                g.drawImage(coracao, coracaoX + (i * 40), coracaoY, this);
            }

            for (Stars q : stars) {
                q.load();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            List<Tiro.Tiro1> tiros1 = player.getTiros1();
            for (Tiro.Tiro1 m : tiros1) {
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            List<Tiro.Tiro2> tiros2 = player.getTiros2();
            for (Tiro.Tiro2 m : tiros2) {
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            List<Tiro.Tiro3> tiros3 = player.getTiros3();
            for (Tiro.Tiro3 m : tiros3) {
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            List<Tiro.Tiro4> tiros4 = player.getTiros4();
            for (Tiro.Tiro4 m : tiros4) {
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (Enemies.Enemy1 in : enemy1) {
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            for (Enemies.Enemy2 in : enemy2) {
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            for (Enemies.Enemy3 in : enemy3) {
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            for (Enemies.Enemy4 in : enemy4) {
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

        } else {
            ImageIcon fimJogo = new ImageIcon("res\\fimdejogo.png");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);

            graficos.setColor(Color.WHITE);
            graficos.setFont(new Font("Arial", Font.BOLD, 20));
            graficos.drawString("Aperte R para Reiniciar o jogo", 580, 580);

            if (!somGameOverReproduzido) {
                try {
                    File soundFile = new File("res/gameover.wav");
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    somGameOverReproduzido = true;
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }

        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRestart) {
            if (somGameOverReproduzido) {
                somGameOverReproduzido = false;
            }

            inicializaInimigos();
            inicializaStars();
            player = new Player();
            player.load();
            score = new Score();
            emJogo = true;
            gameRestart = false;
        }

        player.update();

        for (Stars on : stars) {
            if (on.isVisivel()) {
                on.update();
            }
        }

        List<Tiro.Tiro1> tiros1 = player.getTiros1();
        for (int i = 0; i < tiros1.size(); i++) {
            Tiro.Tiro1 m = tiros1.get(i);
            if (m.isVisivel()) {
                m.update();
            } else {
                tiros1.remove(i);
            }
        }

        List<Tiro.Tiro2> tiros2 = player.getTiros2();
        for (int i = 0; i < tiros2.size(); i++) {
            Tiro.Tiro2 m = tiros2.get(i);
            if (m.isVisivel()) {
                m.update();
            } else {
                tiros2.remove(i);
            }
        }

        List<Tiro.Tiro3> tiros3 = player.getTiros3();
        for (int i = 0; i < tiros3.size(); i++) {
            Tiro.Tiro3 m = tiros3.get(i);
            if (m.isVisivel()) {
                m.update();
            } else {
                tiros3.remove(i);
            }
        }

        List<Tiro.Tiro4> tiros4 = player.getTiros4();
        for (int i = 0; i < tiros4.size(); i++) {
            Tiro.Tiro4 m = tiros4.get(i);
            if (m.isVisivel()) {
                m.update();
            } else {
                tiros4.remove(i);
            }
        }

        for (int o = 0; o < enemy1.size(); o++) {
            Enemies.Enemy1 in = enemy1.get(o);
            if (in.isVisivel()) {
                in.update();
            } else {
                enemy1.remove(o);
            }
        }

        for (int o = 0; o < enemy2.size(); o++) {
            Enemies.Enemy2 in = enemy2.get(o);
            if (in.isVisivel()) {
                in.update();
            } else {
                enemy2.remove(o);
            }
        }
        for (int o = 0; o < enemy3.size(); o++) {
            Enemies.Enemy3 in = enemy3.get(o);
            if (in.isVisivel()) {
                in.update();
            } else {
                enemy3.remove(o);
            }
        }

        for (int o = 0; o < enemy4.size(); o++) {
            Enemies.Enemy4 in = enemy4.get(o);
            if (in.isVisivel()) {
                in.update();
            } else {
                enemy4.remove(o);
            }
        }

        checarColisoes();
        updateInimigos();
        repaint();
    }

    public void checarColisoes() {
        Rectangle formaNave = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaEnemy2;
        Rectangle formaEnemy3;
        Rectangle formaEnemy4;
        Rectangle formaTiro1;
        Rectangle formaTiro2;
        Rectangle formaTiro3;
        Rectangle formaTiro4;

        for (Enemies.Enemy1 tempEnemy1 : enemy1) {
            formaEnemy1 = tempEnemy1.getBounds();
            if (formaNave.intersects(formaEnemy1)) {
                player.perderVida();
                tempEnemy1.setVisivel(false);
                if (player.isVisivel()) {
                    emJogo = false;
                }
            }
        }

        for (Enemies.Enemy2 tempEnemy2 : enemy2) {
            formaEnemy2 = tempEnemy2.getBounds();
            if (formaNave.intersects(formaEnemy2)) {
                player.perderVida();
                tempEnemy2.setVisivel(false);
                if (player.isVisivel()) {
                    emJogo = false;
                }
            }
        }

        for (Enemies.Enemy3 tempEnemy3 : enemy3) {
            formaEnemy3 = tempEnemy3.getBounds();
            if (formaNave.intersects(formaEnemy3)) {
                player.perderVida();
                tempEnemy3.setVisivel(false);
                if (player.isVisivel()) {
                    emJogo = false;
                }
            }
        }

        for (Enemies.Enemy4 tempEnemy4 : enemy4) {
            formaEnemy4 = tempEnemy4.getBounds();
            if (formaNave.intersects(formaEnemy4)) {
                player.perderVida();
                tempEnemy4.setVisivel(false);
                if (player.isVisivel()) {
                    emJogo = false;
                }
            }
        }

        List<Tiro.Tiro1> tiros1 = player.getTiros1();
        for (Tiro.Tiro1 tempTiro1 : tiros1) {
            formaTiro1 = tempTiro1.getBounds();
            for (Enemies.Enemy1 tempEnemy1 : enemy1) {
                formaEnemy1 = tempEnemy1.getBounds();
                if (formaTiro1.intersects(formaEnemy1)) {
                    tempEnemy1.setVisivel(false);
                    tempTiro1.setVisivel(false);
                    score.incrementScore(5);
                }
            }
        }

        List<Tiro.Tiro2> tiros2 = player.getTiros2();
        for (Tiro.Tiro2 tempTiro2 : tiros2) {
            formaTiro2 = tempTiro2.getBounds();
            for (Enemies.Enemy2 tempEnemy2 : enemy2) {
                formaEnemy2 = tempEnemy2.getBounds();
                if (formaTiro2.intersects(formaEnemy2)) {
                    tempEnemy2.setVisivel(false);
                    tempTiro2.setVisivel(false);
                    score.incrementScore(10);
                }
            }
        }

        List<Tiro.Tiro3> tiros3 = player.getTiros3();
        for (Tiro.Tiro3 tempTiro3 : tiros3) {
            formaTiro3 = tempTiro3.getBounds();
            for (Enemies.Enemy3 tempEnemy3 : enemy3) {
                formaEnemy3 = tempEnemy3.getBounds();
                if (formaTiro3.intersects(formaEnemy3)) {
                    tempEnemy3.setVisivel(false);
                    tempTiro3.setVisivel(false);
                    score.incrementScore(25);
                }
            }
        }

        List<Tiro.Tiro4> tiros4 = player.getTiros4();
        for (Tiro.Tiro4 tempTiro4 : tiros4) {
            formaTiro4 = tempTiro4.getBounds();
            for (Enemies.Enemy4 tempEnemy4 : enemy4) {
                formaEnemy4 = tempEnemy4.getBounds();
                if (formaTiro4.intersects(formaEnemy4)) {
                    tempEnemy4.setVisivel(false);
                    tempTiro4.setVisivel(false);
                    score.incrementScore(50);
                }
            }
        }
    }

    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);

            if (e.getKeyCode() == KeyEvent.VK_R) {
                gameRestart = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyRelease(e);
        }
    }
}