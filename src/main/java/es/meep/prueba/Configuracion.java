/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.meep.prueba;

import es.meep.prueba.error.GestorErrores;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase que contiene el Bean que hará la llamada al EndPoint
 * @author Adolfo Urrutia 10-jul-2020 23:24:43
 */
@Configuration
public class Configuracion {

    @Bean
    public RestTemplate restTemplateConGestorDeErrores() {
         return new RestTemplateBuilder()
            .errorHandler(new GestorErrores())
            .build();
    }
}
