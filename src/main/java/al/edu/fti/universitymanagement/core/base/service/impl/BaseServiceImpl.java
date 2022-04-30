package al.edu.fti.universitymanagement.core.base.service.impl;

import al.edu.fti.universitymanagement.core.base.dao.BaseDao;
import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import al.edu.fti.universitymanagement.core.base.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl<E extends BaseEntity, D extends BaseDto> implements BaseService<D> {

    private final BaseDao<E> baseDao;

    @Override
    public D save(D baseDto) {
        return (D) BaseDto.toDto(baseDao.save((E) baseDto.toEntity()));
    }

    @Override
    public D update(D baseDto) {
        BaseEntity baseEntity = baseDao.getById(baseDto.getId());
        return (D) BaseDto.toDto(baseDao.save((E) baseDto.toEntity(baseEntity)));
    }

    @Override
    public D read(Long id) {
        return (D) BaseDto.toDto(baseDao.getById(id));
    }

    @Override
    public D delete(Long id) {
        return null;
    }
}
