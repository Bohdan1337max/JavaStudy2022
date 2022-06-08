package javatest

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

            Model model = Model.findByName(modelName)
            if(model == null) {
                model = new Model(name: modelName)
                model.save()
            }

            Producer producer = Producer.findByName(producerName)
            if(producer == null) {
                producer = new Producer(name: producerName)
                producer.save()
            }

            FuelType fuelType = FuelType.findByName(fuelTypeName)
            if(fuelType == null) {
                fuelType = new FuelType(name: fuelTypeName)
                fuelType.save()
            }
            Car car = new Car(model: model ,
                    producer: producer,
            fuelType: fuelType,
            fuelConsumption: fuelConsumption,
            engineVolume:engineVolume)

            car.save()
        }

}
