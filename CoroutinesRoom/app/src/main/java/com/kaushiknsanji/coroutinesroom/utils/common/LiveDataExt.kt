package com.kaushiknsanji.coroutinesroom.utils.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Kotlin file for extension functions on `LiveData`.
 *
 * @author Kaushik N Sanji
 */

/**
 * Extension function on the [LiveData] of [Event]s to let observe the [Event] only when its
 * content has never been consumed. When the Event's content was never consumed,
 * the given action [onEventUnconsumedContent] will be executed on the content.
 *
 * @param T The type of data wrapped in an [Event].
 * @param owner The [LifecycleOwner] which controls the observer.
 * @param onEventUnconsumedContent The lambda action to be executed on the content [T].
 */
inline fun <T : Any> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnconsumedContent: (data: T) -> Unit
) {
    observe(owner, Observer { event: Event<T>? ->
        event?.getContentIfNotConsumed()?.let(onEventUnconsumedContent)
    })
}

/**
 * Extension function on [LiveData] of any Nullable content of type [T] that is NOT
 * wrapped in any other types like [Event]. When observed, the given [action]
 * will be executed on the content.
 *
 * @param T The type of content that is NOT wrapped in any other types. Can be `null`.
 * @param owner The [LifecycleOwner] which controls the observer.
 * @param action The lambda action to be executed on the Nullable content [T].
 */
inline fun <T : Any?> LiveData<T>.observeNull(
    owner: LifecycleOwner,
    crossinline action: (data: T?) -> Unit
) {
    observe(owner, Observer {
        action(it)
    })
}

/**
 * Extension function on [LiveData] of any Nullable content of type [T] that is NOT
 * wrapped in any other types like [Event]. When observed, the given [action]
 * will be executed on the content, only when the content is `NOT NULL`.
 *
 * @param T The type of content that is NOT wrapped in any other types. Can be `null`.
 * @param owner The [LifecycleOwner] which controls the observer.
 * @param action The lambda action to be executed on the content [T] when it is `NOT NULL`.
 */
inline fun <T : Any?> LiveData<T>.observeNonNull(
    owner: LifecycleOwner,
    crossinline action: (data: T) -> Unit
) {
    observe(owner, Observer {
        it?.let(action)
    })
}