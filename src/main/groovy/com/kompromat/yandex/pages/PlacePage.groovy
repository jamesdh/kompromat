package com.kompromat.yandex.pages

import geb.Page
import geb.module.FileInput
import geb.module.Textarea

import java.nio.file.Path

class PlacePage extends Page {

    static url = "https://yandex.ru/maps/org"

    static at = {
        waitFor(20) {
            $('div._type_bookmark')
        }
    }

    static content = {
        star5 {$('div.business-rating-edit-view > div:nth-child(5)')}
        comment(required: false) {$('span.business-review-form-comment-view__textarea > span > textarea', dynamic: true).module(Textarea)}
        filesInput(required: false) {$('div.business-review-form > div.add-photos-view > div > input[type=file]', dynamic: true).module(FileInput)}
        send(required: false) {$('div.business-review-form__controls > div:nth-child(1)', dynamic: true)}
        cancel(required: false) {$('div.business-review-form__controls > div:nth-child(1)', dynamic: true)}
        placeName {$('h1.orgpage-header-view__header')}
    }

    PlacePage doRating(String message, Path photo) {
        waitFor(5000) {
            star5
        }
        star5.click()
        sleep(2000)
        waitFor(5000) {
            comment
        }
        comment.text = message
        filesInput.file = photo.toFile()
        cancel.click()
        return this
    }

    String getName() {
        placeName.text()
    }

}
