package pcc.Model;

import jakarta.persistence.*;

@Entity
public class Bank {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        @Column
        private String name;
        @Column
        private String url;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
