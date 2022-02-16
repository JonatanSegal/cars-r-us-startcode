package kea.sem3.jwtdemo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime reservationDate;

    private LocalDateTime rentalDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedBy;

    public Reservation(LocalDateTime date,Car reservedCar, Member reservedBy){
        this.rentalDate = date;
        this.reservedCar = reservedCar;
        this.reservedBy = reservedBy;
    }

    public Reservation() {
    }
}
