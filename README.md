# KOMPROMAT

\ käm-prə-ˌmat \ - noun _Russian_

1. compromising information that is used to discredit a person or group
2. a tool for informing the Russian people of the atrocities being committed by their government

Kompromat is an automated WebDriver-based tool used for driving an instance of Google Chrome against Yandex Maps. It
takes a message and photo, locates random restaurants in and around the intended Russian/Belarusian city, and leaves a
review for each. This should make it possible to leave hundreds if not thousands of reviews per day for a single running
instance. In this way it **may** be possible to get information to the Russian people about what is happening just
across their border.

At the moment it is only tested on MacOS. It *should* work on Windows with Windows Subsystem for Linux (WSL) but 
this has not been tested. 

The following is a screen capture of the tool at work: <br>

[![Kompromat at work](kompromat.gif)](https://www.youtube.com/watch?v=jm4DOPDnHoQ)

## Table of Contents:

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
5. [Built With](#built-with)

## Installing

1. Verify you have a Java 11 or greater runtime installed.
   1. From a command line, type `java --version` and hit enter to see the version
2. Verify you have a recent version of [Chrome](https://www.google.com/chrome/index.html) installed.
3. Download the compressed zip of the latest release from [here](https://github.com/jamesdh/kompromat/releases/latest).
4. Extract the zip.
5. Open a terminal and go to the extracted directory, e.g. : <br>
   `cd Downloads/kompromat-1.1.0`

## Running

First you will have to create a Yandex account. This is actually fairly easy to do with a few simple tricks.

#### Creating a Yandex account

1. Using Google Chrome, go to https://yandex.ru
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

Once you have your Yandex account credentials, you can run the program via your opened terminal:

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

## Suggestions and Caveats

#### Use a VPN
This is fairly self-explanatory. It doesn't hurt to even minimally try and cover your tracks and hide your location.   

#### Use a burner email

It would be wise to create a dummy email address rather than using your personal one. Using a service such as
[10MinuteMail](https://10minutemail.com) should make this extremely simple to do.

#### Yandex is asking for a phone number

In the event Yandex thinks you may be a bot (let's be honest, you effectively are one if you are running this) they may
prompt you for a phone number as a form of a captcha. I would strongly advise against using your own personal phone 
number. Instead, use a "burner" phone number. There are several apps you can use to create a virtual/temporary phone 
number. Some may or may not work in your country, but you can try the following:

- [Burner](https://www.burnerapp.com/)
- [Receive-Sms-Free.cc](https://receive-sms-free.cc)
- [Temp-SMS](https://temp-sms.org)

#### Yandex is asking if I am a robot

At times Yandex will detect your actions as a bot and prompt you with a small box to click verifying that you are a
human, and may also ask you to fill out a captcha. In the event this captcha is required, Kompromat should pause for 
up to 5 minutes to allow you to enter the necessary text. 

## How to contribute

The following is a list of things that may help make this tool easier to use by more people:

1. Test and/or provide any fixes for Windows machines
2. Machine learning to automatically decipher the captcha text, thus not requiring users to try and type out cyrillic
   which many people will struggle with
3. Automate the Yandex account creation process. Dependent on #2 above. 

## Built With

- [Micronaut](https://micronaut.io)
- [Groovy](https://groovy.apache.org)
- [Geb](https://gebish.org)
