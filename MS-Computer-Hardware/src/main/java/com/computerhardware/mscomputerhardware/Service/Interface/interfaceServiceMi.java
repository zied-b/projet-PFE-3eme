package com.computerhardware.mscomputerhardware.Service.Interface;


import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateDescription;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateName;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateRef;
import com.computerhardware.mscomputerhardware.Entity.computerHardware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface interfaceServiceMi {

    public ResponseEntity<List<computerHardware>> fetchAll();


    public ResponseEntity<?>add(String ref,String nameProduct,String description,MultipartFile file) throws IOException;
    public ResponseEntity<?>updateInfo(Integer id,String ref,String nameProduct,String description) ;
    public ResponseEntity<?>updateImage(Integer id ,MultipartFile file) throws IOException;
    public ResponseEntity<?>delete(Integer id ) ;








}
