package br.com.dio.barbershopui.controller;

import br.com.dio.barbershopui.controller.request.*;
import br.com.dio.barbershopui.controller.response.*;
import br.com.dio.barbershopui.mapper.IClientMapper;
import br.com.dio.barbershopui.service.IClientService;
import br.com.dio.barbershopui.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * Controller REST para operações de clientes.
 * Mapeado para o endpoint "/clients" seguindo boas práticas RESTful.
 */
@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;      // Serviço para operações de escrita
    private final IClientQueryService queryService; // Serviço para operações de leitura
    private final IClientMapper mapper;        // Conversor DTO/Entidade

    /**
     * Cria um novo cliente.
     * POST /clients
     * @param request DTO com dados do cliente (validado)
     * @return DTO de resposta com cliente criado (HTTP 201)
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public SaveClientResponse save(@RequestBody @Valid SaveClientRequest request) {
        var entity = mapper.toEntity(request);  // Converte DTO para entidade
        service.save(entity);                   // Persiste no banco
        return mapper.toSaveResponse(entity);   // Retorna DTO de resposta
    }

    /**
     * Atualiza um cliente existente.
     * PUT /clients/{id}
     * @param id ID do cliente a ser atualizado
     * @param request DTO com dados atualizados
     * @return DTO com cliente atualizado
     */
    @PutMapping("{id}")
    public UpdateClientResponse update(
            @PathVariable long id,
            @RequestBody @Valid UpdateClientRequest request) {
        var entity = mapper.toEntity(id, request); // Converte DTO + ID para entidade
        service.update(entity);                   // Atualiza no banco
        return mapper.toUpdateResponse(entity);   // Retorna DTO atualizado
    }

    /**
     * Remove um cliente.
     * DELETE /clients/{id}
     * @param id ID do cliente a ser removido
     */
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);  // Deleção física ou lógica
    }

    /**
     * Busca um cliente por ID.
     * GET /clients/{id}
     * @param id ID do cliente
     * @return DTO com detalhes completos do cliente
     */
    @GetMapping("{id}")
    public ClientDetailResponse findById(@PathVariable long id) {
        var entity = queryService.findById(id);      // Busca no banco
        return mapper.toDetailResponse(entity);      // Converte para DTO detalhado
    }

    /**
     * Lista todos os clientes.
     * GET /clients
     * @return Lista de DTOs simplificados
     */
    @GetMapping
    public List<ListClientResponse> list() {
        var entities = queryService.list();         // Busca todos no banco
        return mapper.toListResponse(entities);     // Converte para lista de DTOs
    }
}