public class OrdnetLenkeliste<T extends Comparable<T>> extends Stabel<T> {

  /*Paa samme maate som i Koe saa arver jeg de like metodene
  fra superklassen Stabel<T>, slik at jeg bare behoever aa
  lage settInn-metoden i OrdnetLenkeliste.*/


  /*Metoden som brukes for aa sette inn nye elementer i den
  ordnede lenkelisten. Hvis det ikke er noen elementer i listen, saa
  lag et nytt element og sett det elementet til aa vaere baade det foerste
  og siste elementet i listen, siden det er det eneste elementet i listen.
  */
  public void settInn(T element) {
    Node nyNode = new Node(element);
    if (erTom()) {
      foran = nyNode;
      siste = nyNode;
      teller++;
    } else if (element.compareTo(foran.data) <= 0) {
      nyNode.neste = foran;
      foran = nyNode;
      teller++;
    } else {
      Node hode = foran;
      Node hale = foran.neste;

      while(hale != null) {
        if (element.compareTo(hale.data) < 0) {
          break;
        }
        hode = hale;
        hale = hode.neste;
      }
      nyNode.neste = hale;
      hode.neste = nyNode;
      teller++;
    }
  }
}
