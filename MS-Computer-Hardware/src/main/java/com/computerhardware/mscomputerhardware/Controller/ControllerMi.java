package com.computerhardware.mscomputerhardware.Controller;

import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateDescription;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateName;
import com.computerhardware.mscomputerhardware.ClassRequest.requestUpdateRef;
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
    @PreAuthorize("hasAuthority('SCOPE_AMI')")
    public ResponseEntity<String> addMaterielInformatique(@RequestParam String nameProduct,
                                                          @RequestParam String ref,
                                                          @RequestParam String description,
                                                          @RequestParam MultipartFile file) throws IOException {
        return serviceMi.addMaterielInformatique(nameProduct, ref,description ,file);
    }

    @Override
    @GetMapping("materiel-informatique/{ref}")
    @PreAuthorize("hasAuthority('SCOPE_Emp')")
    public Optional<computerHardware> fetchByRef(@PathVariable String ref) {
        return serviceMi.fetchByRef(ref);
    }

    @Override
    @DeleteMapping("materiel-informatique/delete/{ref}")
    @PreAuthorize("hasAuthority('SCOPE_AMI')")
    public ResponseEntity<String> deleteByRef(@PathVariable String ref){
        if (ref.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return serviceMi.deleteByRef(ref);
    }



    @Override
    @PutMapping("materiel-informatique/name/update")
    @PreAuthorize("hasAuthority('SCOPE_AMI')")

    public ResponseEntity<String> updateNameProduct(@RequestBody requestUpdateName updateName) {
        return serviceMi.updateNameProduct(updateName);
    }

    @Override
    @PutMapping("materiel-informatique/description/update")
    @PreAuthorize("hasAuthority('SCOPE_AMI')")
    public ResponseEntity<String> updateDescription(@RequestBody requestUpdateDescription updateDescription) {
        return serviceMi.updateDescription(updateDescription);
    }

    @Override
    @PutMapping("materiel-informatique/ref/update")
    @PreAuthorize("hasAuthority('SCOPE_AMI')")
    public ResponseEntity<String> UpdateRef(@RequestBody requestUpdateRef updateRef) {
        return serviceMi.UpdateRef(updateRef);
    }
    @Override
    @PutMapping("materiel-informatique/image/update")
    @PreAuthorize("hasAuthority('SCOPE_AMI')")
    public ResponseEntity<String> UpdateImage(String ref, MultipartFile file) throws IOException {
        return serviceMi.UpdateImage(ref,file);
    }

    @Override
    public Optional<Integer> getIdByRef(String ref) {
        return Optional.empty();
    }
}
