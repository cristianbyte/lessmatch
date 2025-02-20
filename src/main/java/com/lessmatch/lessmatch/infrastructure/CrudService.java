package com.lessmatch.lessmatch.infrastructure;

import com.lessmatch.lessmatch.infrastructure.generic.CreateService;
import com.lessmatch.lessmatch.infrastructure.generic.DeleteService;
import com.lessmatch.lessmatch.infrastructure.generic.ReadService;
import com.lessmatch.lessmatch.infrastructure.generic.UpdateService;

public interface CrudService<Request, Response, Id> extends
    CreateService<Request, Response>,
    ReadService<Response, Id>,
    UpdateService<Request, Response, Id>,
    DeleteService<Id>
{
    
}
