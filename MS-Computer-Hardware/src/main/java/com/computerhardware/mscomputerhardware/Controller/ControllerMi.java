package com.computerhardware.mscomputerhardware.Controller;

import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateDescription;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateName;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateRef;
import com.computerhardware.mscomputerhardware.ClassRequest.updateComputerHardwar;
import com.computerhardware.mscomputerhardware.Controller.Interface.InterfaceControllerMI;
import com.computerhardware.mscomputerhardware.Entity.computerHardware;
import com.computerhardware.mscomputerhardware.Service.Interface.interfaceServiceMi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ControllerMi implements InterfaceControllerMI {
    private final interfaceServiceMi serviceMi;

    @Override
    @GetMapping("/materiel-informatique")
    @PreAuthorize("hasAuthority('SCOPE_Emp')")
    public ResponseEntity<List<computerHardware>> fetchAll() {
        return serviceMi.fetchAll();
    }

    @Override
    @PostMapping("/materiel-informatique")
    @PreAuthorize("hasAuthority('SCOPE_Emp')")
    public ResponseEntity<?> add(@RequestParam String ref,
                                 @RequestParam String nameProduct,
                                 @RequestParam String description,
                                 @RequestParam MultipartFile file) throws IOException {
        return serviceMi.add(ref,nameProduct,description,file);
    }

    @Override
    public ResponseEntity<?> updateInfo(Integer id, String ref, String nameProduct, String description) {
        return null;
    }

    @PostMapping("/materiel-informatique/update/info")
    @PreAuthorize("hasAuthority('SCOPE_Emp')")
    public ResponseEntity<?> updateInfo(@RequestBody updateComputerHardwar computerHardwar) {
        return serviceMi.updateInfo(computerHardwar.getId(),
                computerHardwar.getRef(),
                computerHardwar.getNameProduct(),
                computerHardwar.getDescription());
    }

    @Override
    @PutMapping("/materiel-informatique/update/image")
    @PreAuthorize("hasAuthority('SCOPE_Emp')")
    public ResponseEntity<?> updateImage(@RequestParam Integer id,@RequestParam MultipartFile file) throws IOException {
        return serviceMi.updateImage(id,file);
    }

    @Override
    @DeleteMapping("/materiel-informatique/delete")
    @PreAuthorize("hasAuthority('SCOPE_Emp')")
    public ResponseEntity<?> delete(@RequestParam Integer id) {
        return serviceMi.delete(id);
    }


}
