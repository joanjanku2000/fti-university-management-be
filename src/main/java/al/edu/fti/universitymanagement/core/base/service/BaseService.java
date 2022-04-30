package al.edu.fti.universitymanagement.core.base.service;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;

public interface BaseService<T extends BaseDto> {
    T save(T baseDto);

    T update(T baseDto);

    T read(Long id);

    T delete(Long id);
}
