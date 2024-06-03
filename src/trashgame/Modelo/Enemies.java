package trashgame.Modelo;

import javax.swing.*;
import java.awt.*;

public class Enemies {

    public static abstract class Enemy {
        protected Image imagem;
        protected int x, y;
        protected int largura, altura;
        protected boolean isVisivel;
        protected static final int VELOCIDADE = 3;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
            isVisivel = true;
            load();
        }

        protected abstract void load();

        public abstract void update();

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

    }

    public static class Enemy1 extends Enemy {
        private static final double AMPLITUDE = 50; // Ajuste conforme necessário
        private static final double FREQUENCIA = 0.005; // Ajuste conforme necessário
        private final int originalY;

        public Enemy1(int x, int y) {
            super(x, y);
            this.originalY = y;
        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\enemy11.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }

        @Override
        public void update() {
            // Faz o papel flutuar verticalmente
            y = (int)(Math.sin(System.currentTimeMillis() * FREQUENCIA) * AMPLITUDE) + originalY;

            // Move o papel para a esquerda
            x -= VELOCIDADE;

            // Verifica se o papel saiu da tela pela esquerda
            if (x < -largura) {
                isVisivel = false;
            }
        }
    }


    public static class Enemy2 extends Enemy {
        private boolean meioAtingido = false;
        private int velocidade = VELOCIDADE; // Velocidade inicial

        public Enemy2(int x, int y) {
            super(x, y);
        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\enemy22.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }

        @Override
        public void update() {
            if (!meioAtingido && x + largura / 2 <= 960) {
                velocidade = 7;
                meioAtingido = true;
            }

            x -= velocidade;

            if (x < -largura) {
                isVisivel = false;
            }
        }
    }




    public static class Enemy3 extends Enemy {
        public Enemy3(int x, int y) {
            super(x, y);
        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\enemy33.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }

        @Override
        public void update() {
            x -= VELOCIDADE;
        }
    }

    public static class Enemy4 extends Enemy {
        private static final int VELOCIDADE_HORIZONTAL = 3;
        private static final int VELOCIDADE_VERTICAL = 2;
        private int direcaoVertical = 1;

        public Enemy4(int x, int y) {
            super(x, y);

        }

        @Override
        protected void load() {
            ImageIcon referencia = new ImageIcon("res\\enemy44.png");
            imagem = referencia.getImage();
            this.largura = imagem.getWidth(null);
            this.altura = imagem.getHeight(null);
        }

        @Override
        public void update() {
            x -= VELOCIDADE_HORIZONTAL;

            if (x + largura < 0) {
                x = 1920;
            }

            y += VELOCIDADE_VERTICAL * direcaoVertical;
            if (y + altura >= 1080) {
                direcaoVertical = -1;
            } else if (y <= 0) {
                direcaoVertical = 1;
            }
        }
    }
}
