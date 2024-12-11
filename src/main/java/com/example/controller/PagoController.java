package com.example.controller;

import com.example.models.Pagos;
import com.example.service.PagosService;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "**")
public class PagoController {
    private final PagosService pagosService;
    public PagoController(PagosService pagosService) {
        this.pagosService = pagosService;
    }
    String citaIdTemporal = UUID.randomUUID().toString();

    @Value("${mercadopago.clientId}")
    private String clientId;

    @Value("${mercadopago.clientSecret}")
    private String clientSecret;

    @PostMapping("/iniciar-pago")
    public String iniciarPago(@RequestParam("detalle") String detalle,@RequestParam("monto") float monto) throws MPException {
        // Configura tus credenciales de MercadoPago aquí
        MercadoPago.SDK.setAccessToken(clientSecret);

        // Crea una preferencia de pago
        Preference preference = new Preference();

        // Define un artículo (producto o servicio) en el detalle de pago
        Item item = new Item();
        item.setId(citaIdTemporal); // Un identificador único para el producto
        item.setTitle(detalle); // Nombre del producto
        item.setQuantity(1); // Cantidad del producto
        item.setUnitPrice(monto); // Precio unitario

        // Agrega el artículo a la preferencia de pago
        preference.appendItem(item);

        // Configura el pagador (puedes obtener los datos del comprador si es necesario)

        //backurls
        BackUrls backUrls = new BackUrls();
        backUrls.setSuccess("http://localhost:8080/pago-exitoso");
        backUrls.setPending("http://localhost:8080/pago-pendiente");
        backUrls.setFailure("http://localhost:8080/react/pago-fallido");
        preference.setBackUrls(backUrls);
        //cambiar con el ngrok
        preference.setNotificationUrl("https://spring-security-jwt-main.fly.dev/api/pagos/notificacion");
        // Guarda la preferencia y obtén la URL de pago
        preference.save();
        return preference.getInitPoint();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Pagos> get(@PathVariable Long id){
        return ResponseEntity.ok(pagosService.get(id));
    }
    @PostMapping("/create")
    public ResponseEntity<Pagos> create(@RequestBody Pagos pagos){
        Pagos pago = pagosService.create(pagos);
        return ResponseEntity.ok(pago);
    }
    @GetMapping("/getbycita/{id}")
    public ResponseEntity<Pagos> getByCita(@PathVariable Long id){
        return ResponseEntity.ok(pagosService.getByCita(id));
    }
}
