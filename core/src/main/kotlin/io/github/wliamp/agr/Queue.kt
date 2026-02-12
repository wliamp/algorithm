package io.github.wliamp.agr

import java.util.concurrent.ConcurrentLinkedQueue

class Queue<T : Any> {
    private val queue = ConcurrentLinkedQueue<T>()

    /** ---------- Basic Actions ---------- **/

    /**
     * Adds a single item to the queue.
     * @return Boolean indicating whether the item was added successfully.
     */
    fun enqueue(item: T): Boolean =
        queue.add(item)

    /**
     * Adds multiple items to the queue at once.
     * @return Boolean indicating whether all items were added successfully.
     */
    fun enqueueAll(items: Collection<T>): Boolean =
        queue.addAll(items)

    /**
     * Removes and returns the head of the queue.
     * @return T? containing the removed item, or null if the queue is empty.
     */
    fun dequeue(): T? =
        queue.poll()

    /**
     * Returns the head of the queue without removing it.
     * @return T? containing the head item, or null if the queue is empty.
     */
    fun peek(): T? =
        queue.peek()

    /** ---------- Query Actions ---------- **/

    /**
     * Returns the current size of the queue.
     * @return Int representing the number of items in the queue.
     */
    fun size(): Int =
        queue.size

    /**
     * Checks if the queue is empty.
     * @return Boolean indicating whether the queue contains no items.
     */
    fun isEmpty(): Boolean =
        queue.isEmpty()

    /**
     * Checks if the queue contains the given item.
     * @param item the item to check for.
     * @return Boolean indicating presence of the item.
     */
    fun contains(item: T): Boolean =
        queue.contains(item)

    /**
     * Returns a snapshot of all items in the queue as a List.
     * @return List<T> representing all items currently in the queue.
     */
    fun toList(): List<T> =
        queue.toList()

    /** ---------- Criteria-based Actions ---------- **/

    /**
     * Filters items in the queue based on a list of criteria.
     * @param criteria List of ICriteria<T> to apply.
     * @return List<T> containing items that match all criteria.
     */
    fun filter(criteria: List<ICriteria<T>>): List<T> =
        Criteria.filter(queue.toList(), criteria)

    /**
     * Filters items in the queue based on a single criterion.
     * @param criteria ICriteria<T> to apply.
     * @return List<T> containing items that match the criterion.
     */
    fun filter(criteria: ICriteria<T>): List<T> =
        filter(listOf(criteria))

    /**
     * Removes and returns the first item that matches the given criterion.
     * @param criteria ICriteria<T> to match.
     * @return T? containing the removed item, or null if none matched.
     */
    fun dequeueBy(criteria: ICriteria<T>): T? {
        val match = filter(criteria).firstOrNull()
        return if (match != null && queue.remove(match)) match else null
    }

    /**
     * Removes all items that match the given criterion from the queue.
     * @param criteria ICriteria<T> to match.
     * @return Boolean indicating whether any items were removed.
     */
    fun removeBy(criteria: ICriteria<T>): Boolean =
        queue.removeAll(filter(criteria))

    /** ---------- Maintenance Actions ---------- **/

    /**
     * Clears all items from the queue.
     * @return Unit completing when the queue has been cleared.
     */
    fun clear() {
        queue.clear()
    }
}
