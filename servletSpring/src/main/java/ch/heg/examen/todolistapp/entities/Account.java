package ch.heg.examen.todolistapp.entities;

import ch.heg.examen.todolistapp.types.AccountStatusType;
import ch.heg.examen.todolistapp.types.TaskStatusType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatusType status;

    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL, fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private List<Task> tasks;

    public Account() {
    }

    public Account(String username) {
        this.username = username;
        // status par defaut ACTIVE
        status = AccountStatusType.ACTIVE;
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountStatusType getStatus() {
        return status;
    }

    public void setStatus(AccountStatusType status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", tasks=" + tasks +
                '}';
    }
}
