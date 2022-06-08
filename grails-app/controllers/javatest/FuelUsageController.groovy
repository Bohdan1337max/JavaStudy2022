package javatest

import grails.converters.JSON

class FuelUsageController {

    FuelCostService fuelCostService
    def index() {
        render "asdasd"
    }

  def drivingPrice() {
      render fuelCostService.calculateCostModel(params.model)
  }

    def saveNewCar(){
       Car car =  fuelCostService.saveNewCar("Focus","Ford","Diesel",6.2,1.6)
        render car as JSON
    }
    def showAllCars() {
        render view: 'showAllCars', model: [carList: Car.list()]
    }

}
