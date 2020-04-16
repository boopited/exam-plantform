package demo.common;

public interface Entity<T> {
    boolean sameIdentityAs(T other);
}
