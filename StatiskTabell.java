import java.util.Iterator;

public class StatiskTabell<T> implements Tabell<T> {
  protected int arrayLengde;
  protected T[] statiskTabell;
  protected int teller = 0;

  //Konstruktoer som tar inn lengden paa arrayet og som setter lengden til arrayet.
  public StatiskTabell(int arrayLengde) {
    this.arrayLengde = arrayLengde;
    statiskTabell = (T[]) new Object[arrayLengde];
  }

  /*Telleren vil alltid vise hvor mange elementer det er
  i tabellen fordi den registrerer hvor mange elementer
  som har blitt satt inn. Derfor trenger vi bare aa returnere
  telleren naar vi vil finne storrelsen paa tabellen.*/
  public int storrelse() {
    return teller;
  }

  /*Hvis telleren er det samme som 0, saa betyr
  det at ingen elementer er satt inn. Derfor: hvis
  teller er 0 saa betyr det at tabellen er tom.*/
  public boolean erTom() {
    return teller == 0;
  }

  /*Hvis telleren ikke er stoerre enn eller det samme
  som arrayLengden saa er det fremdeles plass til flere
  elementer, og da skal det settes inn et element.
  Ellers saa skal det komme en feilmelding
  om at tabellen er full.*/
  public void settInn(T element) {
    if (!(teller >= arrayLengde)) {
      statiskTabell[teller] = element;
      teller++;
    } else {
      throw new FullTabellUnntak(arrayLengde);
    }
  }

  /*Hvis plass er mindre enn arrayLengden og plass
  er stoerre enn eller det samme som 0 (hvis det er en gyldig arrayplass)
  saa returner hva som er paa den plassen i arrayet. Ellers saa vil
  det regnes som en ugyldig plass og feilmelding sendes.*/
  public T hentFraPlass(int plass) {
    if ((plass < arrayLengde) && (plass >= 0)) {
      return statiskTabell[plass];
    } else {
      throw new UgyldigPlassUnntak(plass, storrelse());
    }
  }

  //oppretter en iterator for StatiskTabell.
  public Iterator<T> iterator() {
    return new StatiskTabellIterator();
  }

  private class StatiskTabellIterator implements Iterator<T> {
    private int posisjon = 0;

    /*Hvis listen ikke er tom, posisjon er mindre enn arrayLengde
    og statiskTabell paa plass posisjon ikke er null, saa betyr det at
    den statiske tabellen fremdeles har en neste.*/
    public boolean hasNext() {
      return !erTom() && posisjon < arrayLengde && statiskTabell[posisjon] != null;
    }

    /*Hvis det stemmer at det er en neste i tabellen, returner
    det elementet som kommer etter statiskTabell paa plass posisjon (det neste elementet).
    Ellers saa skal det returneres null.*/
    public T next() {
      if (hasNext()) {
        return statiskTabell[posisjon++];
      } else {
        return null;
      }
    }

    public void remove() {
      //ikke implementert.
    }
  }
}
