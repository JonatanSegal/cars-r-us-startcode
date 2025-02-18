package kea.sem3.jwtdemo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CarControllerTest {

    @Autowired
    CarRepository carRepository;

    //Do something here
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    static int carFordId, carSuzukiId;

    @BeforeEach
    public void setup() {
        carRepository.deleteAll();
        carFordId = carRepository.save(new Car("Ford", "Focus", 400, 10)).getId();
        carSuzukiId = carRepository.save(new Car("Suzuki", "Vitara", 500, 14)).getId();
    }

    @Test
    void getCars() {
    }

    @Test
    public void testCarById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cars/" + carFordId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(carFordId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Focus"));

    }

    @Test
    public void testAllCars() throws Exception {
        String model = "$[?(@.model == '%s')]";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                //One way of testing this
                .andExpect(MockMvcResultMatchers.jsonPath(model, "Focus").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(model, "Vitara").exists())
                //Another way
                .andExpect(MockMvcResultMatchers.content().string(containsString("Focus")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Vitara")));

    }

    /*
    @Test
    public void testAddCar() throws Exception {
        CarRequest newCar = new CarRequest("WW", "Polo", 200, 10);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cars")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(newCar)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        //Verify that it actually ended in the database
        assertEquals(3, carRepository.count());
    }

    @Test
    public void editCar() throws Exception {
        //New price and discount for the ford
        CarRequest carToEdit = new CarRequest("Ford", "Focus", 500, 20);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/cars/" + carFordId)
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(carToEdit)))
                .andExpect(status().isOk());
        Car editedCarFromDB = carRepository.findById(carFordId).orElse(null);
        assert editedCarFromDB != null;
        editedCarFromDB.setDiscount(20);
        editedCarFromDB.setPricePrDay(400);
        assertEquals(400, editedCarFromDB.getPricePrDay());
        assertEquals(20, editedCarFromDB.getDiscount());
    }

    @Test
    void deleteCar() throws Exception {
        CarRequest carToDelete = new CarRequest("Fiat","500",250,50);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cars/" + carFordId))
                .andExpect(status().isOk());
        //Verify that we only have one car in the database
        Car carToRepos = new Car(carToDelete);
        carRepository.save(carToRepos);
        carRepository.delete(carToRepos);
        assertEquals(false, carRepository.equals(carToRepos));
    }

     */
}



