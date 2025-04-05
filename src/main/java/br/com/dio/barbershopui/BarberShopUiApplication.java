package br.com.dio.barbershopui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que inicia a aplicação Spring Boot para o sistema de barbearia.
 * 
 * <p>Esta classe contém o método main que é o ponto de entrada da aplicação.</p>
 * 
 * <p>A anotação @SpringBootApplication combina três anotações essenciais:
 * <ul>
 *   <li>@Configuration - Marca a classe como fonte de definições de beans</li>
 *   <li>@EnableAutoConfiguration - Habilita a configuração automática do Spring Boot</li>
 *   <li>@ComponentScan - Habilita a varredura de componentes no pacote atual e subpacotes</li>
 * </ul>
 * </p>
 */
@SpringBootApplication
public class BarberShopUiApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     * 
     * @param args Argumentos de linha de comando que podem ser passados para a aplicação
     */
    public static void main(String[] args) {
        SpringApplication.run(BarberShopUiApplication.class, args);
    }

}