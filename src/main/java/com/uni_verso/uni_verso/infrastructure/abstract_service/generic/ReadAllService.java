package com.uni_verso.uni_verso.infrastructure.abstract_service.generic;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

public interface ReadAllService<Response> {
    Page<Response> readAll(Pageable pageable);
}