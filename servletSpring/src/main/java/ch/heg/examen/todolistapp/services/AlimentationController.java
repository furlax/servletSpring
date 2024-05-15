package ch.heg.examen.todolistapp.services;


import ch.heg.examen.todolistapp.entities.Account;
import ch.heg.examen.todolistapp.entities.PersistenceHelper;
import ch.heg.examen.todolistapp.entities.Person;
import ch.heg.examen.todolistapp.entities.Task;
import ch.heg.examen.todolistapp.jpa.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alimentation")
public class AlimentationController {

    @Autowired
    PersonServices ps;

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person p) {
        try{
            ps.insert(p);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/person/account/{email}")
    public ResponseEntity createAccountForPerson(@RequestBody Account account, @PathVariable String email) {
        try{
            ps.insertAccount(account, email);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/person/account/{username}/task")
    public ResponseEntity createTaskForAccount(@RequestBody Task task, @PathVariable String username) {
        try{
            ps.createTask(task, username);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/person/account/lock/{username}")
    public ResponseEntity lockAccount(@PathVariable String username) {
        try{
            ps.lockAccount(username);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
