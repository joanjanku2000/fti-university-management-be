package al.edu.fti.universitymanagement.uniman.core.course.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import com.jpa.filter.dao.FilterRepo;
import com.jpa.filter.dao.FilterRepoJpaImpl;
import com.jpa.filter.dto.filter.FilterWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseService extends BaseServiceAbstractImpl<CourseEntity,CourseDto> {

    public CourseService(BaseDao<CourseEntity, Long> baseDao, BaseConverter<CourseDto, CourseEntity> baseConverter, BaseValidator<CourseDto, CourseEntity> baseValidator, FilterRepo filterRepo) {
        super(baseDao, baseConverter, baseValidator, filterRepo);
    }

    @Override
    public List<CourseDto> findAll(FilterWrap filterWrap, Class<CourseEntity> clazz) {
        return filterRepo.filter(filterWrap,clazz).stream().map(baseConverter::toDto).collect(Collectors.toList());
    }
}
