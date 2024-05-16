package pt.isec.a2019135835.fichaEx.fourinarow.model.memento;

public interface IOriginator {
    IMemento save();

    void restore(IMemento memento);
}
