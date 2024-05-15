package ch.heg.examen.todolistapp.services;


import ch.heg.examen.todolistapp.jpa.PersonServices;
import ch.heg.examen.todolistapp.types.AccountStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restitution")
public class RestitutionController {

    @Autowired
    PersonServices ps;


    @GetMapping("/person/{email}")
    public ResponseEntity getPerson(@PathVariable String email) {
        try{
            ps.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(ps.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/person/all")
    public ResponseEntity getListPerson(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ps.findAllPerson());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }


    @GetMapping("/person/account/{type}")
    public ResponseEntity getListAccountType(@PathVariable AccountStatusType type){
        try{
            String typeString = type.toString();
            return ResponseEntity.status(HttpStatus.OK).body(ps.findByStatus(typeString));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
