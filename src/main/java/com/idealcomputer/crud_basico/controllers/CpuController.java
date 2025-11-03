package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.CpuModel;
import com.idealcomputer.crud_basico.services.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cpus")
public class CpuController extends BaseCrudController<CpuModel, Long, CpuService> {

    @Autowired
    public CpuController(CpuService service) {
        super(service);
    }
}