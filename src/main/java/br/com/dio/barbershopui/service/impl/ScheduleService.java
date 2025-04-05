package br.com.dio.barbershopui.service.impl;

import br.com.dio.barbershopui.entity.ScheduleEntity;
import br.com.dio.barbershopui.repository.IScheduleRepository;
import br.com.dio.barbershopui.service.IScheduleService;
import br.com.dio.barbershopui.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementação concreta do serviço de agendamentos
 * 
 * <p>Gerencia as operações de criação e remoção de agendamentos,
 * garantindo a integridade dos dados através de validações.</p>
 */
@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;        // Repositório para persistência
    private final IScheduleQueryService queryService;   // Serviço de consultas

    /**
     * Salva um novo agendamento após validar conflitos de horário
     * @param entity Entidade contendo os dados do agendamento
     * @return Agendamento persistido com ID gerado
     * @throws ScheduleInUseException Se já existir agendamento no mesmo horário
     */
    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        // Valida conflito de horários antes de salvar
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return repository.save(entity);
    }

    /**
     * Remove um agendamento existente
     * @param id Identificador do agendamento a ser removido
     * @throws NotFoundException Se o agendamento não for encontrado
     */
    @Override
    public void delete(final long id) {
        // Verifica existência antes de deletar
        queryService.findById(id);
        repository.deleteById(id);
    }
}