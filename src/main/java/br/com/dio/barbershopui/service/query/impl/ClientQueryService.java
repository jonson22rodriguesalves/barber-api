package br.com.dio.barbershopui.service.query.impl;

import br.com.dio.barbershopui.entity.ClientEntity;
import br.com.dio.barbershopui.exception.NotFoundException;
import br.com.dio.barbershopui.exception.PhoneInUseException;
import br.com.dio.barbershopui.repository.IClientRepository;
import br.com.dio.barbershopui.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Implementação concreta do serviço de consulta de clientes
 * 
 * <p>Responsável por operações de leitura e validação de dados de clientes</p>
 */
@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository repository; // Repositório para acesso aos dados

    /**
     * Busca um cliente pelo ID
     * @param id Identificador único do cliente
     * @return Entidade ClientEntity encontrada
     * @throws NotFoundException Se o cliente não for encontrado
     */
    @Override
    public ClientEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o cliente de id " + id)
        );
    }

    /**
     * Lista todos os clientes cadastrados
     * @return Lista de entidades ClientEntity
     */
    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    /**
     * Verifica se um telefone já está em uso (para novos clientes)
     * @param phone Número de telefone a verificar
     * @throws PhoneInUseException Se o telefone já estiver cadastrado
     */
    @Override
    public void verifyPhone(final String phone) {
        if (repository.existsByPhone(phone)) {
            var message = "O telefone " + phone + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    /**
     * Verifica se um telefone já está em uso por outro cliente (para atualização)
     * @param id ID do cliente atual (para exclusão na verificação)
     * @param phone Novo número de telefone a verificar
     * @throws PhoneInUseException Se o telefone já estiver em uso por outro cliente
     */
    @Override
    public void verifyPhone(final long id, final String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getId(), id)) {
            var message = "O telefone " + phone + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    /**
     * Verifica se um email já está em uso (para novos clientes)
     * @param email Endereço de email a verificar
     * @throws PhoneInUseException Se o email já estiver cadastrado
     */
    @Override
    public void verifyEmail(final String email) {
        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    /**
     * Verifica se um email já está em uso por outro cliente (para atualização)
     * @param id ID do cliente atual (para exclusão na verificação)
     * @param email Novo endereço de email a verificar
     * @throws PhoneInUseException Se o email já estiver em uso por outro cliente
     */
    @Override
    public void verifyEmail(final long id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getId(), id)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }
}