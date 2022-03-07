package com.kompromat.geo

import java.util.concurrent.ThreadLocalRandom

enum City {

    BELGOROD("Белгород", 36.41, 50.73, 36.8, 50.49),
    GROZNY("Грозный", 45.45, 43.43, 46.04, 43.13),
    MINSK("Минск", 27.42, 43.97, 27.68, 53.83),
    MOSCOW("Москва", 37.4, 55.9, 37.9, 55.5),
    SEVASTOPOL("Севастополь", 33.37, 44.71, 33.65, 44.51),
    STPETE("Санкт-Петербург", 30.23, 60.02, 30.53, 59.83),
    YALTA("Ялта", 34.11, 44.53, 34.20, 44.47)

    String nameRussian
    Double lat1, lng1, lat2, lng2

    City(String nameRussian, Double lat1, Double lng1, Double lat2, Double lng2) {
        this.nameRussian = nameRussian
        this.lat1 = lat1
        this.lat2 = lat2
        this.lng1 = lng1
        this.lng2 = lng2
    }

    /**
     * Returns a random latitude within the bounds of the given city
     */
    Double getRandomLatitude() {
        Double max = Math.max(lat1, lat2)
        Double min = Math.min(lat1, lat2)
        return ThreadLocalRandom.current().nextDouble(min, max)
    }

    /**
     * Returns a randdom longitude within the bounds of the given city
     */
    Double getRandomLongitude() {
        Double max = Math.max(lng1, lng2)
        Double min = Math.min(lng1, lng2)
        return ThreadLocalRandom.current().nextDouble(min, max)
    }

}
