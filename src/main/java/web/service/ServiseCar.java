package web.service;

import web.models.Car;

import java.util.ArrayList;
import java.util.List;


public class ServiseCar {
    List<Car> cars = new ArrayList<>();

    public  List<Car> getListOfCars(){
        Car bmw = new Car("BMW", "black", 2020);
        Car mersedes = new Car("MERSEDES", "white", 2019);
        Car bently = new Car("BENTLY", "blue", 2017);
        cars.add(bmw);
        cars.add(mersedes);
        cars.add(bently);
        return cars;
    }
}
