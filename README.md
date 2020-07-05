# Complete Kotlin Coroutines - Catalin Stefan

My Solution repository for the [course on Kotlin Coroutines](https://www.udemy.com/course/coroutines/) by [Catalin Stefan](https://www.udemy.com/user/catalinstefan2/).

## What one will learn
* Theory and Fundamentals of Coroutines
	* Core components
		* Scope
		* Context
		* Suspending functions
		* Jobs
		* Dispatchers
		* Coroutine Builders
		* Switching context
		* Exception handling
	* Asynchronous Flow
		* Flow Builders
		* Flow properties
		* Flow Operators
		* Exception handling
	* Channels
		* Channel Producer
		* Channel Pipelines
		* Fan-out Principle (Single Channel to multiple Coroutines)
		* Fan-in Principle (Multiple Coroutines to Single Channel)
		* Buffered Channels
		* Ticker Channels
	* Concurrency and Shared State
		* Concurrency and Synchronization approaches
			* Atomic Variables
			* Thread confinement
			* Mutual exclusion locks
* Coroutines and Coroutines Flow with Retrofit
* Coroutines with Room

## Prerequisite
* Android development knowledge with Lifecycle components like ViewModels and LiveData
* Kotlin
* Retrofit
* Room database
* Navigation component
* MVVM architecture

## Branches and Projects
* **[master](https://github.com/kaushiknsanji/Complete_Coroutines-Catalin_Stefan/tree/master)**
	* This is the main/default branch that contains the following practice projects with Coroutines
		* **[ImageProcessingCoroutines](https://github.com/kaushiknsanji/Complete_Coroutines-Catalin_Stefan/tree/master/ImageProcessingCoroutines)**
			* This project shows how to download an Image from a URL and then apply a "Black & White" filter on the image, all in the background using Coroutines.
		* **[CoroutinesRetrofit](https://github.com/kaushiknsanji/Complete_Coroutines-Catalin_Stefan/tree/master/CoroutinesRetrofit)**
			* This project shows how to download a list of Country information from a URL/endpoint in the background via a Coroutine, and then display it on a RecyclerView.
		* **[CoroutinesRoom](https://github.com/kaushiknsanji/Complete_Coroutines-Catalin_Stefan/tree/master/CoroutinesRoom)**
			* This project implements the **First Time User Experience** using **Room** as the backend to onboard and authenticate users.
			* Onboarding and Authentication of Users is done by communicating with the Room database in the background via a Coroutine.
		* **[CoroutinesFlowRetrofit](https://github.com/kaushiknsanji/Complete_Coroutines-Catalin_Stefan/tree/master/CoroutinesFlowRetrofit)**
			* This project shows how to download a list of News items from a URL/endpoint in the background via Coroutine Flows, and then emits each News item with a delay in-between to simulate a News Ticker.
			* The above Flow of News items is converted into a LiveData to show the result in a RecyclerView when the registered observer gets the update.
* **[idea_practice]()**
	* This branch contains **[]()** project with easy to understand codes on each of the Coroutines concepts.

## Udemy Certificate
<a href="https://www.udemy.com/certificate/UC-143a922d-65c3-4f6a-9957-6c92dd00232c/">
<img alt="Udemy Certificate" src="https://udemy-certificate.s3.amazonaws.com/image/UC-143a922d-65c3-4f6a-9957-6c92dd00232c.jpg?v=1593609739000" width="50%">
</a>
