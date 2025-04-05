package br.com.dio.barbershopui.service;

import br.com.dio.barbershopui.entity.ScheduleEntity;

/**
 * Interface que define o contrato para serviços de agendamento
 * 
 * <p>Esta interface estabelece as operações básicas de CRUD para
 * a gestão de agendamentos na barbearia.</p>
 */
public interface IScheduleService {

    /**
     * Persiste um novo agendamento no sistema
     * @param entity Entidade contendo os dados do agendamento
     * @return A entidade salva com identificador gerado
     */
    ScheduleEntity save(final ScheduleEntity entity);

    /**
     * Remove um agendamento existente
     * @param id Identificador único do agendamento a ser removido
     */
    void delete(final long id);

}