package app.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;
    private String company;
    private int arrival;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate estdepart;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate departure;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "typeid")
    private Type type;
    
    public Flight() {
    }

    public Flight(String number, String company, int arrival, LocalDate estdepart, LocalDate departure, Type type) {
        super();
        this.number = number;
        this.company = company;
        this.arrival = arrival;
        this.estdepart = estdepart;
        this.departure = departure;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public LocalDate getEstdepart() {
        return estdepart;
    }

    public void setEstdepart(LocalDate estdepart) {
        this.estdepart = estdepart;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }
}