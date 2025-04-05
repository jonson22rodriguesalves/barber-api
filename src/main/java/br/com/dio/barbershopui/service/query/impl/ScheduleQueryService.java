package br.com.dio.barbershopui.service.query.impl;

import br.com.dio.barbershopui.entity.ScheduleEntity;
import br.com.dio.barbershopui.exception.NotFoundException;
import br.com.dio.barbershopui.exception.ScheduleInUseException;
import br.com.dio.barbershopui.repository.IScheduleRepository;
import br.com.dio.barbershopui.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Implementação concreta do serviço de consulta de agendamentos
 * 
 * <p>Responsável por operações de leitura e validação de agendamentos</p>
 */
@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;  // Repositório para acesso aos dados

    /**
     * Busca um agendamento por ID
     * @param id Identificador do agendamento
     * @return Entidade ScheduleEntity encontrada
     * @throws NotFoundException Se nenhum agendamento for encontrado
     */
    @Override
    public ScheduleEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento não encontrado")
        );
    }

    /**
     * Lista agendamentos dentro de um período mensal
     * @param startAt Data/hora de início do período (inclusivo)
     * @param endAt Data/hora de fim do período (inclusivo)
     * @return Lista ordenada por data/hora de início e fim
     */
    @Override
    public List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    /**
     * Verifica conflito de horários
     * @param startAt Data/hora de início a verificar
     * @param endAt Data/hora de fim a verificar
     * @throws ScheduleInUseException Se já existir agendamento no período
     */
    @Override
    public void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)){
            var message = "Já existe um cliente agendado no horário solicitado";
            throw new ScheduleInUseException(message);
        }
    }
}