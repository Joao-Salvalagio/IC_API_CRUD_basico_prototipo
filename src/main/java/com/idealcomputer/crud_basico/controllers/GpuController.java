package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.GpuModel;
import com.idealcomputer.crud_basico.services.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/gpus")
public class GpuController extends BaseCrudController<GpuModel, Long, GpuService> {

    @Autowired
    public GpuController(GpuService service) {
        super(service);
    }
}