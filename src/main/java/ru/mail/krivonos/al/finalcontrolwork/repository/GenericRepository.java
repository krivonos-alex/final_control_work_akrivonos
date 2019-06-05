package ru.mail.krivonos.al.finalcontrolwork.repository;

public interface GenericRepository<I, T> {

    void persist(T entity);

    void remove(T entity);

    T findById(I id);

}
