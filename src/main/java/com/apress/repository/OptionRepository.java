package com.apress.repository;

import com.apress.domain.Option;
import org.springframework.data.repository.CrudRepository;
//extending the crudrepository for its methods
public interface OptionRepository extends CrudRepository<Option, Long> {
}
