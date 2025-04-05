package br.com.dio.barbershopui.repository;

import br.com.dio.barbershopui.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações de persistência de clientes
 * 
 * <p>Estende JpaRepository para operações CRUD básicas e define
 * consultas customizadas para validação de dados únicos.</p>
 */
@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    /**
     * Verifica se um email já está cadastrado no sistema
     * @param email Endereço de email a ser verificado
     * @return true se o email já estiver em uso
     */
    boolean existsByEmail(final String email);

    /**
     * Verifica se um telefone já está cadastrado no sistema
     * @param phone Número de telefone a ser verificado
     * @return true se o telefone já estiver em uso
     */
    boolean existsByPhone(final String phone);

    /**
     * Busca um cliente pelo email (caso exista)
     * @param email Endereço de email exato
     * @return Optional contendo o cliente encontrado ou vazio
     */
    Optional<ClientEntity> findByEmail(final String email);

    /**
     * Busca um cliente pelo telefone (caso exista)
     * @param phone Número de telefone exato
     * @return Optional contendo o cliente encontrado ou vazio
     */
    Optional<ClientEntity> findByPhone(final String phone);
}