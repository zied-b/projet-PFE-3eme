package com.computerhardware.mscomputerhardware.Service;

import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateDescription;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateName;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateRef;
import com.computerhardware.mscomputerhardware.Entity.computerHardware;
import com.computerhardware.mscomputerhardware.Repo.RepoMI;
import com.computerhardware.mscomputerhardware.Service.Interface.interfaceServiceMi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ServiceMi implements interfaceServiceMi {

    private final RepoMI repoMI;

    @Override
    public ResponseEntity<List<computerHardware>> fetchAll() {
        if (repoMI.findAll().isEmpty()){
            return new ResponseEntity<>(null,
                    HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(repoMI.findAll(),HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> add(String ref, String nameProduct, String description, MultipartFile file) throws IOException {
        Optional<Integer> id=repoMI.getIdByRef(ref);
        if (id.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        computerHardware hardware =new computerHardware();
        hardware.setRef(ref);
        hardware.setDescription(description);
        hardware.setNameProduct(nameProduct);
        hardware.setImage(file.getBytes());
        repoMI.save(hardware);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateInfo(Integer id, String ref, String nameProduct, String description) {
        computerHardware hardware = repoMI.findById(id).get();
        if (hardware.getRef().equals(ref)){
            hardware.setDescription(description);

            hardware.setNameProduct(nameProduct);
            repoMI.save(hardware);
            return ResponseEntity.status(HttpStatus.OK).body(repoMI.findById(id).get());
        }
        Optional<Integer> IdRef= repoMI.getIdByRef(ref);
        if (IdRef.isPresent()){
            ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        hardware.setNameProduct(nameProduct);
        hardware.setDescription(description);
        hardware.setRef(ref);
        repoMI.save(hardware);
        return ResponseEntity.status(HttpStatus.OK).body(repoMI.findById(id).get());
    }

    @Override
    public ResponseEntity<?> updateImage(Integer id, MultipartFile file) throws IOException {
        computerHardware hardware =repoMI.findById(id).get();
        hardware.setImage(file.getBytes());
        repoMI.save(hardware);
        return ResponseEntity.ok().body(repoMI.findById(id).get());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        computerHardware hardware =repoMI.findById(id).get();
        repoMI.delete(hardware);
        return ResponseEntity.ok().build();
    }


}
