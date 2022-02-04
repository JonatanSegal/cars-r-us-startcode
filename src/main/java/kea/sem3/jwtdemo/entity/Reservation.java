package kea.sem3.jwtdemo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @CreatedDate
    private Date reservationDate;
    @Column(nullable = false)
    private Date rentalDate;

    public Reservation() {
    }
}
