package br.com.dio.barbershopui.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.OffsetDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Entidade JPA que representa um agendamento na barbearia.
 * Mapeia a tabela SCHEDULES no banco de dados.
 */
@Entity
@Table(
        name = "SCHEDULES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_SCHEDULE_INTERVAL", 
                               columnNames = {"start_at", "end_at"}), // Garante que não haja sobreposição de horários
        }
)
@Getter
@Setter
@ToString
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY) // Geração automática de ID pelo banco
    private Long id;

    @Column(nullable = false, name = "start_at") // Horário de início obrigatório
    private OffsetDateTime startAt; // Usa OffsetDateTime para armazenar data+hora+fuso horário

    @Column(nullable = false, name = "end_at") // Horário de término obrigatório
    private OffsetDateTime endAt;

    @ToString.Exclude // Evita referência circular no toString()
    @ManyToOne // Relação muitos-para-um com ClientEntity
    @JoinColumn(name = "client_id") // Coluna de chave estrangeira
    private ClientEntity client = new ClientEntity(); // Inicialização padrão

    /**
     * Implementação de equals baseada em ID e horários.
     * @param o Objeto a ser comparado
     * @return true se os objetos forem iguais
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ScheduleEntity that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(startAt, that.startAt) &&
                Objects.equals(endAt, that.endAt);
    }

    /**
     * Implementação de hashCode consistente com equals().
     * @return Código hash do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, startAt, endAt);
    }
}