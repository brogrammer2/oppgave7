import java.util.Iterator;
import java.util.Arrays;

public abstract class Rute {
  //Alle ruter har en tilhoerighet til en labyrint. De har ogsaa koordinater.
  //For aa kunne vandre rundt i labyrinten maa vi ogsaa vite hva nord, soer, oest og vest er. Disse koordinatene lagres i int-arrays
  Labyrint labyrint;
  int[] nord = new int[2];
  int[] soer = new int[2];
  int[] oest = new int[2];
  int[] vest = new int[2];
  public int x;
  public int y;

  public Rute(Labyrint labyrint, int x, int y) {
    this.labyrint = labyrint;
    this.x = x;
    this.y = y;
    nord[0] = x;
    nord[1] = y - 1;
    soer[0] = x;
    soer[1] = y + 1;
    oest[0] = x + 1;
    oest[1] = y;
    vest[0] = x - 1;
    vest[1] = y;
  }

  abstract char tilTegn();

  //komFra er koordinatene man kom fra. Utvei er Stringen som viser og lagrer utveien.
  public void gaa(int[] komFra, String utvei) {
    utvei += "("+ (x+1) + ", "+ (y+1) +" ) --> "; //utvei oppdateres og lagrer informasjon.
    if(!Arrays.equals(komFra, nord)) { //hvis vi ikke kom fra nord...
      sjekkNabo(nord, utvei); //gaa nord
    }
    if(!Arrays.equals(komFra, soer)) {
      sjekkNabo(soer, utvei);
    }
    if(!Arrays.equals(komFra, oest)) {
      sjekkNabo(oest, utvei);
    }
    if(!Arrays.equals(komFra, vest)) {
      sjekkNabo(vest, utvei);
    }

  }

  //metode som returnerer koordinatene vi har faatt inn.
  public int[] minPosisjon() {
    int[] minPosisjon = new int[2];
    minPosisjon[0] = x;
    minPosisjon[1] = y;
    return minPosisjon;
  }

  private void sjekkNabo(int[] naboKoordinater, String utvei) {
    if (!(x >= 1 && x < labyrint.lengde)) {
      return;
    }

    if (!(y >= 1 && y <= labyrint.hoeyde)) {
      return;
    }

    Rute rute = labyrint.hentRute(naboKoordinater); //hent naborutens koordinater

    if(rute instanceof HvitRute) { //vi gaar bare i hvite ruter.
      rute.gaa(minPosisjon(), utvei); //rekursivt kall. (fortsett slik til vi har funnet svaret)
    }

  }

  public void finnUtvei() {
    String utvei = ""; //oppretter utvei-Stringen som skal lagre utveien.
       gaa(minPosisjon(), utvei); //begynner aa gaa.
  }
}
