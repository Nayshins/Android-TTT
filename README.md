Android Tic Tac Toe
===========

This is an Android app that uses my Java Unbeatable Tic Tac Toe engine. It can be built and played locally by using an emulator or personal android device.

Requires:
Android SDK >16


Opening in Android Studio
==
[Android Studio](https://developer.android.com/sdk/installing/studio.html)

First clone the repository into the directory of your choosing. Then open Android Studio and select Import Project. When prompted to select a file, go to the directory you cloned the app into, and then select the gradle.build file. This will build the app into the IDE.

Run on Emulator
==
After importing the app into Android Studio, you can run the app on the emulator by going to the run menu and selecting run App. This will build the app and run it on your emulator.

If you have not set up your emulator, click on the tools menu on the top of your screen. In the tools menu hover over Android, and then select the AVD Manager. This will bring up the Android Virtual Device Manager. From here select the Create button, now you should see something like this: [AVD](http://developer.android.com/images/developing/avd-dialog.png)
The only required settings for the AVD are to set the SDK > 16 and that device screen is not larger than 5". There are certain settings that will make it run faster, and I have a [blog post](http://jakenations.me/2014/09/26/how-to-make-the-android-emulator-faster/) on speeding it up!

Run on Device
==

Google has a guide on how to properly load the app onto your phone in a debugging environment, and that can be found [Here](http://developer.android.com/tools/device.html)
