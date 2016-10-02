package com.workscape.vehicleidentifier.dao;

import java.util.List;

public interface QueryBuilder<T> {
    QueryBuilder<T> setParameter(String name, Object value);

    List<T> asList();
}
