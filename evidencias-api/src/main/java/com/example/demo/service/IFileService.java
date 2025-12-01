package com.example.demo.service;

import java.io.IOException;

public interface IFileService {
    public void upload(String identificador, String tipo, byte[] conteudo) throws IOException ;
}
