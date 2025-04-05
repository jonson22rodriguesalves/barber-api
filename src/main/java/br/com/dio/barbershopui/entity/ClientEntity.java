package br.com.dio.barbershopui.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Entidade JPA que representa um cliente da barbearia.
 * Mapeia a tabela CLIENTS no banco de dados.
 */
@Entity
@Table(
    name = "CLIENTS",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "email"),  // Garante unicidade de e-mail
        @UniqueConstraint(name = "UK_PHONE", columnNames = "phone")   // Garante unicidade de telefone
    }
)
@Getter
@Setter
@ToString
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)  // ID auto-incrementado pelo banco de dados
    private Long id;

    @Column(nullable = false, length = 150)  // Nome obrigatório com limite de 150 caracteres
    private String name;

    @Column(nullable = false, length = 150)  // E-mail obrigatório com limite de 150 caracteres
    private String email;

    @Column(nullable = false, length = 11, columnDefinition = "bpchar(11)")  // Telefone obrigatório, armazenado como string fixa de 11 caracteres
    private String phone;

    @ToString.Exclude  // Evita referência circular no toString()
    @OneToMany(
        mappedBy = "client",  // Mapeado pelo campo 'client' na entidade ScheduleEntity
        cascade = ALL,  // Operações em cascata para todas as operações (PERSIST, MERGE, REMOVE, REFRESH, DETACH)
        orphanRemoval = true  // Remove automaticamente schedules sem cliente
    )
    private Set<ScheduleEntity> schedules = new HashSet<>();  // Lista de agendamentos do cliente

    /**
     * Implementação de equals() baseada nos campos id, name, email e phone.
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ClientEntity that)) return false;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(email, that.email) &&
               Objects.equals(phone, that.phone);
    }

    /**
     * Implementação de hashCode() consistente com equals().
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone);
    }
}