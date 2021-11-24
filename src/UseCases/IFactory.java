package UseCases;

public interface IFactory<T> {
    T get(String type);
}