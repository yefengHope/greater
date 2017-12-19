package hf.com.test.genericTest.basic;

public class Pair<T extends Number & Comparable> {

    private T first;
    private T second;

    public Pair() {

    }

    public Pair(T first) {
        this.first = first;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static void main(String[] args) {
        Pair<Integer> pair = new Pair();
        pair.setFirst(1);
        pair.setSecond(222);
        System.out.println(pair.toString());
    }
}
