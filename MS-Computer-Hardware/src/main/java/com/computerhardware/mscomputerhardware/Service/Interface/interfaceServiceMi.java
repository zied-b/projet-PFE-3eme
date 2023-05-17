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
    public Optional<computerHardware> fetchByRef(String ref);
    public ResponseEntity<String> deleteByRef(String ref);
    public ResponseEntity<String> addMaterielInformatique(String nameProduct,String ref,String description ,MultipartFile file) throws IOException;
    public ResponseEntity<String> updateNameProduct(requestUpdateName updateName);
    public ResponseEntity<String> updateDescription(requestUpdateDescription updateDescription);
    public ResponseEntity<String>UpdateRef(requestUpdateRef updateRef);
    public  ResponseEntity<String> UpdateImage(String ref,MultipartFile file) throws IOException;
    Optional<Integer> getIdByRef(String ref);





}
