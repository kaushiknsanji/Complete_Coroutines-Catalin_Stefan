# News Ticker : Coroutines, Coroutines Flow and Retrofit
In this Project, one will learn-
* **How to use Coroutines Flow with Retrofit and MVVM**
* **How to use Coroutines Flow to update the UI Interface**
* **How to download the News items and show them periodically in a RecyclerView, one after the other with a delay, in order to simulate a News Ticker**
	* The News items are downloaded and parsed from this [sample URL](https://raw.githubusercontent.com/DevTides/NewsApi/master/news.json), in the background using the cold nature of Coroutines Flow, to emit each article item one after the other with a delay in-between, in order to simulate a News Ticker.
	* This flow is directly converted to a LiveData, and is shown on the RecyclerView when its registered observer gets the update.
	
## Getting Started
Link to the source Starter Project is [here](https://github.com/DevTides/AndroidCoroutinesFlow). Can also be cloned from the [commit](https://github.com/kaushiknsanji/Complete_Coroutines-Catalin_Stefan/commit/fba7adc84fabc26cf027f51e9bdcd8062f278c33) of this project, in order to start with the initial state.

## Built With
* **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)** and **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)** Jetpack components for MVVM
* **[Retrofit](https://square.github.io/retrofit/)** for Networking
* **[GSON](https://github.com/google/gson)** for converting JSON to java objects
* **[Glide](https://bumptech.github.io/glide/)** for loading Images
* Kotlin **[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)** and Coroutines **[Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)** for background processing
