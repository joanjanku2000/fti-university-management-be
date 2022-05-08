package al.edu.fti.universitymanagement.base.core.service.impl;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotFoundException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseServiceAbstractImpl<E extends BaseEntity, D extends BaseDto> implements BaseService<D, E> {

    private static final String SERVICE = "Service";
    protected final BaseDao<E, Long> baseDao;
    protected final BaseConverter<D, E> baseConverter;
    protected final BaseValidator<D, E> baseValidator;

    @Override
    public D save(D baseDto) {
        log.info("Base:: Saving baseDto {} ", baseDto);
        baseValidator.validate(baseDto, Operation.CREATE);
        return baseConverter.toDto(baseDao.save(baseConverter.toEntity(baseDto)));
    }

    @Override
    public D update(D baseDto) {
        E baseEntity = baseDao.findById((baseDto).getId()).
                orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND + baseDto.getId() + this.getClass().getSimpleName()
                        .substring(0, this.getClass().getSimpleName().indexOf(SERVICE))));
        baseValidator.validate(baseDto, Operation.UPDATE);
        log.info("Got entity {}", baseEntity);
        return baseConverter.toDto(baseDao.save(baseConverter.toEntity(baseDto, baseEntity)));
    }

    @Override
    public D read(Long id) {
        log.info("Base Entity is of Class {}", baseDao.getClass().getName());
        E baseEntity = baseDao.findById(id).
                orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND + id + this.getClass().getSimpleName()
                        .substring(0, this.getClass().getSimpleName().indexOf(SERVICE))));
        return baseConverter.toDto(baseEntity);
    }

    /**
     * Soft delete method
     * @param id Id of Entity
     * @return Deleted Object
     */
    @Override
    public D delete(Long id) {
        E baseEntity = baseDao.findById(id).
                orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND + id + this.getClass().getSimpleName()
                        .substring(0, this.getClass().getSimpleName().indexOf(SERVICE))));
        baseValidator.validate(baseEntity, Operation.DELETE);
        log.info("Deleting  entity");
        baseDao.delete(baseEntity);
        log.info("Deleted entity");


        return baseConverter.toDto(baseEntity);
    }

    public Page<D> findAll(RequestDto requestDto) {
        return baseDao.findAll(PageRequest.of(requestDto.getPageNumber(), requestDto.getPageSize()))
                .map(baseConverter::toDto);
    }

}
