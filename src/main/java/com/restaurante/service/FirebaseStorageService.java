package com.restaurante.service;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    final String BucketName = "restaurante-fc198.appspot.com"; // ID DEL PROYECTO

    final String rutaSuperiorStorage = "restaurante"; // NOMBRE DEL PROYECTO
    
    final String rutaJsonFile = "firebase"; // FIREBASE
    
    final String archivoJsonFile = "restaurante-fc198-firebase-adminsdk-4oi1g-275c6524d5.json"; // NOMBRE DE LA CARPETA FIREBASE
    
}
