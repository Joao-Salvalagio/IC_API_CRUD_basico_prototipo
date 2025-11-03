package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.GabineteModel;
import com.idealcomputer.crud_basico.services.GabineteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/gabinetes")
public class GabineteController extends BaseCrudController<GabineteModel, Long, GabineteService> {

    @Autowired
    public GabineteController(GabineteService service) {
        super(service);
    }
}