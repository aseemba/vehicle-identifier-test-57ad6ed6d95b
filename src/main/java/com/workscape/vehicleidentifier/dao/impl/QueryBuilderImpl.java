package com.workscape.vehicleidentifier.dao.impl;

import com.workscape.vehicleidentifier.dao.QueryBuilder;

import java.util.List;

import javax.persistence.Query;

class QueryBuilderImpl<T> implements QueryBuilder<T> {
    private final Query query;

    QueryBuilderImpl(final Query q) {
        this.query = q;
    }

    public QueryBuilder<T> setParameter(String name, Object value) {
        query.setParameter(name, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public List<T> asList() {
        return query.getResultList();
    }
}
