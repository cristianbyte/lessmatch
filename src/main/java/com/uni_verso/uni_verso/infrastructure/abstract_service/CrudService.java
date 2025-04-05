package com.uni_verso.uni_verso.infrastructure.abstract_service;

import com.uni_verso.uni_verso.infrastructure.abstract_service.generic.CreateService;
import com.uni_verso.uni_verso.infrastructure.abstract_service.generic.DeleteService;
import com.uni_verso.uni_verso.infrastructure.abstract_service.generic.ReadService;
import com.uni_verso.uni_verso.infrastructure.abstract_service.generic.UpdateService;

public interface CrudService<Request, Response, Id> extends
    CreateService<Request, Response>,
    ReadService<Response, Id>,
    UpdateService<Request, Response, Id>,
    DeleteService<Id>
{
    
}
