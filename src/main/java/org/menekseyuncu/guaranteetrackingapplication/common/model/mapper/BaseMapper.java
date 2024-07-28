package org.menekseyuncu.guaranteetrackingapplication.common.model.mapper;

import java.util.List;

/**
 * A base mapper interface for converting between source and target types.
 *
 * @param <S> the source type
 * @param <T> the target type
 */
public interface BaseMapper<S, T> {

    /**
     * Maps an object of type {@code S} to an object of type {@code T}.
     *
     * @param source the source object to be mapped
     * @return the mapped target object
     */
    T map(S source);

    /**
     * Maps a list of objects of type {@code S} to a list of objects of type {@code T}.
     *
     * @param source the list of source objects to be mapped
     * @return the list of mapped target objects
     */
    List<T> map(List<S> source);

}