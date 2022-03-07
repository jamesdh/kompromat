package com.kompromat

import com.kompromat.geo.City
import com.kompromat.yandex.YandexService
import groovy.util.logging.Slf4j
import io.micronaut.configuration.picocli.PicocliRunner
import jakarta.inject.Inject
import picocli.CommandLine
import picocli.CommandLine.Command

import java.nio.file.Path

@Command(name = 'kompromat', mixinStandardHelpOptions = true)
@Slf4j
class KompromatCommand implements Runnable {

    @Inject YandexService yandexService

    @CommandLine.Parameters
    String username

    @CommandLine.Parameters
    String password

    @CommandLine.Option(
            names = "--city",
            description = "Optional city. Defaults to MOSCOW. Can be one of: MOSCOW, STPETE, BELGORAD, MINSK, SEVASTOPOL, YALTA, GROZNY",
            defaultValue = "MOSCOW"
    )
    City city

    static void main(String[] args) throws Exception {
        PicocliRunner.run(KompromatCommand.class, args)
    }

    void run() {
        Path message = Path.of('message.txt')
        Path photo = Path.of('photo.jpg')
        log.info("Starting reviews within {} area", city.name())
        log.info("Using the following text from file {}: {}", message.toAbsolutePath(), message.toFile().text)
        log.info("Using photo from file {}", photo.toAbsolutePath())
        yandexService.go(username, password, message.toFile().text, photo, city)
    }
}
