import java.util.Scanner;
import java.io.*;

public class Labyrint {
  public Rute[][] ruteArray;
  public int lengde;
  public int hoeyde;
  Stabel<String> liste = new Stabel<String>(); //En String-liste som lagrer labyrintens

  public Rute hentRute(int x, int y) {
    return ruteArray[x][y];
  }

  public Rute hentRute(int[] koordinater) {
    return ruteArray[koordinater[0]][koordinater[1]];
  }

  public void settMinimalUtskrift() {
    //Du må sørge for at dette gjør at programmet ikke skriver ut noe annet enn eventuelle feilmeldinger
  }

  private Labyrint(int lengde, int hoeyde) {
      this.lengde = lengde;
      this.hoeyde = hoeyde;
  }

  private void setLabyrint(Rute[][] ruteArray) {
    this.ruteArray = ruteArray;
  }

  public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
    Scanner leser = new Scanner(fil);
    int linjeTeller = 0; //holder oversikt over hvilken rad vi er paa
    String linje = leser.nextLine();

    String[] splittTallet = linje.split(" ");
    int x, y;
    y = Integer.parseInt(splittTallet[0]);
    x = Integer.parseInt(splittTallet[1]);
    Rute[][] ruteArray = new Rute[x][y]; //lager en ny labyrint med de innleste koordinatene

    Labyrint l = new Labyrint(x, y);

    while (leser.hasNextLine()) {
      linje = leser.nextLine();
      for (int i = 0; i < linje.length(); i++) { //loep gjennom linjen i filen
        if (String.valueOf(linje.charAt(i)).equals(".")) { //hvis i er det samme som punktum... konverterer String til char
          if (i == 0 || linjeTeller == 0 || i == x - 1 || linjeTeller == y - 1) { //og hvis det er helt paa kanten av labyrinten... i og linjeteller starter paa 0
            Aapning aapen = new Aapning(l, i, linjeTeller); //lag en ny aapning.
            ruteArray[i][linjeTeller] = aapen; //plasser aapningen i labyrint-rutenettet. i = x, linjeTeller = y
          } else {
          HvitRute hvit = new HvitRute(l, i, linjeTeller); //hvis ikke saa er det en vanlig hvit rute
          ruteArray[i][linjeTeller] = hvit; //legg til den vanlige hvite ruten i labyrint-rutenettet
        }
        } else {
          SortRute sort = new SortRute(l, i, linjeTeller); //hvis det ikke er en hvit rute saa er det en sort rute
          ruteArray[i][linjeTeller] = sort; //legg til den sorte ruten i labyrint-rutenettet
        }
      }
      linjeTeller++;
    }
    l.setLabyrint(ruteArray); //setter rutenettet vi skapte her til aa vaere labyrintes 2D-array.
    return l;
  }

  //metode som printer ut labyrinten.
  @Override
  public String toString() {
    String labyrintStreng = "";

    for(int y = 0; y < hoeyde; y++) { //labyrintens hoeyde
      for(int x = 0; x < lengde; x++) { //faar med seg alle x-ene paa hver y-akse
        labyrintStreng += Character.toString(ruteArray[x][y].tilTegn()); //legg til rutenes tegn
      }
      labyrintStreng += "\n";
    }
    return labyrintStreng;
  }

  //metode som finner veien ut fra gitte koordinater. Returnerer ogsaa listen.
  public Stabel<String> finnUtveiFra(int kol, int rad) {
      liste = new Stabel<String>();
      kol -= 1;
      rad -= 1;
      hentRute(kol, rad).finnUtvei();
      return liste;
  }


}
