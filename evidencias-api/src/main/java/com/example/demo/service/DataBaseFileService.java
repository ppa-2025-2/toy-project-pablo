package com.example.demo.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.controller.EvidencesController;

@Service("database")
public class DataBaseFileService implements IFileService {

    @Value("${imagem.upload.pasta}")
    private String uploadPasta;
    private static Logger logger = LoggerFactory.getLogger(EvidencesController.class);
    private final ArquivoService arquivoService;
    

    public DataBaseFileService(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    @Override
    public void upload(String identificador, String tipo, byte[] conteudo) throws IOException {
        logger.info("Fazendo upload de arquivo...");
        Path caminho = getCaminhoArquivo(identificador);
        persistirArquivo(caminho, conteudo, identificador);
    }

    private Path getCaminhoArquivo(String identificador) throws IOException{
        var caminho = Paths.get(uploadPasta);
        criarDiretorioCasoNaoExista(caminho);
        
        return caminho.resolve(identificador);
    }

    private void persistirArquivo(Path caminhoArquivo, byte[] conteudo, String nome) throws IOException {
        Files.write(caminhoArquivo, conteudo);
        this.arquivoService.salvarArquivo(caminhoArquivo.toString(), nome);
    }

    private void criarDiretorioCasoNaoExista(Path caminho) throws IOException{
        if (!Files.exists(caminho))
            Files.createDirectory(caminho);
    }

}
