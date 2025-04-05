package br.com.dio.barbershopui.mapper;

import br.com.dio.barbershopui.controller.request.SaveClientRequest;
import br.com.dio.barbershopui.controller.request.UpdateClientRequest;
import br.com.dio.barbershopui.controller.response.ClientDetailResponse;
import br.com.dio.barbershopui.controller.response.ListClientResponse;
import br.com.dio.barbershopui.controller.response.SaveClientResponse;
import br.com.dio.barbershopui.controller.response.UpdateClientResponse;
import br.com.dio.barbershopui.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

// Interface Mapper do MapStruct para conversão entre entidades e DTOs de Cliente
@Mapper(componentModel = SPRING) // Configura o mapper para integração com Spring
public interface IClientMapper {

    // Converte SaveClientRequest (DTO de criação) para ClientEntity (entidade JPA)
    @Mapping(target = "id", ignore = true) // Ignora o ID pois será gerado automaticamente
    @Mapping(target = "schedules", ignore = true) // Ignora a lista de agendamentos para evitar circularidade
    ClientEntity toEntity(final SaveClientRequest request);

    // Converte ClientEntity para SaveClientResponse (DTO de resposta pós-criação)
    SaveClientResponse toSaveResponse(final ClientEntity entity);

    // Converte UpdateClientRequest (DTO de atualização) + ID para ClientEntity
    @Mapping(target = "schedules", ignore = true) // Ignora agendamentos durante atualização
    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    // Converte ClientEntity para UpdateClientResponse (DTO de resposta pós-atualização)
    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    // Converte ClientEntity para ClientDetailResponse (DTO com detalhes completos do cliente)
    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    // Converte lista de ClientEntity para lista de ListClientResponse (DTO simplificado para listagem)
    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);
}