package com.example.service;

import com.example.models.Pagos;

public interface PagosService {
    //get by id
    Pagos get(Long id);
    //create
    Pagos create(Pagos pagos);
    //get by cita
    Pagos getByCita(Long id);
}
