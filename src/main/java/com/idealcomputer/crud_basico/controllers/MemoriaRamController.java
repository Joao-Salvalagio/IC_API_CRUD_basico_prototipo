package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.MemoriaRamModel;
import com.idealcomputer.crud_basico.services.MemoriaRamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/memorias-ram")
public class MemoriaRamController extends BaseCrudController<MemoriaRamModel, Long, MemoriaRamService> {

    @Autowired
    public MemoriaRamController(MemoriaRamService service) {
        super(service);
    }
}