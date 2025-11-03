package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.FonteModel;
import com.idealcomputer.crud_basico.services.FonteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/fontes")
public class FonteController extends BaseCrudController<FonteModel, Long, FonteService> {

    @Autowired
    public FonteController(FonteService service) {
        super(service);
    }
}