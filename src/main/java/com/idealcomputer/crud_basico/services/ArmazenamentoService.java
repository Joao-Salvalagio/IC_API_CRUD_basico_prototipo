package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.ArmazenamentoModel;
import com.idealcomputer.crud_basico.repositories.ArmazenamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 1. A classe agora "estende" (herda de) nossa BaseCrudService.
// 2. Nós especificamos os tipos genéricos:
//    <T> -> ArmazenamentoModel
//    <ID> -> Long
//    <R> -> ArmazenamentoRepository
@Service
public class ArmazenamentoService extends BaseCrudService<ArmazenamentoModel, Long, ArmazenamentoRepository> {

    /*
     * 3. Este é o construtor. Graças à Injeção de Dependência por Construtor,
     * o Spring automaticamente nos fornecerá o "ArmazenamentoRepository" aqui.
     */
    @Autowired
    public ArmazenamentoService(ArmazenamentoRepository repository) {
        /*
         * 4. Aqui nós chamamos o construtor da classe "Pai" (BaseCrudService),
         * passando o repositório que recebemos e o nome da entidade.
         * Isso é o que "liga" o serviço genérico ao específico.
         */
        super(repository, "Armazenamento");
    }

    /*
     * 5. NÃO precisamos mais dos métodos findById() e findAll().
     * Eles já existem na classe BaseCrudService e são herdados!
     */

    /*
     * 6. Nós apenas "sobrescrevemos" (Override) os métodos que precisam de
     * uma anotação especial, como @Transactional, para modificar o banco.
     */
    @Override
    @Transactional
    public ArmazenamentoModel save(ArmazenamentoModel entity) {
        // 7. O "super.save(entity)" chama a lógica de "save" que já está
        //    escrita na nossa classe BaseCrudService.
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // 8. O "super.deleteById(id)" chama a lógica de "delete" (que inclui
        //    a verificação se o item existe) que está na classe Pai.
        super.deleteById(id);
    }
}