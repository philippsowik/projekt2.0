/**
 * @author apries2m
 */
public class FeldDyn<T>
extends FeldAbstrakt<T>
implements FeldDefaultFuerIndexed<T> {
  private Feld<T> f = new FeldFix<T>(1);
  private int size;
  public int total() {
    return 2 + f.size();
  }
  public int size() {
    return size;
  }
  public T get(Integer p) {
    checkKey(p);
    return f.get(p);
  }
  public T set(Integer p, T v) {
    checkKey(p);
    return f.set(p, v);
  }
  // in der Vorlesung gezeigte Fassung - nicht aufgeteilt
  /*
  public void insertLast(T v) {
    if (size == f.size()) {
      FeldFix<T> fNeu = new FeldFix<T>(2 * size);
      for (int i = 0 ; i < size ; ++i) {
        fNeu.set(i, f.get(i));
      }
      f = fNeu;
    }
    f.set(size++, v);
  }
  */
  private void adjust(int s) {
    Feld<T> ff = new FeldFix<T>(s);
    for (int i = 0 ; i < size ; ++i) {
      ff.set(i, get(i));
    }
    f = ff;
  }
  private void adjustInsertLast() {
    if (size == f.size()) {
      adjust(f.size() * 2);
    }
  }
  private void adjustRemoveLast() {
     if((size==(f.size()/4))&&f.size()>=1)
    {
        adjust(f.size()/2);
     }
  }
  public void insertLast(T v) {
    adjustInsertLast();
    f.set(size++, v);
     }
    public T removeLast() {
    if (size == 0) {
      throw new java.util.NoSuchElementException();
    }
    T tmp=f.get(--size);
    adjustRemoveLast();
    return tmp;
    // hier sollte der Speicherplatz halbiert werden,
    // wenn der Fuellgrad nur noch ein Viertel betraegt
  }
}