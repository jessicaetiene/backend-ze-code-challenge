package com.ze.codechallenge.common.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<E, T, R> {

    R convertTransferObjectResponse(E e);

    E convertEntity(T t);

    default List<R> convertTransferObjectResponse(List<E> e) {
        return e.stream().map(e1 -> convertTransferObjectResponse(e1)).collect(Collectors.toList());
    }
}
