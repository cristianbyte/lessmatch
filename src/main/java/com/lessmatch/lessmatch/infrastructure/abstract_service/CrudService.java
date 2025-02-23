package com.lessmatch.lessmatch.infrastructure.abstract_service;

import com.lessmatch.lessmatch.infrastructure.abstract_service.generic.CreateService;
import com.lessmatch.lessmatch.infrastructure.abstract_service.generic.DeleteService;
import com.lessmatch.lessmatch.infrastructure.abstract_service.generic.ReadService;
import com.lessmatch.lessmatch.infrastructure.abstract_service.generic.UpdateService;

public interface CrudService<Request, Response, Id> extends
    CreateService<Request, Response>,
    ReadService<Response, Id>,
    UpdateService<Request, Response, Id>,
    DeleteService<Id>
{
    
}
