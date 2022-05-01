package al.edu.fti.universitymanagement.core.base.service;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;

public interface BaseService<T extends BaseDto, E extends BaseEntity> {
    T save(T baseDto);

    T update(T baseDto);

    T read(Long id);

    T delete(Long id);
}
