package al.edu.fti.universitymanagement.base.core.service;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import com.jpa.filter.dto.FilterWrap;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T extends BaseDto, E extends BaseEntity> {
    T save(T baseDto);

    T update(T baseDto);

    T read(Long id);

    T delete(Long id);

    Page<T> findAll(RequestDto requestDto);

    List<T> findAll(FilterWrap filterWrap, Class<E> clazz);
}
