package com.kompromat.yandex.pages

import geb.Page
import geb.module.SearchInput
import geb.navigator.Navigator
import org.openqa.selenium.Keys

class MapsPage extends Page {

    static url = "https://yandex.ru/maps"

    static at = {
        waitFor(20) {
            title.contains("Карты")
        }
    }

    static content = {
        restaurants { $('a.catalog-grid-view__item._id_food', title: 'Где поесть')}
        searchInput { $('input.input__control', placeholder: 'Поиск мест и адресов').module(SearchInput)}
        searchResults { $('ul.search-list-view__list > li > div > div > a')}
    }

    MapsPage doSearch(String search) {
        searchInput.text = search
        searchInput << Keys.ENTER
        return this
    }

    MapsPage panTo(Double lat, Double lng, Double zoom = 15) {
        String newUrl = browser.currentUrl - browser.currentUrl.toURL().query + "ll=${lat}%2C${lng}&z=${zoom}"
        browser.go(newUrl)
    }

    Navigator getSearchResult() {
        waitFor(10) {
            searchResults
        }
        searchResults
    }

}
