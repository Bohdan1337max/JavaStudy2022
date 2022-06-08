package javatest

class FuelUsageController {

    FuelCostService fuelCostService
    def index() {
        render "asdasd"
    }

  def drivingPrice() {
      render fuelCostService.calculateCostModel(params.model)
  }

    def saveNewCar(){
       render fuelCostService.saveNewCar("Focus","Ford","Diesel",6.2,1.6)
    }



}
