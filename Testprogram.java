import java.io.File;
import java.util.Iterator;

class Testprogram {
  public static void main(String[] args) throws Exception {

    File fil = new File("LabyrintTest3.txt");
    Labyrint l =  Labyrint.lesFraFil(fil);
    System.out.println(l.toString());
    //System.out.println(l.hentRute(1, 1).tilTegn());

    /*l.finnUtveiFra(6, 4);
    for(String loesning : l.liste) {
      System.out.println(loesning);
    }
    System.out.println(); */

  }
}
