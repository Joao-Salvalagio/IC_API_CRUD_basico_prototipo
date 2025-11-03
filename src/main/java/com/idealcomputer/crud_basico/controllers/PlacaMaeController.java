package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.PlacaMaeModel;
import com.idealcomputer.crud_basico.services.PlacaMaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/placas-mae")
public class PlacaMaeController extends BaseCrudController<PlacaMaeModel, Long, PlacaMaeService> {

    @Autowired
    public PlacaMaeController(PlacaMaeService service) {
        super(service);
    }
}