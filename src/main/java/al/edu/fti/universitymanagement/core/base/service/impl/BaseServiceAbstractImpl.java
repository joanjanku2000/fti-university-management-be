package al.edu.fti.universitymanagement.core.base.service.impl;

import al.edu.fti.universitymanagement.core.base.converter.BaseConverter;
import al.edu.fti.universitymanagement.core.base.dao.BaseDao;
import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BaseServiceAbstractImpl<E extends BaseEntity, D extends BaseDto> implements al.edu.fti.universitymanagement.core.base.service.BaseService<D,E> {

    @Autowired
    private BaseDao<E,Long> baseDao;

    @Autowired
    private BaseConverter<D, E> baseConverter;

    @Override
    public D save(D baseDto) {
        log.info("Base:: Saving baseDto {} ", baseDto);
        return baseConverter.toDto(baseDao.save(baseConverter.toEntity(baseDto)));
    }

    @Override
    public D update(D baseDto) {
        E baseEntity = baseDao.getById(((BaseDto)baseDto).getId());
        return baseConverter.toDto(baseDao.save(baseConverter.toEntity(baseDto, baseEntity)));
    }

    @Override
    public D read(Long id) {
        log.info("Base Entity is of Class {}",baseDao.getClass().getName());
        E baseEntity  = baseDao.getById(id);
        return baseConverter.toDto(baseEntity);
    }

    @Override
    public D delete(Long id) {
        return null;
    }

}
