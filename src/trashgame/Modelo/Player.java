package trashgame.Modelo;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Player {

    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private List <Tiro.Tiro1> tiros1;
    private List <Tiro.Tiro2> tiros2;
    private List <Tiro.Tiro3> tiros3;
    private List <Tiro.Tiro4> tiros4;
    private boolean isVisivel;
    private int vida;

    private long lastShotTime;
    private long shootDelay = 300;


    public Player() {
        this.x = 100;
        this.y = 500;
        vida = 3;
        isVisivel = true;


        tiros1 = new ArrayList<>();
        lastShotTime = System.currentTimeMillis();

        tiros2 = new ArrayList<>();
        lastShotTime = System.currentTimeMillis();

        tiros3 = new ArrayList<>();
        lastShotTime = System.currentTimeMillis();

        tiros4 = new ArrayList<>();
        lastShotTime = System.currentTimeMillis();
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("res\\NaveLix.png");
        imagem = referencia.getImage();

        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;

        if (x < 0) {
            x = 0;
        }

        if (x > 1920 - 100) {
            x = 1920 - 100;
        }

        if (y < 0) {
            y = 0;
        }

        if (y > 1020 - 72) {
            y = 1020 - 72;
        }
    }

    public void tiroSimples1(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > shootDelay) {
            this.tiros1.add(new Tiro.Tiro1(x + largura, y + (altura / 2)));

            try {
                File soundFile = new File("res\\tiro.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            lastShotTime = currentTime;
        }
    }

    public void tiroSimples2(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > shootDelay) {
            this.tiros2.add(new Tiro.Tiro2(x + largura, y + (altura / 2)));

            try {
                File soundFile = new File("res\\tiro.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            lastShotTime = currentTime;
        }
    }

    public void tiroSimples3(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > shootDelay) {
            this.tiros3.add(new Tiro.Tiro3(x + largura, y + (altura / 2)));

            try {
                File soundFile = new File("res\\tiro.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            lastShotTime = currentTime;
        }
    }

    public void tiroSimples4(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > shootDelay) {
            this.tiros4.add(new Tiro.Tiro4(x + largura, y + (altura / 2)));

            try {
                File soundFile = new File("res\\tiro.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            lastShotTime = currentTime;
        }
    }

    public void perderVida() {
        vida--;
        tocarSom();

        if (vida <= 0) {
            setVisivel(false);
        }
    }

    private void tocarSom() {
        try {
            File soundFile = new File("res\\dano.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }



    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        int velocidade = 7;

        if (codigo == KeyEvent.VK_Z) {
            tiroSimples1();
        }
        if (codigo == KeyEvent.VK_X) {
            tiroSimples2();
        }
        if (codigo == KeyEvent.VK_C) {
            tiroSimples3();
        }
        if (codigo == KeyEvent.VK_V) {
            tiroSimples4();
        }

        if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W) {
            dy = -velocidade;
        }

        if (codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S) {
            dy = velocidade;
        }

        if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A) {
            dx = -velocidade;
        }

        if (codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D) {
            dx = velocidade;
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP) {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if(codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if(codigo == KeyEvent.VK_W) {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_S) {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_A) {
            dx = 0;
        }

        if(codigo == KeyEvent.VK_D) {
            dx = 0;
        }
    }



    public boolean isVisivel() {
        return !isVisivel;
    }

    public void setVisivel( boolean visivel ) {
        isVisivel = visivel;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getVida() {
        return vida;
    }

    public Image getImagem() {
        return imagem;
    }

    public List<Tiro.Tiro1> getTiros1() {
        return tiros1;
    }

    public List<Tiro.Tiro2> getTiros2() {
        return tiros2;
    }

    public List<Tiro.Tiro3> getTiros3() {
        return tiros3;
    }

    public List<Tiro.Tiro4> getTiros4() {
        return tiros4;
    }
}
