package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ArquivoRepository;
import com.example.demo.repository.entity.Arquivo;

@Service
public class ArquivoService {

    private ArquivoRepository arquivoRepository;

    public ArquivoService(ArquivoRepository arquivoRepository){
        this.arquivoRepository = arquivoRepository;
    }

    public void salvarArquivo(String caminho, String nome) {
        Arquivo arquivo = new Arquivo(caminho, nome);
        this.arquivoRepository.save(arquivo);
    }
}
