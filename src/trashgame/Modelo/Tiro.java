package trashgame.Modelo;

import javax.swing.*;
import java.awt.*;

public abstract class Tiro {

    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean isVisivel;

    protected static final int LARGURA = 1920;

    public Tiro(int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
        load();
    }

    protected abstract void load();

    public void update() {
        this.x += 10;
        if (this.x > LARGURA) {
            isVisivel = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        isVisivel = visivel;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Image getImagem() {
        return imagem;
    }

    // Subclasse Tiro1
    public static class Tiro1 extends Tiro {
        public Tiro1(int x, int y) {
            super(x, y);
        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\tiro1.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }
    }

    // Subclasse Tiro2
    public static class Tiro2 extends Tiro {
        public Tiro2(int x, int y) {
            super(x, y);
        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\tiro2.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }
    }

    // Subclasse Tiro3
    public static class Tiro3 extends Tiro {
        public Tiro3(int x, int y) {
            super(x, y);
        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\tiro3.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }
    }

    // Subclasse Tiro4
    public static class Tiro4 extends Tiro {
        public Tiro4(int x, int y) {
            super(x, y);
        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\tiro4.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }
    }
}
