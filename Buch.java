import sas.*;
import java.awt.Color;
/**
 * @author 
 * @version 
 */
public class Buch
{
    private static final String[] TITELLISTE = {"Algorithmen","Datenstrukturen","Automatentheorie","Graphentheorie",
        "Datenbanken","Java","Netzwerke","Kryptographie","Datenschutz","Urheberrecht","Objektorientierung",
        "SQL","Raspberry Pi","Python","Roboter","App-Entwicklung","Informatik","3D-Druck","Linux","Server"};
    // Objektvariablen der Bezugsobjekte
    private Rectangle buch;
    private Text etikett;
    // Attribute (Instanzvariablen)
    private int hoehe;
    private int breite;
    private String titel;
    // Konstruktor
    public Buch(int px)
    {
        hoehe = (int)(Math.random()*50+120);
        breite = 24;
        buch = new Rectangle(px,Buecherregal.YKANTE-hoehe,breite,hoehe);
        
        int r = (int) (255 * Math.random());
        int g = (int) (255 * Math.random());
        int b = (int) (255 * Math.random());
        buch.setColor(new Color(r, g, b));
        
        titel = TITELLISTE[(int) (Math.random()*TITELLISTE.length)];
        etikett = new Text(px+breite/2-6,Buecherregal.YKANTE-5,titel);
        etikett.setFontSansSerif(true, 12);
        etikett.turn(px + breite/2-6, Buecherregal.YKANTE-5, -90);
        
    }

    // Dienste
    public int gibHoehe() {
        return hoehe;
    }
    
    public int gibBreite() {
        return breite;
    }
    
    public String gibTitel() {
        return titel;
    }
    
    public void setzeXLinks(int px){
        double dx = px - buch.getShapeX();
        buch.move(dx,0); 
        etikett.move(dx,0);
    }
}
