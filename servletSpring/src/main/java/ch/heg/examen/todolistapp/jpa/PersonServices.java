package ch.heg.examen.todolistapp.jpa;

import ch.heg.examen.todolistapp.Exception.AccountNotExistException;
import ch.heg.examen.todolistapp.Exception.PersonAlreadyExistException;
import ch.heg.examen.todolistapp.entities.Account;
import ch.heg.examen.todolistapp.entities.Person;
import ch.heg.examen.todolistapp.entities.Task;
import ch.heg.examen.todolistapp.types.AccountStatusType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PersonServices {

    static Logger logger = Logger.getLogger(PersonServices.class);

    @PersistenceContext
    private EntityManager em;


    // créé la personne
    @Transactional
    public void insert(Person p) throws PersonAlreadyExistException {
        if (findByEmail(p.getEmail()) != null) {
            logger.warn("Erreur, la Person existe déjà");
            throw new PersonAlreadyExistException("La Person : " + p.getEmail() + " existe déjà.");
        }
        em.persist(p);
    }

    // trouver la personne
    @Transactional
    public Person findByEmail(String email) {
        return em.find(Person.class, email);
    }

    // Find the account by username
    @Transactional
    public Account findByUsername(String username) throws NoResultException {
        TypedQuery<Account> query = em.createQuery("select a from Account a where a.username = :username", Account.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    // find account by status
    @Transactional
    public Account findByStatus(String status) throws NoResultException {
        AccountStatusType convertTypeStatus = AccountStatusType.valueOf(status);
        TypedQuery<Account> query = em.createQuery("select a from Account a where a.status = :status", Account.class);
        query.setParameter("status", convertTypeStatus);
        return query.getSingleResult();
    }



    @Transactional
    public List<Person> findAllPerson() {
        TypedQuery<Person> query = em.createQuery("select p from Person p ", Person.class);
        return query.getResultList();
    }

    @Transactional
    public void insertAccount(Account a, String email) throws PersonAlreadyExistException {
        Account account = new Account(a.getUsername());
        Person findP = findByEmail(email);
        if (findP == null) {
            logger.warn("Erreur, la Person n'existe pas");
            throw new PersonAlreadyExistException("La Person : " + email + " n'existe pas.");
        }
        findP.addAccount(account);
        em.merge(findP);
    }

    @Transactional
    public void createTask(Task t, String username) throws AccountNotExistException {
        Task task = new Task(t.getTitle(), t.getDescription());
        Account findA = findByUsername(username);
        if (findA == null) {
            logger.warn("Error, Account doesn't exist");
            throw new AccountNotExistException("This account : " + findA + "  doesn't exist.");
        }
        findA.addTask(task);
        em.merge(findA);
    }

    @Transactional
    public void lockAccount(String username) throws AccountNotExistException {
        Account findA = findByUsername(username);
        if (findA == null) {
            logger.warn("Error, Account doesn't exist");
            throw new AccountNotExistException("This account : " + findA + "  doesn't exist.");
        }
        findA.setStatus(AccountStatusType.BLOQUE);
        em.merge(findA);
    }
}
