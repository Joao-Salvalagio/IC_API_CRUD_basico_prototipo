package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.ArmazenamentoModel;
import com.idealcomputer.crud_basico.services.ArmazenamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. A classe agora "estende" (herda de) nossa BaseCrudController.
// 2. Nós especificamos os tipos genéricos:
//    <T> -> ArmazenamentoModel (que deve implementar BaseEntity<Long>)
//    <ID> -> Long
//    <S> -> ArmazenamentoService
@RestController
@RequestMapping(value = "/api/armazenamentos")
public class ArmazenamentoController extends BaseCrudController<ArmazenamentoModel, Long, ArmazenamentoService> {

    /*
     * 3. Este é o construtor. Graças à Injeção de Dependência por Construtor,
     * o Spring automaticamente nos fornecerá o "ArmazenamentoService" aqui.
     */
    @Autowired
    public ArmazenamentoController(ArmazenamentoService service) {
        /*
         * 4. Aqui nós chamamos o construtor da classe "Pai" (BaseCrudController),
         * passando o serviço que recebemos.
         */
        super(service);
    }

    /*
     * 5. E É SÓ ISSO!
     * Todos os 5 endpoints (findAll, findById, create, update, delete)
     * são herdados automaticamente da BaseCrudController.
     * A classe fica 100% limpa, contendo apenas as anotações de configuração.
     */
}