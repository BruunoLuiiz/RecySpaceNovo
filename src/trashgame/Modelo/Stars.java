package trashgame.Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Stars {

    private Image imagem;
    private int x, y;
    private int largura;
    private boolean isVisivel;


    public Stars( int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("res\\Stars.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        imagem.getHeight(null);
    }

    public void update() {
        if (this.x < 0) {
            this.x = largura;
            Random a = new Random();
            int m = a.nextInt(1000);
            this.x = m + 1920;
            Random r = new Random();
            this.y = r.nextInt(1536);
        } else {

            int VELOCIDADE = 5;
            this.x -= VELOCIDADE;

        }
    }

    public boolean isVisivel() {
        return isVisivel;
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
}
