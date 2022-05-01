package al.edu.fti.universitymanagement.core.base.service.impl;

import al.edu.fti.universitymanagement.core.base.dao.BaseDao;
import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import al.edu.fti.universitymanagement.core.base.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseServiceImpl<E extends BaseEntity, D extends BaseDto> implements BaseService<D> {

    private final BaseDao<E> baseDao;

    @Override
    public D save(D baseDto) {
        log.info("Saving baseDto {} ",baseDto);
        return (D) baseDto.toDto(baseDao.save((E) baseDto.toEntity()));
    }

    @Override
    public D update(D baseDto) {
        BaseEntity baseEntity = baseDao.getById(baseDto.getId());
        return (D) baseDto.toDto(baseDao.save((E) baseDto.toEntity(baseEntity)));
    }

    @Override
    public D read(Long id) {
        return null; // (D) (baseDao.getById(id));
    }

    @Override
    public D delete(Long id) {
        return null;
    }

}
