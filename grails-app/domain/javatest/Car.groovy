package javatest

class Car {
    Model model
    Producer producer
    FuelType fuelType
    Double engineVolume
    Double fuelConsumption
    public String toString()
    {
        return "Model: " + model.name + " Producer: " + producer.name + " ID " + id
    }
    static constraints = {

    }
}
