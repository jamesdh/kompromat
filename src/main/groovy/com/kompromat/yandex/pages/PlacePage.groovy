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
        editReview(required: false) {$('span.business-review-view__edit', dynamic: true)}
        star5(required: false) {$('div.business-rating-edit-view > div:nth-child(5)')}
        comment(required: false) {$('span.business-review-form-comment-view__textarea > span > textarea', dynamic: true).module(Textarea)}
        filesInput(required: false) {$('div.business-review-form > div.add-photos-view > div > input[type=file]', dynamic: true).module(FileInput)}
        send(required: false) {$('div.business-review-form__controls > div:nth-child(1)', dynamic: true)}
        cancel(required: false) {$('div.business-review-form__controls > div:nth-child(1)', dynamic: true)}
        placeName {$('div.card-title-view__title-link > a')}
        gratitude(required: false) {$('div.business-review-dialog-form__gratitude > button')}
        back(to: MapsPage, required: false) {$('div.small-search-form-view__icon._type_back')}
    }

    PlacePage doRating(String message, Path photo) {
        try {
            // First check if we've already rated this place
            waitFor(5) {
                reviews
            }
            if(reviews) {
                reviews.click()
                waitFor(5) {
                    star5
                }
                star5.click()
            } else if(editReview) {
                editReview.click()
            }
            waitFor(5) {
                comment
            }
            // If we haven't already left a review, then do so
            if(!comment.text) {
                comment.text = message
                filesInput.file = photo.toFile()
                send.click()
                waitFor(10) {
                    gratitude
                }
                gratitude.click()
            }
            sleep(1500)
        } catch(WaitTimeoutException e) {
            log.warn("yandex.maps.place: Unable to review, skipping...", url)
        } catch(Exception e) {
            log.warn("yandex.maps.place: Unable to review, skipping...", url)
        }
        return this
    }

    String getName() {
        placeName.text()
    }

    MapsPage goBackToResults() {
        back.click()
        waitFor(5) {
            !back
        }
        return (MapsPage)browser.page
    }

}
