package br.com.dio.barbershopui.controller;

import br.com.dio.barbershopui.controller.request.SaveScheduleRequest;
import br.com.dio.barbershopui.controller.response.SaveScheduleResponse;
import br.com.dio.barbershopui.controller.response.ScheduleAppointmentMonthResponse;
import br.com.dio.barbershopui.mapper.IScheduleMapper;
import br.com.dio.barbershopui.service.IScheduleService;
import br.com.dio.barbershopui.service.query.IScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.YearMonth;

import static java.time.ZoneOffset.UTC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Controller REST para operações de agendamento na barbearia.
 * Mapeado para o endpoint "/schedules".
 */
@RestController
@RequestMapping("schedules")
@AllArgsConstructor // Injeção de dependência via construtor (Lombok)
public class ScheduleController {

    private final IScheduleService service; // Serviço para operações de escrita
    private final IScheduleQueryService queryService; // Serviço para consultas
    private final IScheduleMapper mapper; // Conversor DTO/Entidade

    /**
     * Cria um novo agendamento.
     * @param request DTO com dados do agendamento (validado automaticamente)
     * @return DTO de resposta com agendamento criado
     */
    @PostMapping
    @ResponseStatus(CREATED) // Retorna HTTP 201 Created
    public SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request) {
        var entity = mapper.toEntity(request); // Converte DTO para entidade
        service.save(entity); // Persiste no banco
        return mapper.toSaveResponse(entity); // Retorna DTO de resposta
    }

    /**
     * Remove um agendamento existente.
     * @param id ID do agendamento a ser removido
     */
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT) // Retorna HTTP 204 No Content
    public void delete(@PathVariable final long id) {
        service.delete(id); // Deleção física/lógica
    }

    /**
     * Lista agendamentos de um mês específico.
     * @param year Ano de referência
     * @param month Mês de referência (1-12)
     * @return DTO com agendamentos do período
     */
    @GetMapping("{year}/{month}")
    public ScheduleAppointmentMonthResponse listMonth(
            @PathVariable final int year,
            @PathVariable final int month) {
        
        var yearMonth = YearMonth.of(year, month);
        // Calcula intervalo do mês (primeiro dia 00:00 até último dia 23:59:59.999)
        var startAt = yearMonth.atDay(1).atTime(0, 0).atOffset(UTC);
        var endAt = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999_999_999).atOffset(UTC);
        
        var entities = queryService.findInMonth(startAt, endAt); // Busca no banco
        return mapper.toMonthResponse(year, month, entities); // Converte para DTO
    }
}