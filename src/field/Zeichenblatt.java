package field;

/** Zeichenblatt
*
* Einfache Java-Klasse zum Zeichnen einfacher Grafikelemente
* Enthaelt auch Turtle-Funktionalitaet
*
* @author Rudolf Berrendorf
* @version 1.0
*/
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Zeichenblatt {

   //==========================================================================

   // Jave Frame Objekt
   private JFrame frame;

   // Bildschirmausdehung
   private Insets bildschirmAusdehnung;

   // Bildschirmanzeigefenster (Breite und Hoehe)
   private int breite;
   private int hoehe;

   // Bild (Vordergrund + Hintergrund).
   private BufferedImage vordergrundBild;
   private BufferedImage hintergrundBild;

   // Grafikobjekt (Vordergrund + Hintergrund)
   private Graphics2D vordergrundGrafik;
   private Graphics2D hintergrundGrafik;

   // Vorder- und Hintergrundfarbe fuer Grafikobjekte
   private Color vordergrundFarbe;
   private Color hintergrundFarbe;


   // Benutzerkoordinatensystem
   private double xmin, ymin, xmax, ymax;


   // Turtle Grafik
   // aktuelle Position
   private double x;
   private double y;

   // Bewegungsrichtung
   private double bewegungsRichtung;


   //==========================================================================

   /** neues Zeichenblatt erzeugen
    * @param breite Breite des Fensters in Pixeln 
    * @param hoehe Hoehe des Fensters in Pixeln 
    */
   public Zeichenblatt(int breite, int hoehe) {

      // Groessenangaben pruefen
      if (breite <= 0)
         throw new RuntimeException("Bildschirmbreite nicht zulaessig");
      this.breite = breite;
      if (hoehe <= 0)
         throw new RuntimeException("Bildschirmhoehe nicht zulaessig");
      this.hoehe = hoehe;

      // zwei Bilder erzeugen (Vordergrund + Hintergrund).
      vordergrundBild = new BufferedImage(breite, hoehe, BufferedImage.TYPE_INT_RGB);
      hintergrundBild  = new BufferedImage(breite, hoehe, BufferedImage.TYPE_INT_RGB);

      // Grafikobjekte dazu besorgen
      vordergrundGrafik = (Graphics2D) vordergrundBild.getGraphics();
      hintergrundGrafik  = (Graphics2D) hintergrundBild.getGraphics();

      // Farben fuer Grafikvordergrund und -hintergrund setzen
      vordergrundFarbe = Color.black;
      hintergrundFarbe = Color.white;

      // Default-Benutzerkoordinatensystem setzen
      benutzerkoordinaten(0.0, 0.0, 1.0, 1.0);

      // Turtle Grafik initialisieren
      x = 0.0;
      y = 0.0;
      bewegungsRichtung = 0.0;

      // Frame anlegen
      frame = new JFrame();
      ImageIcon icon = new ImageIcon(vordergrundBild);
      frame.setContentPane(new JLabel(icon));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.pack();
      frame.setVisible(true);

      // Bildschirmausdehung bestimmen
      bildschirmAusdehnung = frame.getInsets();

      // Bild initialisieren
      this.loeschen();

   }


   //==========================================================================

   /** Benutzerkoordinatensystem definieren
    * @param xmin minimale x-Koordinate des Benutzerkoordinatensystems
    * @param ymin minimale y-Koordinate des Benutzerkoordinatensystems
    * @param xmax maximale x-Koordinate des Benutzerkoordinatensystems
    * @param ymax maximale y-Koordinate des Benutzerkoordinatensystems
    */
   public void benutzerkoordinaten(double xmin, double ymin, double xmax, double ymax) {
      this.xmin = xmin;
      this.ymin = ymin;
      this.xmax = xmax;
      this.ymax = ymax;
   }


   //--------------------------------------------------------------------------
   // Umrechnung vom Benutzer- in Bildschirmkoordinatensystem

   // Koordinatenumrechnung von Benutzer- in Bildschirmkoordinaten
   private double mapX(double x) {
	   return breite  * (x - xmin) / (double)(xmax - xmin);
   }

   private double mapY(double y) {
	   return hoehe * (ymax - y) / (double)(ymax - ymin);
   }


   //--------------------------------------------------------------------------
   // Distanzumrechnung von Benutzer- in  Bildschirmkoordinaten

   private double distanzX(double x) {
	   return x * breite  / Math.abs(xmax - xmin);
   }

   private double distanzY(double y) {
	   return y * hoehe / Math.abs(ymax - ymin);
   }


   //--------------------------------------------------------------------------
   // Koordinatenumrechnung von Bildschirm- in Benutzerkoordinaten

   public double remapX(double x) {
	   return (xmax - xmin) * (x - bildschirmAusdehnung.left) / breite  + xmin;
   }

   public double remapY(double y) {
	   return (ymax - ymin) * (hoehe - y + bildschirmAusdehnung.top) / hoehe + ymin;
   }


   //==========================================================================

   /** Zeichenblatt loeschen
    */
   public void loeschen() {
	   // mit Hintergrundfarbe fensterausfuellendes Rechteck malen
       hintergrundGrafik.setColor(hintergrundFarbe);
       hintergrundGrafik.fillRect(0, 0, breite, hoehe);

	   // wieder auf Vordergrundfarbe wechseln
       hintergrundGrafik.setColor(vordergrundFarbe);
   }


   //--------------------------------------------------------------------------

   /** Zeichenblatt anzeigen
    */
   public void anzeigen() {
      vordergrundGrafik.drawImage(hintergrundBild, 0, 0, null);
	   frame.repaint();
   }


   //--------------------------------------------------------------------------

   /** Pause einlegen
    * @param delay Verzoegrung in Millisekunden
    */
   public void pause(int delay) {
       anzeigen();
       try {
      // Thread.currentThread().sleep(delay); **********************************
         Thread.sleep(delay);
       } catch (InterruptedException e) { }
   }


   //--------------------------------------------------------------------------

   /** Vordergrundfarbe setzen
    * @param farbe Vordergrundfarbe
    */
   public void setVordergrundFarbe(Color farbe) {
      vordergrundFarbe = farbe;
      hintergrundGrafik.setColor(farbe);
   }


   //--------------------------------------------------------------------------

   /** Hintergrundfarbe setzen (wird erst nach naechstem Loeschen des Bildes aktiv)
    * @param farbe Hintergrundfarbe
    */
   public void setHintergrundFarbe(Color farbe) {
	   hintergrundFarbe = farbe;
   }


   //==========================================================================

   /** Punkt an aktueller Position zeichnen als einen Pixel
    */
   public void punkt() {
      int x1 = (int) mapX(getX());
      int y1 = (int) mapY(getY());

      hintergrundGrafik.fillRect(x1, y1, 1, 1);
   }


   //--------------------------------------------------------------------------

   /** Punkt zeichnen als einen Pixel
    * @param x x-Koordinate des Punktes
    * @param y y-Koordinate des Punktes
    */
   public void punkt(double x, double y) {
      int x1 = (int) mapX(x);
      int y1 = (int) mapY(y);

      hintergrundGrafik.fillRect(x1, y1, 1, 1);
   }


   //--------------------------------------------------------------------------

   /** Punkt zeichnen mit vorgegebenem Durchmesser
    * @param x x-Koordinate des Punktes
    * @param y y-Koordinate des Punktes
    * @param durchmesser Durchmesser des Punktes in Koordinateneinheiten
    */
   public void punkt(double x, double y, double durchmesser) {
      int x1 = (int) mapX(x);
      int y1 = (int) mapY(y);
      int dx = (int) distanzX(durchmesser);
      int dy = (int) distanzY(durchmesser);

      hintergrundGrafik.fill(new Ellipse2D.Double(x1 - dx/2, y1 - dy/2, dx, dy));
   }


   //--------------------------------------------------------------------------

   /** Rechteck zeichnen an aktueller Position
    * @param weite Ausdehnung in x-Richtung
    * @param hoehe Ausdehnung in y-Richtung
    */
   public void rechteck(double weite, double hoehe) {
      int x1 = (int) mapX(getX());
      int y1 = (int) mapY(getY());
      int w1 = (int) distanzX(weite);
      int h1 = (int) distanzY(hoehe);

      hintergrundGrafik.fillRect(x1, y1-h1, w1, h1);
   }


   //--------------------------------------------------------------------------

   /** Rechteck zeichnen
    * @param x x-Koordinate des Referenzpunkts (untere linke Ecke)
    * @param y y-Koordinate des Referenzpunkts (untere linke Ecke)
    * @param weite Ausdehnung in x-Richtung
    * @param hoehe Ausdehnung in y-Richtung
    */
   public void rechteck(double x, double y, double weite, double hoehe) {
      int x1 = (int) mapX(x);
      int y1 = (int) mapY(y);
      int w1 = (int) distanzX(weite);
      int h1 = (int) distanzY(hoehe);

      hintergrundGrafik.fillRect(x1, y1-h1, w1, h1);
   }


   //--------------------------------------------------------------------------

   private void anzeigeBild(int x, int y, int w, int h, Image img) {
	   // Bild drehen und anzeigen
      hintergrundGrafik.rotate(Math.toRadians(bewegungsRichtung), x, y);
      hintergrundGrafik.drawImage(img, x-w/2, y-h/2, w, h, null);
      hintergrundGrafik.rotate(Math.toRadians(-bewegungsRichtung), x, y);
   }


   //--------------------------------------------------------------------------

   /** Anzeige eines Bildes, das als Datei vorliegen muss, an aktueller Position
    * @param bildname Dateiname eines Bildes (gif, jpeg, png,...)
    */
   public void zeichneBild(String bildname) {
      ImageIcon icon = new ImageIcon(bildname);
      if (icon.getImageLoadStatus() != MediaTracker.COMPLETE)
         throw new RuntimeException("Bild " + bildname + " nicht vorhanden");
      Image image = icon.getImage();

      int x1 =  (int) mapX(getX());
      int y1 =  (int) mapY(getY());
      int w1 = image.getWidth(null);
      int h1 = image.getHeight(null);

      anzeigeBild(x1, y1, w1, h1, image);
   }

   //--------------------------------------------------------------------------

   /** Anzeige eines Bildes, das als Datei vorliegen muss, mit Vorgabe Position und Groesse
    * @param bildname Dateiname eines Bildes (gif, jpeg, png,...)
    * @param x x-Koordinate des Bildzentrums
    * @param y y-Koordinate des Bildzentrums
    * @param weite Weite des Bildes
    * @param hoehe Hoehe des Bildes
    */
   public void zeichneBild(String bildname, double x, double y, double weite, double hoehe) {
	   ImageIcon icon = new ImageIcon(bildname);
       if (icon.getImageLoadStatus() != MediaTracker.COMPLETE)
           throw new RuntimeException("Bild " + bildname + " nicht vorhanden");
       Image image = icon.getImage();

      int x1 =  (int) mapX(x);
      int y1 =  (int) mapY(y);
      int w1 = (int) distanzX(weite);
      int h1 = (int) distanzY(hoehe);

      anzeigeBild(x1, y1, w1, h1, image);
   }


   //--------------------------------------------------------------------------

   /** Anzeige eines Bildes, das als Datei vorliegen muss, mit Vorgabe Position
    * @param bildname Dateiname eines Bildes (gif, jpeg, png,...)
    * @param x x-Koordinate der Bildmitte
    * @param y y-Koordinate der Bildmitte
    */
   public void zeichneBild(String bildname, double x, double y) {
	   ImageIcon icon = new ImageIcon(bildname);
       if (icon.getImageLoadStatus() != MediaTracker.COMPLETE)
           throw new RuntimeException("Bild " + bildname + " nicht vorhanden");
       Image image = icon.getImage();

      int x1 =  (int) mapX(x);
      int y1 =  (int) mapY(y);
      int w1 = image.getWidth(null);
      int h1 = image.getHeight(null);

      anzeigeBild(x1, y1, w1, h1, image);
   }

   //==========================================================================

   // Turtle Grafik

   /** aktuelle Position verschieben (ohne zu zeichnen)
    * @param x x-Koordinate neue Position
    * @param y y-Koordinate neue Position
    */
   public void gehe(double x, double y) {
      this.x = x;
      this.y = y;
   }


   //--------------------------------------------------------------------------

   /** Linie ziehen von alter nach neuer Position
    * @param x x-Koordinate neue Position
    * @param y y-Koordinate neue Position
    */
   public void linie(double x, double y) {
	   // zeichne Linie von aktueller Position zu neuer Position
       hintergrundGrafik.draw(new Line2D.Double(mapX(getX()), mapY(getY()), mapX(x), mapY(y)));
	
      // das ist unsere neue Position
      this.x = x;
      this.y = y;
   }


   //--------------------------------------------------------------------------

   /** Linie ziehen nach x Schritte weiter
    * @param schritte Anzahl Schritte in aktueller Bewegungsrichtung
    */
   public void geheWeiter(double schritte) {
       double xAlt = x;
       double yAlt = y;
       x += schritte * Math.cos(Math.toRadians(bewegungsRichtung));
       y += schritte * Math.sin(Math.toRadians(bewegungsRichtung));
       hintergrundGrafik.draw(new Line2D.Double(mapX(xAlt), mapY(yAlt), mapX(x), mapY(y)));
   }


   //--------------------------------------------------------------------------

   /** Bewegungsrichtung veraendern
    * @param winkel Drehwinkelaenderung entgegen dem Uhrzeigersinn
    */
   public void drehen(double winkel) {
	   bewegungsRichtung += winkel;
   }


   //--------------------------------------------------------------------------

   /** aktuelle X-Position ermitteln
    * @return x-Position
    */
   public double getX() {
	   return this.x;
   }


   //--------------------------------------------------------------------------

   /** aktuelle Y-Position ermitteln
    * @return y-Position
    */
   public double getY() {
	   return this.y;
   }


   //==========================================================================

}
