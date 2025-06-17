package com.proyecto.laJaqueriaAPI.services;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar la conexión MQTT
 * y el envío de comandos hacia dispositivos domóticos.
 *
 * <p>Este servicio simula el control domótico mediante el envío de
 * mensajes a un broker MQTT público.</p>
 */
@Service
public class MqttService {

    private static final String BROKER = "tcp://broker.hivemq.com:1883";
    private static final String CLIENT_ID = "jaqueria-admin";
    private static final String TOPIC = "jaqueria/domotica/control";

    private MqttClient client;

    /**
     * Constructor que establece la conexión con el broker MQTT.
     * Si no hay conexión al broker, el servicio sigue funcionando sin domótica.
     */
    public MqttService() {
        try {
            this.client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);
            System.out.println("[MQTT] Conectado exitosamente al broker.");
        } catch (MqttException e) {
            System.err.println("[MQTT] No se pudo conectar al broker: " + e.getMessage());
            this.client = null;
        }
    }

    /**
     * Envía un comando MQTT al tópico configurado para simular control domótico.
     *
     * @param comando Texto del comando a enviar (ej. "LUZ_ON", "PUERTA_ABRIR")
     * @throws MqttException si ocurre un error durante el envío del mensaje
     */
    public void enviarComando(String comando) throws MqttException {
        if (client != null && client.isConnected()) {
            MqttMessage message = new MqttMessage(comando.getBytes());
            message.setQos(1);
            client.publish(TOPIC, message);
        } else {
            System.err.println("[MQTT] Broker no disponible. Comando no enviado: " + comando);
        }
    }
}