package com.lessmatch.lessmatch.infrastructure.generic;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

public interface ReadAllService<Response> {
    Page<Response> getAll(Pageable pageable);
}