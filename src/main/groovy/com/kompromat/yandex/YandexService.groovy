package com.kompromat.yandex

import com.kompromat.geo.City
import com.kompromat.yandex.pages.LandingPage
import com.kompromat.yandex.pages.PlacePage
import com.kompromat.yandex.pages.MapsPage
import geb.Browser
import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import jakarta.inject.Singleton

import java.nio.file.Path

@Singleton
@Slf4j
class YandexService {

    void go(String username, String password, String message, Path photo, City city = City.MOSCOW) {
        Browser.drive {
            LandingPage landingPage = to(LandingPage)
            log.debug("yandex.login: {}", username)
            landingPage.goToLogin().doLogin(username, password)
            sleep(1500)
            MapsPage mapsPage = to(MapsPage)
            sleep(1500)
            while(true) {
                js.exec("return window.stop();")
                Double lat = city.randomLatitude
                Double lng = city.randomLongitude
                log.info("yandex.maps: panning to random location {}, {} near {}", lat, lng, city.name())
                mapsPage.panTo(lat, lng)
                sleep(1500)
                log.debug("yandex.maps: searching for places...")
                mapsPage.doSearch("Где поесть")
                sleep(1500)
                Navigator results = mapsPage.getSearchResult()
                sleep(1500)
                results.each {
                    String url = it.attr('href')
                    log.debug("yandex.maps: opening new tab for {}", url)
                    withNewWindow({js.exec("window.open('$url', 'place');")}, page: PlacePage, close: false) {
                        sleep(1500)
                        PlacePage placePage = at(PlacePage)
                        log.info("yandex.maps: reviewing place {} @ {}", placePage.name, url)
                        placePage.doRating(message, photo)
                        js.exec("window.close();")
                    }
                }
                sleep(5000)
            }
        }
    }
}
