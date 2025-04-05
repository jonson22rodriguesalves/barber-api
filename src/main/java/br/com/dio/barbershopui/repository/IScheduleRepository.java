package br.com.dio.barbershopui.repository;

import br.com.dio.barbershopui.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Repositório para operações de persistência de agendamentos
 * 
 * <p>Estende JpaRepository para operações CRUD básicas e define
 * consultas customizadas para necessidades específicas do domínio.</p>
 */
@Repository
public interface IScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    /**
     * Busca agendamentos dentro de um intervalo de tempo
     * @param startAt Data/hora de início do intervalo (inclusivo)
     * @param endAt Data/hora de fim do intervalo (inclusivo)
     * @return Lista ordenada por data/hora de início e fim
     */
    List<ScheduleEntity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );

    /**
     * Verifica existência de agendamento em um horário específico
     * @param startAt Data/hora de início exata
     * @param endAt Data/hora de fim exata
     * @return true se existir agendamento no horário especificado
     */
    boolean existsByStartAtAndEndAt(final OffsetDateTime startAt, final OffsetDateTime endAt);

}