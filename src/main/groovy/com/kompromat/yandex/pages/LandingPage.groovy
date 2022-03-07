package com.kompromat.yandex.pages

import geb.Page
import groovy.util.logging.Slf4j
import jakarta.inject.Singleton

@Singleton
@Slf4j
class LandingPage extends Page {

    static url = "https://yandex.ru"

    static at = {
        waitFor(20) {
            $('div.home-logo__default')
        }
    }

    static content = {
        mapsButton(to: MapsPage) { $('div.services-new__icon_maps') }
        toLogin(to: LoginPage) { $('div.desk-notif-card__login-new-item-title', text: "Войти")}
    }

    LoginPage goToLogin() {
        toLogin.click()
        sleep(2000)
        (LoginPage)browser.page
    }

}
