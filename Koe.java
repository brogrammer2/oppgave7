import java.util.Iterator;

/*Siden Stabel, Koe og OrdnetLenkeliste er
essensielt de samme klassene, saa bruker jeg det
jeg allerede har laget i Stabel<T> for aa unngaa
aa maatte implementere alle disse klassene paa nytt.*/

public class Koe<T> extends Stabel<T> {

  /*Ved aa lage en Koe-konstruktoer og kalle paa
  super() saa arver jeg storrelse, erTom, settInn og fjern-metodene
  fra Stabel<T>. Koe arver ogsaa klassene Node og SuperIterator
  og deres metoder fra Stabel<T>. Det eneste som varierer mellom
  hver lenkeliste er settInn-metoden. Defor lager jeg kun den
  i de resterende lenkeliste-klassene.*/
  

  /*Hvis listen er tom, lag en nyNode og faa
  foran og siste til aa peke paa den og oek telleren. Ellers
  er den nye noden siste.neste og siste blir nyNode. Saa
  oekes telleren.*/
  public void settInn(T element) {
    if (erTom()) {
      Node nyNode = new Node(element);
      foran = nyNode;
      siste = nyNode;
      teller++;
    } else {
      Node nyNode = new Node(element);
      siste.neste = nyNode;
      siste = nyNode;
      teller++;
    }
  }
}
