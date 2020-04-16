package demo.common;

public interface ValueObject<T> {
    boolean sameValueAs(T other);
}
