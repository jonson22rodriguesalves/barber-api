package br.com.dio.barbershopui.service;

import br.com.dio.barbershopui.entity.ClientEntity;

/**
 * Interface que define o contrato para serviços de gestão de clientes
 * 
 * <p>Estabelece as operações básicas de CRUD para a entidade ClientEntity.</p>
 */
public interface IClientService {

    /**
     * Persiste um novo cliente no sistema
     * @param entity Entidade contendo os dados do cliente
     * @return A entidade salva com identificador gerado
     */
    ClientEntity save(final ClientEntity entity);

    /**
     * Atualiza os dados de um cliente existente
     * @param entity Entidade com os dados atualizados (deve conter o ID)
     * @return A entidade atualizada
     */
    ClientEntity update(final ClientEntity entity);

    /**
     * Remove permanentemente um cliente do sistema
     * @param id Identificador único do cliente a ser removido
     */
    void delete(final long id);

}