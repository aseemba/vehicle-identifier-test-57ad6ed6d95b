package com.workscape.vehicleidentifier.dao;

public interface BaseDAO<T, Id> {
  QueryBuilder<T> createQuery(final String query);
}
