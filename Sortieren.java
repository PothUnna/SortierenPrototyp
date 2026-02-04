/**
 * Die Klasse {@code Sortieren} erzeugt ein Array mit Zufallszahlen und stellt
 * zwei Sortierverfahren bereit: BubbleSort und MergeSort.
 * <p>
 * Ziel dieser Klasse ist es, die grundlegenden Ideen von Sortieralgorithmen
 * nachvollziehbar zu machen:
 * <ul>
 *   <li>BubbleSort: vertauscht benachbarte Elemente und kann früh abbrechen, wenn alles sortiert ist.</li>
 *   <li>MergeSort: teilt das Array rekursiv in kleinere Bereiche und fügt diese anschließend sortiert zusammen.</li>
 * </ul>
 * <p>
 * Hinweis: {@code mergesort(...)} und {@code merge(...)} enthalten bewusst Lücken,
 * die von Schülern ergänzt werden sollen.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Sortieren {

    /**
     * Das zu sortierende Zahlenarray.
     * <p>
     * Es wird im Konstruktor angelegt und anschließend mit Zufallszahlen gefüllt.
     */
    private int[] zahlen;

    /**
     * Erzeugt ein Sortieren-Objekt mit einem Array der angegebenen Größe und
     * füllt es direkt mit Zufallszahlen.
     *
     * @param anzahl die Anzahl der Zahlen im Array (Array-Länge).
     *               Erwartet wird ein Wert {@code >= 0}.
     */
    public Sortieren(int anzahl) {
        zahlen = new int[anzahl];
        this.zahlenErzeugen();
    }

    /**
     * Erzeugt ein Sortieren-Objekt mit Standardgröße.
     * <p>
     * Diese Variante ist praktisch zum schnellen Testen.
     * Standard: 30 Zahlen.
     */
    public Sortieren() {
        this(30);
    }

    /**
     * Befüllt das Array {@code zahlen} mit Zufallszahlen.
     * <p>
     * Wertebereich hier: 1 bis 1000 (inklusive).
     * Bereits vorhandene Inhalte werden überschrieben.
     */
    public void zahlenErzeugen() {
        for (int i = 0; i < zahlen.length; i++) {
            zahlen[i] = (int) (Math.random() * 1000 + 1);
        }
    }

    /**
     * Gibt die ersten (bis zu) 30 Elemente des Arrays als kommaseparierte Liste aus.
     * <p>
     * Diese Methode dient nur der Kontrolle/Ansicht und ist nicht als „schöne“
     * Ausgabe gedacht. Bei sehr großen Arrays würde man nicht alles ausgeben.
     */
    public void zahlenAusgeben() {
        String text = new String();
        text = text + zahlen[0];
        for (int i = 1; i < zahlen.length && i < 30; i++) {
            text = text + ", " + zahlen[i];
        }
        System.out.println(text);
    }

    /**
     * Führt BubbleSort auf dem Array {@code zahlen} aus und misst die Laufzeit.
     * <p>
     * Es wird die optimierte Variante verwendet, die abbricht, wenn in einem
     * vollständigen Durchlauf kein Tausch mehr nötig war.
     * <p>
     * Ausgabe: Dauer in Millisekunden (ms).
     */
    public void bubblesort() {
        long start = System.nanoTime();
        this.bubblesort1();
        long ende = System.nanoTime();
        long dauer = ende - start;
        double dauerMs = dauer / 1000000.0;
        System.out.println("Dauer Bubblesort: " + dauerMs + "ms");
    }

    /**
     * Sortiert das Array {@code zahlen} mit BubbleSort (in-place).
     * <p>
     * Idee:
     * <ul>
     *   <li>In jedem Durchlauf „wandert“ das größte noch unsortierte Element nach hinten.</li>
     *   <li>Der zu prüfende Bereich wird daher nach jedem Durchlauf um 1 kleiner.</li>
     *   <li>Wenn in einem Durchlauf nichts getauscht wird, ist das Array sortiert.</li>
     * </ul>
     * <p>
     * Laufzeit:
     * <ul>
     *   <li>Worst Case: O(n²)</li>
     *   <li>Best Case (schon sortiert): O(n) durch frühen Abbruch</li>
     * </ul>
     */
    private void bubblesort1() {
        int i = 0;
        boolean sortiert = false;
        while (!sortiert) {
            i++;
            sortiert = true;
            for (int j = 0; j < zahlen.length - i; j++) {
                if (zahlen[j] > zahlen[j + 1]) {
                    // tauschen
                    int zahl = zahlen[j];
                    zahlen[j] = zahlen[j + 1];
                    zahlen[j + 1] = zahl;
                    sortiert = false;
                }
            }
        }
    }

    /**
     * Führt MergeSort auf dem Array {@code zahlen} aus und misst die Laufzeit.
     * <p>
     * Vorgehen:
     * <ol>
     *   <li>Ein Hilfsarray {@code tmp} wird einmalig angelegt (gleiche Länge wie {@code zahlen}).</li>
     *   <li>Dann wird rekursiv sortiert (Teilen und Zusammenführen).</li>
     * </ol>
     * <p>
     * Ausgabe: Dauer in Millisekunden (ms).
     */
    public void mergesort() {
        long start = System.nanoTime();
        int[] tmp = new int[zahlen.length];
        this.mergesort(zahlen, 0, zahlen.length - 1, tmp);
        long ende = System.nanoTime();
        long dauer = ende - start;
        double dauerMs = dauer / 1000000.0;
        System.out.println("Dauer Mergesort: " + dauerMs + "ms");
    }

    /**
     * Rekursiver Kern von MergeSort: sortiert den Teilbereich {@code array[linkeGrenze..rechteGrenze]}.
     * <p>
     * Grundidee (Divide & Conquer):
     * <ul>
     *   <li><b>Teilen:</b> Bestimme die Mitte und teile den Bereich in zwei Hälften.</li>
     *   <li><b>Erobern:</b> Sortiere beide Hälften rekursiv.</li>
     *   <li><b>Kombinieren:</b> Füge beide sortierten Hälften mit {@link #merge(int[], int, int, int, int, int[])}
     *       wieder zu einem sortierten Bereich zusammen.</li>
     * </ul>
     * <p>
     * Abbruchbedingung:
     * <ul>
     *   <li>Wenn {@code linkeGrenze >= rechteGrenze}, enthält der Bereich 0 oder 1 Element und ist damit bereits sortiert.</li>
     * </ul>
     *
     * @param array        das zu sortierende Array
     * @param linkeGrenze  Startindex des zu sortierenden Bereichs (inklusive)
     * @param rechteGrenze Endindex des zu sortierenden Bereichs (inklusive)
     * @param tmp          Hilfsarray zum Zwischenspeichern beim Zusammenführen (gleiche Länge wie {@code array})
     */
    private void mergesort(int[] array, int linkeGrenze, int rechteGrenze, int[] tmp) {
        if (linkeGrenze < rechteGrenze) {
            // 1) Bestimme die Mitte des Bereichs (Index der Trennstelle).

            // 2) Sortiere die linke Hälfte rekursiv.

            // 3) Sortiere die rechte Hälfte rekursiv.

            // 4) Füge beide Hälften mit merge(...) wieder sortiert zusammen.
            //    Dabei ist links:  [linkeGrenze .. mitte]
            //          rechts: [mitte+1 .. rechteGrenze]
        }
    }

    /**
     * Führt zwei bereits sortierte Teilbereiche des Arrays zu einem sortierten Gesamtbereich zusammen.
     * <p>
     * Annahme (Vorbedingung):
     * <ul>
     *   <li>{@code array[linksStart..linksEnde]} ist sortiert.</li>
     *   <li>{@code array[rechtsStart..rechtsEnde]} ist sortiert.</li>
     * </ul>
     * <p>
     * Ergebnis (Nachbedingung):
     * <ul>
     *   <li>{@code array[linksStart..rechtsEnde]} ist nach dem Merge sortiert.</li>
     * </ul>
     * <p>
     * Ablaufidee:
     * <ol>
     *   <li>Vergleiche die jeweils „vordersten“ Elemente beider Teilbereiche.</li>
     *   <li>Schreibe das kleinere (oder bei Gleichheit das linke, um Stabilität zu erreichen) ins Hilfsarray.</li>
     *   <li>Wenn ein Teilbereich leer ist, kopiere den Rest des anderen Teilbereichs.</li>
     *   <li>Kopiere den gemergten Bereich aus dem Hilfsarray zurück in {@code array}.</li>
     * </ol>
     * <p>
     * Hinweis zur Stabilität:
     * <ul>
     *   <li>Mit {@code <=} beim Vergleich (links vor rechts) bleibt MergeSort stabil.</li>
     * </ul>
     *
     * @param array      das Array, in dem gemergt wird
     * @param linksStart Startindex des linken Teilbereichs (inklusive)
     * @param linksEnde  Endindex des linken Teilbereichs (inklusive)
     * @param rechtsStart Startindex des rechten Teilbereichs (inklusive)
     * @param rechtsEnde Endindex des rechten Teilbereichs (inklusive)
     * @param hilfe      Hilfsarray (gleiche Länge wie {@code array}); genutzt wird nur der Bereich {@code [linksStart..rechtsEnde]}
     */
    private void merge(int[] array, int linksStart, int linksEnde,
                       int rechtsStart, int rechtsEnde, int[] hilfe) {

        int ziel = linksStart;      // Position im Hilfsarray, an die als nächstes geschrieben wird
        int links = linksStart;     // aktueller Index im linken Teilbereich
        int rechts = rechtsStart;   // aktueller Index im rechten Teilbereich

        // 1) Solange in beiden Teilbereichen noch Elemente vorhanden sind:
        //    Vergleiche array[links] und array[rechts] und kopiere das kleinere Element nach hilfe[ziel].
        //    Danach den entsprechenden Zeiger (links oder rechts) und auch ziel weiter bewegen.
        //    Tipp: Mit <= bleibt der Merge stabil (bei Gleichheit nimmt man das linke Element zuerst).

        // 2) Kopiere eventuell übrig gebliebene Elemente aus dem linken Teilbereich nach hilfe.
        //    (Das passiert, wenn der rechte Teilbereich zuerst „leer“ ist.)

        // 3) Kopiere eventuell übrig gebliebene Elemente aus dem rechten Teilbereich nach hilfe.
        //    (Das passiert, wenn der linke Teilbereich zuerst „leer“ ist.)

        // 4) Kopiere den fertigen Bereich aus hilfe zurück nach array,
        //    aber nur von linksStart bis rechtsEnde (nicht das ganze Array!).
    }
    
    /**
     * Prüft, ob das Array sortiert ist und gibt eine Meldung in der Konsole aus.
     */
    public void sortiertTesten(){
        boolean sortiert = true;
        for(int i=0;i<zahlen.length-1;i++){
            if(zahlen[i]>zahlen[i+1]){
                sortiert=false;
                break;
            }
        }
        if(sortiert){
            System.out.println("Die Zahlen sind sortiert.");
        }
        else{
            System.out.println("Die Zahlen sind NICHT sortiert.");
        }
    }
}
