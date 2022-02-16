package kea.sem3.jwtdemo.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate reservationDate;

    private LocalDate rentalDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedBy;

    public Reservation(LocalDate date, Car reservedCar, Member reservedBy){
        this.rentalDate = date;
        this.reservedCar = reservedCar;
        this.reservedBy = reservedBy;
        reservedCar.addReservation(this);
        reservedCar.addReservation(this);
    }

    public Reservation() {
    }
}
