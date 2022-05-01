package al.edu.fti.universitymanagement.core.course.service.impl;

import al.edu.fti.universitymanagement.core.base.service.BaseService;
import al.edu.fti.universitymanagement.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.core.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public LocationDto save(LocationDto baseDto) {
        log.info("No here");
        return null;
    }

    @Override
    public LocationDto update(LocationDto baseDto) {
        return null;
    }

    @Override
    public LocationDto read(Long id) {
        return null;
    }

    @Override
    public LocationDto delete(Long id) {
        return null;
    }
}
