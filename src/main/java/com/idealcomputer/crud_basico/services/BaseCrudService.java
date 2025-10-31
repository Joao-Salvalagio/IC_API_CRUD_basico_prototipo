package com.idealcomputer.crud_basico.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
 * Esta é a nossa classe base abstrata e genérica.
 * <T> - Representa o tipo da nossa Model (ex: CpuModel)
 * <ID> - Representa o tipo do ID do nosso Model (ex: Long)
 * <R> - Representa o tipo do nosso Repository (ex: CpuRepository)
 */
public abstract class BaseCrudService<T, ID, R extends JpaRepository<T, ID>> {

    // O repositório específico (ex: CpuRepository) será armazenado aqui.
    // É 'protected' para que as classes filhas possam acessá-lo se precisarem de um método muito customizado.
    protected final R repository;

    // O nome da entidade, para mensagens de erro mais claras.
    private final String entityName;

    // O construtor que as classes filhas (CpuService, GpuService) vão chamar.
    public BaseCrudService(R repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    // --- MÉTODOS DE CRUD GENÉRICOS ---

    /**
     * Busca uma lista de todas as entidades.
     * @return Lista de entidades.
     */
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * Busca uma entidade pelo seu ID.
     * @param id O ID da entidade a ser buscada.
     * @return A entidade encontrada.
     * @throws RuntimeException se a entidade não for encontrada.
     */
    public T findById(ID id) {
        Optional<T> entity = repository.findById(id);
        return entity.orElseThrow(() -> new RuntimeException(this.entityName + " com ID " + id + " não encontrado."));
    }

    /**
     * Salva ou atualiza uma entidade.
     * A anotação @Transactional deve ser colocada na implementação da classe filha.
     * @param entity A entidade a ser salva.
     * @return A entidade salva.
     */
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Deleta uma entidade pelo seu ID.
     * A anotação @Transactional deve ser colocada na implementação da classe filha.
     * @param id O ID da entidade a ser deletada.
     */
    public void deleteById(ID id) {
        findById(id); // Reutiliza a verificação de existência
        repository.deleteById(id);
    }
}