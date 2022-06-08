package javatest

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

@Transactional
class FuelCostService {
static final String FUEL_SERVICE_ADRESS ="https://www.autocentrum.pl/paliwa/ceny-paliw/"
    def calculateCostByFuelUsage(Double fuelUsage)
    {
        Document doc = Jsoup.connect(FUEL_SERVICE_ADRESS).get();
        String priceText = doc.select("div.price").first().text()
        Double price = Double.parseDouble(priceText.split(" ")[0].replace(",","."))
        return price * fuelUsage

    }

    def calculateCostModel(String model) {

        if (model = "ford") {
            return calculateCostByFuelUsage(7.6)
        } else if ("vw") {
            return calculateCostByFuelUsage(6.9)
        } else {
            return 0
        }
    }
        def saveNewCar(String modelName,String producerName, String fuelTypeName, Double fuelConsumption,
                       Double engineVolume)
        {

            Model model = Model.findOrSaveByName(modelName)
            Producer producer = Producer.findOrSaveByName(producerName)
            FuelType fuelType = FuelType.findOrSaveByName(fuelTypeName)

            Car.findOrSaveWhere(model: model ,
                    producer: producer,
            fuelType: fuelType,
            fuelConsumption: fuelConsumption,
            engineVolume:engineVolume)

        }




}
