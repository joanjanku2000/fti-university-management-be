package al.edu.fti.universitymanagement.api.base;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import al.edu.fti.universitymanagement.core.base.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseController<T extends BaseDto, S extends BaseEntity> {

    private final BaseService<T, S> baseService;

    @PostMapping
    public ResponseEntity<T> save(@RequestBody T courseDto) {
        log.info("Got {} from request", courseDto);
        return ResponseEntity.ok(baseService.save(courseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        return ResponseEntity.ok(baseService.read(id));
    }

    @PutMapping
    public ResponseEntity<T> update(@RequestBody T body) {
        return ResponseEntity.ok(baseService.update(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(@PathVariable Long id) {
        return ResponseEntity.ok(baseService.delete(id));
    }


}
