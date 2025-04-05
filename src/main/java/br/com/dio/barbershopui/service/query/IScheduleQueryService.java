package br.com.dio.barbershopui.service.query;

import br.com.dio.barbershopui.entity.ScheduleEntity;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Interface para serviços de consulta de agendamentos
 * 
 * <p>Especializada em operações de leitura e verificação
 * relacionadas a agendamentos na barbearia.</p>
 */
public interface IScheduleQueryService {

    /**
     * Busca um agendamento pelo seu identificador único
     * @param id Identificador do agendamento
     * @return Entidade ScheduleEntity correspondente
     * @throws jakarta.persistence.EntityNotFoundException se não encontrado
     */
    ScheduleEntity findById(final long id);

    /**
     * Lista agendamentos dentro de um período mensal
     * @param startAt Data/hora de início do período (inclusivo)
     * @param endAt Data/hora de fim do período (inclusivo)
     * @return Lista de agendamentos no período especificado
     */
    List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);

    /**
     * Verifica a existência de agendamentos em um horário específico
     * @param startAt Data/hora de início a verificar
     * @param endAt Data/hora de fim a verificar
     * @throws br.com.dio.barbershopui.exception.ScheduleConflictException se já existir agendamento
     */
    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);

}