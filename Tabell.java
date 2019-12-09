public interface Tabell<T> extends Iterable<T> {
  public int storrelse();
  public boolean erTom();
  public void settInn(T element);
  public T hentFraPlass(int plass);
}
