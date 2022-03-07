package com.kompromat.yandex.pages

import geb.Page
import geb.module.PasswordInput
import geb.module.TextInput

class LoginPage extends Page {

    static url = "https://passport.yandex.ru/auth?origin=home_yandexid&retpath=https%3A%2F%2Fyandex.ru&backpath=https%3A%2F%2Fyandex.ru"

    static at = {
        waitFor(20) {
            $('span.passp-add-account-page-title').text() == "Войдите с Яндекс ID"
        }
    }

    static content = {
        username(required: false) { $('#passp-field-login').module(TextInput) }
        password(required: false) { $('#passp-field-passwd', dynamic: true).module(PasswordInput) }
        login { $('#passp\\:sign-in', dynamic: true) }
        notNowPhoneSubmit(required: false) { $('div.passp-button > button > span', text: "Не&nbsp;сейчас", dynamic: true) }
    }

    LandingPage doLogin(String user, String pass) {
        username.text = user
        login.click()
        sleep(1000)
        password.text = pass
        login.click()
        sleep(1000)
        browser.to(LandingPage)
        return (LandingPage)browser.page
    }
}
