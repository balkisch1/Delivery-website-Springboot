package com.balkis.delivery.services;
import com.balkis.delivery.models.Teste;
import com.balkis.delivery.repository.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesteService {

    private final TesteRepository testeRepository;

    @Autowired
    public TesteService(TesteRepository testeRepository) {
        this.testeRepository = testeRepository;
    }

    public Teste saveTeste(Teste teste) {
        return testeRepository.save(teste);
    }
}
