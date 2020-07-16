/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.meep.prueba.error;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Clase encargada de recibir los errores, básicos, que puedan surgir al momento de hacer las llamadas al EndPoint.
 * @author Adolfo Urrutia
 * 12-jul-2020 11:16:01
 */
public class GestorErrores implements ResponseErrorHandler{

    private final Logger logger = LogManager.getLogger(GestorErrores.class);
    
    @Override
    public boolean hasError(ClientHttpResponse chr) throws IOException {
        return new DefaultResponseErrorHandler().hasError(chr);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
           
            // Errores 500's
            logger.error(response.getRawStatusCode());

            logger.error(response.getStatusCode());

        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // Errores 400's
            logger.error(response.getRawStatusCode());

            logger.error(response.getStatusCode());
            
            // Cuerpo del mensaje
            logger.error(response.getBody());

            // Encabezados
            HttpHeaders headers = response.getHeaders();
            logger.error(headers.get("Content-Type"));
            logger.error(headers.get("Server"));
        }
    }
    
}
