package al.edu.fti.universitymanagement.base.api;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import com.jpa.filter.dto.FilterWrap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseController<T extends BaseDto, S extends BaseEntity> {

    protected final BaseService<T, S> baseService;

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

    @GetMapping
    public ResponseEntity<Page<T>> findAll() {
        return ResponseEntity.ok(baseService.findAll(new RequestDto()));
    }

    @GetMapping("/filter")
    public abstract ResponseEntity<List<T>> filter(@RequestBody FilterWrap filterWrap) throws ClassNotFoundException;

}
