package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.RefrigeracaoModel;
import com.idealcomputer.crud_basico.services.RefrigeracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/refrigeracoes")
public class RefrigeracaoController extends BaseCrudController<RefrigeracaoModel, Long, RefrigeracaoService> {

    @Autowired
    public RefrigeracaoController(RefrigeracaoService service) {
        super(service);
    }
}