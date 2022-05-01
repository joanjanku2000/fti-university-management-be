package al.edu.fti.universitymanagement.core.base.service;

public interface BaseService<T, E> {
    T save(T baseDto);

    T update(T baseDto);

    T read(Long id);

    T delete(Long id);
}
