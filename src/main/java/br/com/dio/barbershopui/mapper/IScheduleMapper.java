package br.com.dio.barbershopui.mapper;

import br.com.dio.barbershopui.controller.request.SaveScheduleRequest;
import br.com.dio.barbershopui.controller.response.ClientScheduleAppointmentResponse;
import br.com.dio.barbershopui.controller.response.SaveScheduleResponse;
import br.com.dio.barbershopui.controller.response.ScheduleAppointmentMonthResponse;
import br.com.dio.barbershopui.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

// Interface Mapper do MapStruct para conversão entre entidades e DTOs de agendamento
@Mapper(componentModel = SPRING) // Define o modelo de injeção de dependência como Spring
public interface IScheduleMapper {

    // Converte SaveScheduleRequest (DTO de entrada) para ScheduleEntity (entidade JPA)
    @Mapping(target = "id", ignore = true) // Ignora o ID pois será gerado automaticamente
    @Mapping(target = "client.id", source = "clientId") // Mapeia clientId para client.id
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    // Converte ScheduleEntity (entidade) para SaveScheduleResponse (DTO de saída)
    @Mapping(target = "clientId", source = "client.id") // Mapeia client.id para clientId
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    // Converte lista de ScheduleEntity para ScheduleAppointmentMonthResponse (relatório mensal)
    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    // Usa o método toClientMonthResponse abaixo para converter a lista de entidades
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleEntity> entities);

    // Converte lista de ScheduleEntity para lista de ClientScheduleAppointmentResponse
    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    // Converte ScheduleEntity para ClientScheduleAppointmentResponse (DTO detalhado)
    @Mapping(target = "clientId", source = "client.id") // Mapeia client.id para clientId
    @Mapping(target = "clientName", source = "client.name") // Mapeia client.name para clientName
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())") 
    // Extrai o dia do mês de startAt
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);
}