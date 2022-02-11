package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    static int carId1,carId2;

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository) {
        carRepository.deleteAll();
        carId1= carRepository.save(new Car("BMW","i8",15000,5000)).getId();
        carId2 = carRepository.save(new Car("Tesla","model X",7500,500)).getId();
    }
    @Test
    public void testCount(){
        assertEquals(2,carRepository.count());
    }
    @Test
    public void testFindById(){
        Car carFound = carRepository.findById(carId1).orElse(null);
        assertEquals("BMW",carFound.getBrand());
    }
}