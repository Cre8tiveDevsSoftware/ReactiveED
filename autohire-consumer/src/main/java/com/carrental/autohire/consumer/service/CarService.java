package com.carrental.autohire.consumer.service;

import com.carrental.autohire.consumer.config.DatabaseConfig;
import com.carrental.autohire.consumer.repository.CarRepository;
import com.carrental.autohire.dto.CarRequestDto;
import com.carrental.autohire.dto.CarResponseDto;
import com.carrental.autohire.consumer.entities.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarService {

    @Autowired
    private CarRepository carRepository = null;
    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public CarResponseDto addCar(CarRequestDto carRequestDto) {

        String insertQuery = "INSERT INTO car (manufacturer, model, year, color, price,is_booked) VALUES (?, ?, ?, ?,?,?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, carRequestDto.manufacturer());
            preparedStatement.setString(2, carRequestDto.model());
            preparedStatement.setInt(3, carRequestDto.year());
            preparedStatement.setString(4, carRequestDto.color());
            preparedStatement.setLong(5,carRequestDto.price());
            preparedStatement.setBoolean(6,carRequestDto.isBooked());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                Long carId = null;
                if (generatedKeys.next()) {
                    carId = generatedKeys.getLong(1);
                }

                log.info("Car added with ID: {}", carId);

                return new CarResponseDto(carId, carRequestDto.manufacturer(), carRequestDto.model(),
                        carRequestDto.year(), carRequestDto.color(),carRequestDto.price(),carRequestDto.isBooked());
            } else {
                log.error("Failed to add car to the database");
            }

        } catch (SQLException e) {
            log.error("An error occurred while adding a car to the database", e);
        }

        return null;
    }

    public List<CarResponseDto> getAllCars() {
        List<CarResponseDto> cars = new ArrayList<>();

        String selectQuery = "SELECT * FROM car";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Long carId = resultSet.getLong("id");
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                Long price = resultSet.getLong("price");
                Boolean isBooked = resultSet.getBoolean("is_booked");

                CarResponseDto car = new CarResponseDto(carId, manufacturer, model, year, color, price,isBooked);
                cars.add(car);
            }

        } catch (SQLException e) {
            log.error("An error occurred while retrieving cars from the database", e);
        }

        log.info("Retrieved {} cars", cars.size());

        cars = cars.stream().filter(carResponseDto -> carResponseDto.isBooked() == false).collect(Collectors.toList());

        return cars;
    }

    public CarResponseDto getCarById(Long carId) {
        String selectQuery = "SELECT * FROM car WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setLong(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                Long price = resultSet.getLong("price");

                CarResponseDto car = new CarResponseDto(carId, manufacturer, model, year, color,price,Boolean.FALSE);
                log.info("Retrieved car with ID {}: {}", carId, car);
                return car;
            }

        } catch (SQLException e) {
            log.error("An error occurred while retrieving the car with ID " + carId, e);
        }

        log.warn("Car with ID {} not found", carId);
        return null;
    }

    public String unregisterCarById(Long carId) {

            String updateQuery = "UPDATE car SET is_booked = ? WHERE id = ?";
            String deleteCustomerCarQuery = "DELETE FROM car_customer where car_id = ?";

            try (Connection connection = DatabaseConfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                 PreparedStatement preparedStatementCarCustomer = connection.prepareStatement(deleteCustomerCarQuery)) {

                preparedStatement.setBoolean(1, false);
                preparedStatement.setLong(2, carId);

                int rowsAffected = preparedStatement.executeUpdate();

                preparedStatementCarCustomer.setLong(1, carId);

                preparedStatementCarCustomer.executeUpdate();




                if (rowsAffected > 0) {
                    log.info("Car with ID {} updated successfully. is_booked set to true.", carId);
                    return "Car updated successfully.";
                } else {
                    log.warn("Car with ID {} not found.", carId);
                    return "Car not found.";
                }

            } catch (SQLException e) {
                log.error("An error occurred while updating the car with ID " + carId, e);
                return "An error occurred while updating the car.";
            }
    }
}
