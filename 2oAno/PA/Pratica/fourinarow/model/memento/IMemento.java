package pt.isec.a2019135835.fichaEx.fourinarow.model.memento;

public interface IMemento {
    default Object getSnapshot() { return null; } //devolve um objeto e depois damos cast para fourinarow
}
