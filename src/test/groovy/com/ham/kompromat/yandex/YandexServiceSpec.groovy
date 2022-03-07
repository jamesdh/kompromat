package com.ham.kompromat.yandex


import com.kompromat.yandex.YandexService
import com.kompromat.yandex.pages.LandingPage
import com.kompromat.yandex.pages.LoginPage
import com.kompromat.yandex.pages.MapsPage
import geb.spock.GebSpec
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class YandexServiceSpec extends GebSpec {

    @Inject YandexService yandex

    void "go to login"() {
        given:
        LandingPage landingPage = to(LandingPage)

        when:
        landingPage.goToLogin()

        then:
        at(LoginPage)
    }

    void "maps search"() {
        given:
        MapsPage mapsPage = to(MapsPage)

        when:
        mapsPage.doSearch("Где поесть Москва")

        then:
        mapsPage.getSearchResult()
    }
}
