package com.kompromat.yandex.pages

import geb.Page
import geb.module.FileInput
import geb.module.Textarea
import geb.waiting.WaitTimeoutException
import groovy.util.logging.Slf4j

import java.nio.file.Path

@Slf4j
class PlacePage extends Page {

    static url = "https://yandex.ru/maps/org"

    static at = {
        waitFor(20) {
            $('div._type_bookmark')
        }
    }

    static content = {
        reviews(required: false) {$('span.business-header-rating-view__text._clickable', dynamic: true)}
        star5(required: false) {$('div.business-rating-edit-view > div:nth-child(5)')}
        comment(required: false) {$('span.business-review-form-comment-view__textarea > span > textarea', dynamic: true).module(Textarea)}
        filesInput(required: false) {$('div.business-review-form > div.add-photos-view > div > input[type=file]', dynamic: true).module(FileInput)}
        send(required: false) {$('div.business-review-form__controls > div:nth-child(1)', dynamic: true)}
        cancel(required: false) {$('div.business-review-form__controls > div:nth-child(1)', dynamic: true)}
        placeName {$('h1.orgpage-header-view__header')}
        gratitude(required: false) {$('div.business-review-dialog-form__gratitude')}
    }

    PlacePage doRating(String message, Path photo) {
        if(reviews) {
            try {
                reviews.click()
                waitFor(5) {
                    star5
                }
                star5.click()
                waitFor(5) {
                    comment
                }
                comment.text = message
                filesInput.file = photo.toFile()
                send.click()
                waitFor(10) {
                    gratitude
                }
            } catch(WaitTimeoutException e) {
                log.warn("yandex.maps.place: Unable to review {}, skipping...", url)
            } catch(Exception e) {
                log.warn("yandex.maps.place: Unable to review {}, skipping...", url)
            }
        }
        return this
    }

    String getName() {
        placeName.text()
    }

}
