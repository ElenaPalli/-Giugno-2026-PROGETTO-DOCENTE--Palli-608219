package it.uniroma3.siw.calcio.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Referee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String refereeCode;

    @OneToMany(mappedBy = "referee")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Match> refereedMatches;

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRefereeCode() {
        return refereeCode;
    }

    public void setRefereeCode(String refereeCode) {
        this.refereeCode = refereeCode;
    }

    public List<Match> getRefereedMatches() {
        return refereedMatches;
    }

    public void setRefereedMatches(List<Match> refereedMatches) {
        this.refereedMatches = refereedMatches;
    }

    // hashCode e equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((refereeCode == null) ? 0 : refereeCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Referee other = (Referee) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        if (refereeCode == null) {
            if (other.refereeCode != null)
                return false;
        } else if (!refereeCode.equals(other.refereeCode))
            return false;
        return true;
    }

}
