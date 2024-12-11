package com.example.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class WebHookController {
    @Value("${mercadopago.clientId}")
    private String clientId;

    @Value("${mercadopago.clientSecret}")
    private String clientSecret;

//    @PostMapping("/notificacion")
//    public ResponseEntity<String> recibirWebhook(@RequestBody String payload) {
//        try {
//            JsonObject pay = new Gson().fromJson(payload, JsonObject.class);
//            if (pay.has("type") && pay.get("type").getAsString().equals("payment")) {
//                JsonObject data = pay.getAsJsonObject("data");
//                if (data.has("id")) {
//                    String paymentId = data.get("id").getAsString();
//                    Payment payment = Payment.findById(paymentId);
//                    System.out.println("ID del Pago: " + payment.getId());
//                    System.out.println("Status del Pago: " + payment.getStatus());
//                    System.out.println("Monto del Pago: " + payment.getTransactionAmount());
//                    System.out.println("Fecha de pago: " + payment.getDateApproved());
//                    System.out.println("Descripcion del Pago: " + payment.getDescription());
//                }
//
//            }
//            return ResponseEntity.status(HttpStatus.OK).body("OK");
//        } catch (Exception e) {
//            // Maneja posibles excepciones, como un formato JSON incorrecto
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar el webhook.");
//        }
//    }
//
//    @GetMapping("/getpago/{id}")
//    public ResponseEntity<Map<String, String>> devolverdetallePago(@PathVariable("id") String id) throws MPException {
//        MercadoPago.SDK.setAccessToken(clientSecret);
//        Payment payment =Payment.findById(id);
//        //hashmap
//        Map<String, String> map = new HashMap<>();
//        map.put("id", payment.getId());
//        map.put("status", payment.getStatus().toString());
//        map.put("monto", payment.getTransactionAmount().toString());
//        map.put("fecha", payment.getDateApproved().toString());
//        map.put("descripcion", payment.getDescription());
//        return ResponseEntity.ok(map);
//    }

    @PostMapping("/notificacion")
    public ResponseEntity<String> recibirWebhook(@RequestBody String payload) {
        try {
            JsonObject pay = new Gson().fromJson(payload, JsonObject.class);
            if (pay.has("type") && pay.get("type").getAsString().equals("payment")) {
                JsonObject data = pay.getAsJsonObject("data");
                if (data.has("id")) {
                    String paymentId = data.get("id").getAsString();
                    Payment payment = Payment.findById(paymentId);
                    System.out.println("ID del Pago: " + payment.getId());
                    System.out.println("Status del Pago: " + payment.getStatus());
                    System.out.println("Monto del Pago: " + payment.getTransactionAmount());
                    System.out.println("Fecha de pago: " + payment.getDateApproved());
                    System.out.println("Descripcion del Pago: " + payment.getDescription());
                }

            }
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (Exception e) {
            // Maneja posibles excepciones, como un formato JSON incorrecto
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar el webhook.");
        }
    }

    @GetMapping("/getpago/{id}")
    public ResponseEntity<Map<String, String>> devolverdetallePago(@PathVariable("id") String id) throws MPException {
        MercadoPago.SDK.setAccessToken(clientSecret);
        Payment payment =Payment.findById(id);
        //hashmap
        Map<String, String> map = new HashMap<>();
        map.put("id", payment.getId());
        map.put("status", payment.getStatus().toString());
        map.put("monto", payment.getTransactionAmount().toString());
        map.put("fecha", payment.getDateApproved().toString());
        map.put("descripcion", payment.getDescription());
        return ResponseEntity.ok(map);
    }
}