package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.services.MqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que permite al administrador enviar comandos domóticos
 * a través de MQTT mediante una petición HTTP.
 *
 * <p>Está restringido a usuarios autenticados con rol de administrador.</p>
 */
@RestController
@RequestMapping("/api/domotica")
public class DomoticaController {

    private final MqttService mqttService;

    /**
     * Inyección del servicio encargado del manejo de MQTT.
     *
     * @param mqttService Servicio MQTT
     */
    @Autowired
    public DomoticaController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    /**
     * Endpoint para enviar comandos domóticos al broker MQTT.
     *
     * @param comando Comando en texto plano enviado desde el cuerpo de la petición
     * @return Respuesta HTTP indicando éxito o error
     */

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarComando(@RequestBody String comando) {
        try {
            mqttService.enviarComando(comando);
            return ResponseEntity.ok("Comando enviado: " + comando);
        } catch (MqttException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar comando: " + e.getMessage());
        }
    }
}

