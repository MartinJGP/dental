package com.example.service.Impl;

import com.example.models.Pagos;
import com.example.repositories.PagosRepository;
import com.example.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagosServiceImpl implements PagosService {
    private final PagosRepository pagosRepository;
    public PagosServiceImpl(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }


    @Override
    public Pagos get(Long id) {
        Optional<Pagos> pagos = pagosRepository.findByCodigo(id);
        return pagos.orElse(null);
    }

    @Override
    public Pagos create(Pagos pagos) {
        return pagosRepository.save(pagos);
    }

    @Override
    public Pagos getByCita(Long id) {
        return pagosRepository.findByCita_Id(id);
    }
}
