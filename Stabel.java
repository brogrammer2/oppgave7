import java.util.Iterator;

public class Stabel<T> implements Liste<T> {
  protected Node foran = null;
  protected Node siste;
  protected int teller;

  /*Akkurat som i StatiskTabell, saa vil telleren
  registrere hvert element som blir satt inn i listen.
  Derfor er telleren det samme som antall elementer i
  listen.*/
  public int storrelse() {
    return teller;
  }

  /*Hvis det foerste elementet i listen er det samme
  som null, saa betyr det at ingenting har blitt satt inn.
  Derfor: hvis det ikke er noe foerste element, saa er lista tom.*/
  public boolean erTom() {
    return foran == null;
  }

  /*Her settes det inn nye elementer i listen. Nye elementer
  settes inn paa starten av listen. */
  public void settInn(T element) {
    Node nyNode = new Node(element);
    nyNode.neste = foran;
    foran = nyNode;
    teller++;
  }

  public T hent() {
    if(foran != null) return foran.data;
    return null;
  }

  /*Hvis lenkelisten er tom, saa returner null. Hvis ikke saa
  */
  public T fjern() {
    if (foran == null) {
      return null;
    } else {
      Node temp = foran;
      foran = temp.neste;
      temp.neste = null;
      teller--;
      return temp.data;
    }
  }

  //Klassen som lager Node-objektene som brukes for aa lage lenkelisten.
  public class Node {
    protected T data;
    protected Node neste;

    public Node(T data) {
      this.data = data;
    }
  }

  public Iterator<T> iterator() {
    return new SuperIterator();
  }

  public class SuperIterator implements Iterator<T> {
    protected Node minPosisjon = foran;

    //fordi lenkelisten oppdaterer seg saa vil denne metoden fungere
    @Override
    public boolean hasNext() {
      return minPosisjon != null;
    }

    //
    @Override
    public T next() {
      T type = minPosisjon.data;
      minPosisjon = minPosisjon.neste;
      return type;
    }

    @Override
    public void remove() {
      //ikke implementert.
    }
  }
}
