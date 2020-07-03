# Image Processing in Coroutines

In this Project, one will learn-
* **How to download an Image from a URL**
  * This is accomplished in the background via a Coroutine running on **IO Dispatcher** Threads.
  * The is the [sample URL](https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png) of the Image that is downloaded.
* **How to do Image processing**
  * After downloading the image, we apply a **Black & White** filter on the image, in the background via a Coroutine running on **Default Dispatcher** Threads, which is meant for such computations.
* **Show the processed Image on an ImageView**
  * After processing, image is set on an ImageView in the UI Thread via a Coroutine running on **Android Main Thread Dispatcher**.
