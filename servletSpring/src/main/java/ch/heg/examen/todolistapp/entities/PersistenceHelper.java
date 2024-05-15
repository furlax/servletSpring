package ch.heg.examen.todolistapp.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PersistenceHelper {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("todoAppPU");
    }

    @Bean
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void close() {
        emf.close();
    }
}
