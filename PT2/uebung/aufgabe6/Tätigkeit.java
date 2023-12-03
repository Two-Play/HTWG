package aufgabe6;

public interface Tätigkeit {

    public double getTime();
    public void  add(Tätigkeit tätigkeit);
    public  void  remove(Tätigkeit tätigkeit);
    public int getAnzahl();
}
