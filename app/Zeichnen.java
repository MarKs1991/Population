package app;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import scene.Kreis;
import javax.swing.*;

public class Zeichnen extends JFrame {

    //Berechnung Population
    public Population p;
   //Grafik
    public ArrayList<Kreis> g1;            //ArrayListe der Punkte von G
    public ArrayList<Kreis> h1;            //ArrayListe der Punkte von H

        //UI
    private static final long serialVersionUID = 1L;
    private BufferedImage backBuffer;

    public void fensterZeichnen() {
        setTitle("Populationen Blue vs. Red");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backBuffer = new BufferedImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

    }

    public Zeichnen(double g, double h, double s, double r, int delay) {
        p = new Population(g,h,s,r, this, delay);
        this.g1 = new ArrayList<Kreis>();
        this.h1 = new ArrayList<Kreis>();
        fensterZeichnen();
        erstelleKreise();
        setVisible(true);
        p.init();
    }

    public void erstelleKreise() {
        for (int i = 0; i < p.getG(); i++)    //For-Schleife generiert Punkte von der Population G
        {
            Kreis k = new Kreis();
            k.diameter = 80;
            k.positionX = Math.random() * Constants.WINDOW_WIDTH; //position x von den Kreisen von G
            k.positionY = Math.random() * Constants.WINDOW_HEIGHT; //posision y von den Kreisen von G
            this.g1.add(k);
        }

        for (int i = 0; i < p.getH(); i++) {//For-Schleife generiert Punkte von der Population H
            Kreis k = new Kreis();
            k.diameter = 80;
            k.positionX = Math.random() * Constants.WINDOW_WIDTH; //position x von den Kreisen von H
            k.positionY = Math.random() * Constants.WINDOW_HEIGHT; //posision y von den Kreisen von H
            this.h1.add(k);
        }
    }

    public void loescheKreise (boolean hLoeschen) {
        int loeschenG = (int) (Math.floor(Math.random() * g1.size()));
        int loeschenH = (int) (Math.floor(Math.random() * h1.size()));


        if (g1.size()>0 && !hLoeschen) {
            g1.remove(loeschenG);
        }
        if (h1.size() > 0 && hLoeschen) {
            h1.remove(loeschenH);
        }
    }

    public void draw (){

            Graphics g = getGraphics();
            Graphics bbg = backBuffer.getGraphics();

            //Zeichnet Background und malt in grau an
            bbg.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            bbg.setColor(Color.lightGray);

            bbg.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            //gibt Farbe für H an
            bbg.setColor(Color.blue);
           // System.out.println("g1: " + g1);


            //  Malt Kreise von H
          for (Kreis zeichnen : h1)
                bbg.fillOval((int) (zeichnen.positionX), (int) (zeichnen.positionY), 10, 10);
          //gibt Farbe für H an
           bbg.setColor(Color.red);
           //  Malt Kreise von G
            for(Kreis zeichnen : g1)
                bbg.fillOval((int) (zeichnen.positionX), (int) (zeichnen.positionY), 10, 10);
            g.drawImage(backBuffer, 0, 0, null);
        }

        public static void main(String[] args) {
        double g, h, s, r;
        int delay;
        Scanner sc = new Scanner(System.in);
        System.out.println("Populationssimulation\nTruppenanzahl G: ");
        g = Double.parseDouble(sc.next());
        System.out.println("Truppenanzahl H: ");
        h = Double.parseDouble(sc.next());
        System.out.println("Feuerkraft s für G: ");
        s = Double.parseDouble(sc.next());
        System.out.println("Feuerkraft für H r: ");
        r = Double.parseDouble(sc.next());
        System.out.println("Delay: ");
        delay = Integer.parseInt(sc.next());
        new Zeichnen(g, h, s, r, delay);
        }
    }





