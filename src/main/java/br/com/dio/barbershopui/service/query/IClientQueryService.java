package br.com.dio.barbershopui.service.query;

import br.com.dio.barbershopui.entity.ClientEntity;

import java.util.List;

/**
 * Interface para serviços de consulta de clientes
 * 
 * <p>Contém operações especializadas em consultas e validações
 * relacionadas à entidade ClientEntity.</p>
 */
public interface IClientQueryService {

    /**
     * Busca um cliente pelo seu identificador único
     * @param id Identificador do cliente
     * @return Entidade ClientEntity correspondente
     * @throws jakarta.persistence.EntityNotFoundException se não encontrado
     */
    ClientEntity findById(final long id);

    /**
     * Lista todos os clientes cadastrados no sistema
     * @return Lista contendo todas as entidades de clientes
     */
    List<ClientEntity> list();

    /**
     * Verifica se um telefone já está cadastrado no sistema (para novos clientes)
     * @param phone Número de telefone a ser verificado
     * @throws br.com.dio.barbershopui.exception.DuplicatePhoneException se o telefone já existir
     */
    void verifyPhone(final String phone);

    /**
     * Verifica se um telefone já está cadastrado para outro cliente (atualização)
     * @param id Identificador do cliente atual (para exclusão na verificação)
     * @param phone Novo número de telefone a ser verificado
     * @throws br.com.dio.barbershopui.exception.DuplicatePhoneException se o telefone já existir para outro cliente
     */
    void verifyPhone(final long id, final String phone);

    /**
     * Verifica se um email já está cadastrado no sistema (para novos clientes)
     * @param email Endereço de email a ser verificado
     * @throws br.com.dio.barbershopui.exception.DuplicateEmailException se o email já existir
     */
    void verifyEmail(final String email);

    /**
     * Verifica se um email já está cadastrado para outro cliente (atualização)
     * @param id Identificador do cliente atual (para exclusão na verificação)
     * @param email Novo endereço de email a ser verificado
     * @throws br.com.dio.barbershopui.exception.DuplicateEmailException se o email já existir para outro cliente
     */
    void verifyEmail(final long id, final String email);

}