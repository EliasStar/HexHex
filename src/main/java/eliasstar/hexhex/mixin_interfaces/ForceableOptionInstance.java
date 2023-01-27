package eliasstar.hexhex.mixin_interfaces;

public interface ForceableOptionInstance<T> {

    public T get();

    public void set(T value);

    public void forceSet(T val);

}
