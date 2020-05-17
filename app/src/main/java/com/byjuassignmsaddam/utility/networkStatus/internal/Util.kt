package com.byjuassignmentbysaddam.networkStatus.internal

import java.util.ArrayList

/**
 * Created by chweya on 29/08/17.
 */

object Util {

    fun <T> getSnapshot(other: Collection<T>): List<T> {
        // toArray creates a new ArrayList internally and this way we can guarantee entries will not
        // be null. See #322.
        val result = ArrayList<T>(other.size)
        for (item in other) {
            result.add(item)
        }
        return result
    }
}
