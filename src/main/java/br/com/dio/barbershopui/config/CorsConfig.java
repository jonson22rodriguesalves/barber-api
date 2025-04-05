package br.com.dio.barbershopui.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * Classe de configuração CORS para permitir requisições entre origens diferentes.
 * Configuração global que se aplica a todos os endpoints da aplicação.
 */
@Configuration
public class CorsConfig {

    /**
     * Configura e registra o filtro CORS com as permissões mais abrangentes.
     * ATENÇÃO: Esta configuração permite qualquer origem/método/header (não recomendado para produção).
     * 
     * @return FilterRegistrationBean<CorsFilter> bean configurado
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        // Configuração CORS
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);  // Permite cookies/envio de credenciais
        config.setAllowedOriginPatterns(Collections.singletonList("*"));  // Permite qualquer origem (ajustar para produção)
        config.setAllowedMethods(Collections.singletonList("*"));  // Permite todos os métodos HTTP (GET, POST, etc)
        config.setAllowedHeaders(Collections.singletonList("*"));  // Permite todos os headers

        // Aplica a configuração para todos os endpoints
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // Registra o filtro com a maior precedência
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CorsFilter(source));
        bean.setOrder(HIGHEST_PRECEDENCE);  // Garante que seja o primeiro filtro executado

        return bean;
    }
}