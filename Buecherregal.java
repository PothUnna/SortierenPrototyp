import sas.*;
import java.awt.Color;
/**
 * @author 
 * @version 
 */
public class Buecherregal
{
    // Ablaufgeschwindigkeit hier anpassen
    private static final int WARTEZEIT = 500;
    
    public static final int YKANTE = 200;
    private static final int MARKERRADIUS = 4;
    // Objekte
    private View fenster;
    private Rectangle regalboden;
    
    private Circle markerRot;
    private Circle markerBlau;
    private Circle markerGruen;
    
    private Buch[] buecher;

    // Konstruktor
    public Buecherregal()
    {
        // Objekte erzeugen
        fenster = new View(500,240,"Sortiere das Bücherregal");
        buecher = new Buch[20];
        int x = 0;
        for (int i = 0; i < buecher.length; i++) {
            buecher[i] = new Buch(x);
            x = x + buecher[i].gibBreite()+1;
        }

        regalboden = new Rectangle(0,YKANTE,500,40);
        regalboden.setColor(new Color(198,139,40));
        
        
        markerRot = new Circle(0,YKANTE+10,MARKERRADIUS);
        markerRot.setColor(new Color(255,0,0));
        markerRot.setHidden(true);
        markerGruen = new Circle(0,YKANTE+20,MARKERRADIUS);
        markerGruen.setColor(new Color(0,255,0));
        markerGruen.setHidden(true);
        markerBlau = new Circle(0,YKANTE+30,MARKERRADIUS);
        markerBlau.setColor(new Color(0,0,255));
        markerBlau.setHidden(true);    
    }

    // Dienste
    public void sortiere() {
        this.zeichneRegal(2,5,1);
        // ab hier wird das Sortieren programmiert
        
        
        //this.zeichneRegal();
    }

    public void bubblesort(){
        for(int i=0;i<buecher.length;i++){
            
        }
        
    }
    
    
    private void zeichneRegal() {
        this.zeichneRegal(-1,-1,-1);
    }
    
    private void zeichneRegal(int pRot, int pGruen, int pBlau) {
        markerRot.setHidden(pRot < 0);
        markerGruen.setHidden(pGruen < 0);
        markerBlau.setHidden(pBlau < 0);
        int x = 0;
        for (int i = 0; i < buecher.length; i++) {
            buecher[i].setzeXLinks(x);
            if (i == pRot) {
                markerRot.moveTo(x+buecher[i].gibBreite()/2.0-MARKERRADIUS,YKANTE+10);
            }
            if (i == pGruen) {
                markerGruen.moveTo(x+buecher[i].gibBreite()/2.0-MARKERRADIUS,YKANTE+20);
            }
            if (i == pBlau) {
                markerBlau.moveTo(x+buecher[i].gibBreite()/2.0-MARKERRADIUS,YKANTE+30);
            }
            x = x + buecher[i].gibBreite() +1 ;
        }        
        fenster.wait(WARTEZEIT);
    }
}