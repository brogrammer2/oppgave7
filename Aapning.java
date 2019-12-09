public class Aapning extends HvitRute {

  public Aapning(Labyrint labyrint, int x, int y) {
    super(labyrint, x, y);
  }

  /*hvis gaa blir kalt i Aapning saa er man i maal.
  Derfor: hvis gaa blir kalt her, saa er man i maal.
  Legger ogsaa til utveien inn i listen.*/
  public void gaa(int[] komFra, String utvei) {
    //try {Thread.sleep(5000);} catch (Exception ex) {}
    utvei += "(" + (x+1) + ", " + (y+1) + ")";
    labyrint.liste.settInn(utvei); //lagre utveien i listen.
  }
}
