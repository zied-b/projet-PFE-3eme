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
    public Optional<Integer> getIdByRef(String ref) {
        return repoMI.getIdByRef(ref) ;
    }



    @Override
    public Optional<computerHardware> fetchByRef(String ref) {
        if (getIdByRef(ref).isPresent()){
            return repoMI.findById(getIdByRef(ref).get());
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity<String> deleteByRef(String ref) {
        if(!ref.isEmpty()) {
            Optional<computerHardware> informatiqueOptional = fetchByRef(ref);
            if (informatiqueOptional.isPresent()) {
                computerHardware computerHardware = informatiqueOptional.get();
                repoMI.delete(computerHardware);
                return new ResponseEntity<>("Delete Bien'" + computerHardware.getNameProduct() + "'\n" +
                        " by ref :" + computerHardware.getRef() + "' successfully"
                        , HttpStatus.OK);
            }
                return new ResponseEntity<>("The ref  '" + ref + "' does not exist"
                        , HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<String> addMaterielInformatique(String nameProduct,String ref,String description ,MultipartFile file) throws IOException {
        Optional<computerHardware> optionalMaterielInformatique = fetchByRef(ref);

        if (optionalMaterielInformatique.isPresent()){
            return new ResponseEntity<>("This ref '"+ref+"' exists"
                    , HttpStatus.CONFLICT);
        }else {

            computerHardware hardware=new computerHardware();
            hardware.setRef(ref);
            hardware.setNameProduct(nameProduct);
            hardware.setDescription(description);
            hardware.setImage(file.getBytes());

            repoMI.save(hardware);
            return new ResponseEntity<>("This MI '"+nameProduct+"' has been created successfully"
                    ,HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<String> updateNameProduct(requestUpdateName updateName) {
        Optional<computerHardware> materielInformatiqueOptional=fetchByRef(updateName.getRef());
        if (materielInformatiqueOptional.isPresent()){
            computerHardware computerHardware =materielInformatiqueOptional.get();
            computerHardware.setNameProduct(updateName.getName());
            repoMI.save(computerHardware);
            return new ResponseEntity<>("update name product successfully !",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("The ref  '"+updateName.getRef()+"' does not exist",
                    HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<String> updateDescription(requestUpdateDescription updateDescription) {
        Optional<computerHardware> materielInformatiqueOptional=fetchByRef(updateDescription.getRef());
        if (materielInformatiqueOptional.isPresent()){
            computerHardware computerHardware =materielInformatiqueOptional.get();
            computerHardware.setDescription(updateDescription.getDescription());
            repoMI.save(computerHardware);
            return new ResponseEntity<>("update description successfully !",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("The ref  '"+updateDescription.getRef()+"' does not exist",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> UpdateRef(requestUpdateRef updateRef) {
        Optional<computerHardware> optionalMaterielInformatiqueNew=fetchByRef(updateRef.getNewRef());
        Optional<computerHardware> optionalMaterielInformatique =fetchByRef(updateRef.getCurrRef());
        if (optionalMaterielInformatiqueNew.isEmpty()){
            if (optionalMaterielInformatique.isPresent()){
                computerHardware computerHardware =optionalMaterielInformatique.get();
                computerHardware.setRef(updateRef.getNewRef());
                repoMI.save(computerHardware);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Update Ref '"+updateRef.getCurrRef()+"' to '"+updateRef.getNewRef()+"' successfully !");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The ref  '"+updateRef.getCurrRef()+"' does not exist");
            }

        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This reference is already in use by "+optionalMaterielInformatiqueNew.get().toString());
        }

    }

    @Override
    public ResponseEntity<String> UpdateImage(String ref,MultipartFile file) throws IOException {
        Optional<computerHardware>optionalComputerHardware=fetchByRef(ref);
        if(optionalComputerHardware.isPresent()){
            optionalComputerHardware.get().setImage(file.getBytes());
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }




}
