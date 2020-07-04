# Coroutines and Retrofit
In this Project, one will learn-
* **How to use Coroutines with Retrofit and MVVM**
* **How to download a list of Country information from an endpoint**
	* The list of country information are downloaded and parsed from this [sample URL](https://raw.githubusercontent.com/DevTides/countries/master/countriesV2.json), in the background via a Coroutine running on **IO Dispatcher** Threads.
* **How to load and show the list in a RecyclerView**

## Getting Started
Link to the source Starter Project is [here](https://github.com/DevTides/AndroidCoroutinesRetrofitMVVM). Can also be cloned from the [commit](https://github.com/kaushiknsanji/Complete_Coroutines-Catalin_Stefan/commit/ac872cd8805a9d10e14d09d3b194aeff80f8fe8c) of this project, in order to start with the initial state.

## Built With
* **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)** and **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)** Jetpack components for MVVM
* **[Retrofit](https://square.github.io/retrofit/)** for Networking
* **[GSON](https://github.com/google/gson)** for converting JSON to java objects
* **[Glide](https://bumptech.github.io/glide/)** for loading Images
* Kotlin **[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)** for background processing
