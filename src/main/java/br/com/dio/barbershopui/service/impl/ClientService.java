package br.com.dio.barbershopui.service.impl;

import br.com.dio.barbershopui.entity.ClientEntity;
import br.com.dio.barbershopui.repository.IClientRepository;
import br.com.dio.barbershopui.service.IClientService;
import br.com.dio.barbershopui.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Implementação concreta do serviço de gestão de clientes
 * 
 * <p>Responsável pelas operações de CRUD de clientes com validações de negócio</p>
 */
@Repository
@AllArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository repository;       // Repositório para persistência
    private final IClientQueryService queryService;  // Serviço de consultas e validações

    /**
     * Cria um novo cliente no sistema
     * @param entity Dados do cliente a ser cadastrado
     * @return Cliente persistido com ID gerado
     * @throws PhoneInUseException Se telefone já estiver em uso
     * @throws EmailInUseException Se email já estiver em uso
     */
    @Override
    public ClientEntity save(final ClientEntity entity) {
        // Valida unicidade de email e telefone
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        return repository.save(entity);
    }

    /**
     * Atualiza os dados de um cliente existente
     * @param entity Dados atualizados do cliente (deve conter ID)
     * @return Cliente atualizado
     * @throws NotFoundException Se cliente não existir
     * @throws PhoneInUseException Se novo telefone já estiver em uso
     * @throws EmailInUseException Se novo email já estiver em uso
     */
    @Override
    public ClientEntity update(final ClientEntity entity) {
        // Valida unicidade dos novos valores (excluindo o cliente atual)
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        // Busca a versão atual persistida
        var stored = queryService.findById(entity.getId());
        
        // Aplica as atualizações
        stored.setName(entity.getName());
        stored.setPhone(entity.getPhone());
        stored.setEmail(entity.getEmail());
        
        return repository.save(stored);
    }

    /**
     * Remove um cliente do sistema
     * @param id Identificador do cliente a ser removido
     * @throws NotFoundException Se cliente não existir
     */
    @Override
    public void delete(final long id) {
        // Verifica existência antes de deletar
        queryService.findById(id);
        repository.deleteById(id);
    }
}