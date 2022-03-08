# KOMPROMAT

\ käm-prə-ˌmat \ - noun _Russian_

1. compromising information that is used to discredit a person or group
2. a tool for informing the Russian people of the atrocities being committed by their government

Kompromat is an automated WebDriver-based tool used for driving a sandboxed instance of FireFox against Yandex Maps. It
takes a message and photo, locates random restaurants in and around the intended Russian/Belarusian city, and leaves a
review for each. In this way it **may** be possible to let the people within Russia see what is happening just across
their border.

At the moment it is only tested on MacOS. It *should* work on Windows, but you will at the very least need to manually
install geckodriver for it to work. A script is provided for MacOS which will do this for you.

## Table of Contents:
___

1. [Installing](#installing)
2. [Running](#running)
    1. [Creating a Yandex account](#creating-a-yandex-account)
    2. [Modifying the review text/photo](#modifying-the-review-text-and-photo)
    3. [Running the script](#running-the-script)
3. [Suggestions and Caveats](#suggestions-and-caveats)
    1. [Use a burner email](#use-a-burner-email)
    2. [Yandex wants a phone number](#yandex-is-asking-for-a-phone-number)
    3. [Yandex thinks I'm a robot](#yandex-is-asking-if-i-am-a-robot)
4. [How to contribute](#how-to-contribute)

---

## Installing

1. Make sure you have the latest version of [FireFox](https://www.mozilla.org/en-US/firefox/download/thanks/) installed.
2. Download the compressed zip of the latest release from [here](https://github.com/jamesdh/kompromat/releases/latest).
3. Extract the zip to any directory.
4. Open a terminal and go to the extracted directory.
5. Run the [geckodriver.sh](https://github.com/jamesdh/kompromat/blob/master/src/main/dist/geckodriver.sh) script, e.g.
   `$ ./geckodriver.sh`. This installs the correct version
   of [WebDriver](https://www.selenium.dev/documentation/webdriver/)
   for your operating system and only needs to be run the first time.
---
## Running

First you will have to create a Yandex account. This is actually fairly easy to do with a few simple tricks.

#### Creating a Yandex account

1. Using a recent version of Google Chrome, go to https://yandex.ru
2. On the right side of the address bar you should see a small translation icon. Click it and select "English". The page
   should largely be visible in English now.
3. Click "Log in" in the upper right corner.
4. Click "Create ID".
5. Add the necessary information to create an account. **NOTE**: You do not need to add a phone number when initially
   signing up, but you may be prompted for it later as a form of "captcha".
   See [adding a phone number](#adding-a-phone-number)
   to avoid using your actual phone number.
6. You may be prompted with a "captcha" of text, in which case you will have to retype what is shown in the captcha
   image. Don't be intimidated by this even though it's in cyrillic, it's actually quite easy:
    1. On MacOS:
        1. In the desktop menu bar, click the  menu, then System Preferences
        2. In System Preferences, click Keyboard -> Input Sources
        3. Click the '+' button, search for Russian, click Add
        4. Finally, click 'Show Input menu in menu bar'
        5. In your menu bar you should now see a small flag icon of your current country
        6. Click the icon, select Russian
        7. Сlick the icon again, select 'Show Keyboard Viewer'
        8. Now with your cursor in the captcha input box, type the letters as you see them in the captcha image using
           the simulated Russian keyboard on screen.

#### Modifying the review text and photo

By default [a message is already defined](https://github.com/jamesdh/kompromat/blob/master/src/main/dist/message.txt)
containing a Russian and English translation. The message is contained in the `message.txt` file in the same directory
as the `kompromat` script. You can edit this file to say whatever you like, but we'd recommend that you make a plea to
the hearts and minds of ordinary people, and refrain from being cruel/bitter. The idea is to win people over, not to
make them turn their backs in anger.

Likewise, [a default photo](https://github.com/jamesdh/kompromat/blob/master/src/main/dist/photo.jpg) is included that
will be left in each review. This photo can also be changed so long as it remains the same filename.

#### Running the script

Once you have your Yandex account credentials, you can run the program as so:

```
Usage: kompromat [--city=<city>] <username> <password>
      <username>      A Yandex username
      <password>      A password for the given username
      --city=<city>   Optional city. Defaults to MOSCOW. Can be one of: MOSCOW,
                        STPETE, BELGORAD, MINSK, SEVASTOPOL, YALTA, GROZNY
```

Simplest Example, defaulting to Moscow:

```bash
$ ./kompromat 'myusername' 'mypassword'
```

Specify what city to focus on:

```bash
$ ./kompromat --city=MINSK 'myusername' 'mypassword'
```

---

## Suggestions and Caveats

#### Use a burner email

It would be wise to create a dummy email address rather than using your personal one. Using a service such as
[10MinuteMail](https://10minutemail.com) should make this extremely simple to do.

#### Yandex is asking for a phone number

In the event Yandex thinks you may be a bot (let's be honest, you effectively are one if you are running this) they may
prompt you for a phone number as a form of a captcha. I would advise against using your own personal phone number.
Instead, download [Burner](https://www.burnerapp.com/)
to your phone and register a new phone number for receiving the Yandex verification text. The burner number will not be
associated with you in any way, and it should not cost anything during the free trial.

#### Yandex is asking if I am a robot

At times Yandex will detect your actions as a bot and prompt you with a small box to click verifying that you are a
human. This can be difficult to catch while the script is running. The browser may time out and exit before you've done
so. The easy way to get around this is to manually log into Yandex (not using the script) and to do the captcha. Then
the next time you run the script it should not prompt you with the captcha.

---

## How to contribute

The following is a list of things that may help make this tool easier to use by more people:

1. A Windows equivalent of the geckodriver script to automate the installation of WebDriver on Windows
2. Machine learning to automatically decipher the captcha text, thus not requiring users to try and type out 
   cyrillic which many people will struggle with
3. Automate the Yandex account creation process. 
4. Improvements to help avoid bot detection (and thus the captcha requests)
