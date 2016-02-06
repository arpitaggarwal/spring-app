package com.test.functions;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T, U, Z, R> {

	/**
	 * Applies this function to the given arguments.
	 *
	 * @param t
	 *            the first function argument
	 * @param u
	 *            the second function argument
	 * @param z
	 *            the third function argument
	 * @return the function result
	 */
	R apply(T t, U u, Z z);

	/**
	 * Returns a composed function that first applies this function to its
	 * input, and then applies the {@code after} function to the result. If
	 * evaluation of either function throws an exception, it is relayed to the
	 * caller of the composed function.
	 *
	 * @param <V>
	 *            the type of output of the {@code after} function, and of the
	 *            composed function
	 * @param after
	 *            the function to apply after this function is applied
	 * @return a composed function that first applies this function and then
	 *         applies the {@code after} function
	 * @throws NullPointerException
	 *             if after is null
	 */
	default <V> TriFunction<T, U, Z, V> andThen(
			Function<? super R, ? extends V> after) {
		Objects.requireNonNull(after);
		return (T t, U u, Z z) -> after.apply(apply(t, u, z));
	}
}
