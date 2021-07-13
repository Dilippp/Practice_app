package com.nineleaps.banking.utils;

import java.util.function.Predicate;

public class ArraysHelper {

    /** Find the element that matches the given matcher. */
    public static <T> T find(T[] arr, Predicate<T> matcher) {
        for (T element : arr) {
            if (matcher.test(element)) {
                return element;
            }
        }
        return null;
    }

    /** Checks if all the elements matches the given predicate. */
    public static <T> boolean allMatch(Predicate<T> matcher, T... elements) {
        for (T element : elements) {
            if (!matcher.test(element)) {
                return false;
            }
        }
        return true;
    }
    /** Checks if any of the elements matches the given predicate. */
    public static <T> boolean anyMatch(Predicate<T> matcher, T... elements) {
        for (T element : elements) {
            if (matcher.test(element)) {
                return true;
            }
        }
        return false;
    }
}
