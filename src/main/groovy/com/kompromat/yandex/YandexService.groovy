package com.kompromat.yandex

import com.kompromat.geo.City
import com.kompromat.yandex.pages.LandingPage
import com.kompromat.yandex.pages.PlacePage
import com.kompromat.yandex.pages.MapsPage
import geb.Browser
import geb.navigator.Navigator
import groovy.util.logging.Slf4j
import jakarta.inject.Singleton
import org.openqa.selenium.ElementClickInterceptedException
import org.openqa.selenium.JavascriptExecutor

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
                log.debug("yandex.maps: searching for places...")
                mapsPage.doSearch("Где поесть")
                sleep(1000)
                Navigator results = mapsPage.getSearchResult()
                int initialSize = results.size() - 1
                log.info("yandex.maps: reviewing {} places for the current map location", initialSize)
                (0..initialSize).each {
                    Navigator place = results[it]
                    String url = place.attr('href')
                    log.debug("yandex.maps: opening page for {}", url)
                    ((JavascriptExecutor)browser.driver).executeScript("arguments[0].scrollIntoView(true);", place.firstElement())
                    sleep(2500)
                    try {
                        place.click()
                        PlacePage placePage = at(PlacePage)
                        log.info("yandex.maps: reviewing {} @ {}", placePage.name, url)
                        placePage.doRating(message, photo)
                        placePage.goBackToResults()
                    } catch(Exception e) {
                        log.info("yandex.maps: unable to open page {}", url)
                    }
                }
                sleep(5000)
            }
        }
    }
}
