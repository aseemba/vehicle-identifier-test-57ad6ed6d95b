package com.workscape.vehicleidentifier.dao.impl;

import com.google.inject.Provider;
import com.workscape.vehicleidentifier.dao.BaseDAO;
import com.workscape.vehicleidentifier.dao.QueryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class BaseDAOImpl<T, Id> implements BaseDAO<T, Id> {
  private Class<? extends T> entityClass;
  protected Provider<EntityManager> emProvider;

  protected BaseDAOImpl(final Class<? extends T> entityClass, final
    Provider<EntityManager> emProvider) {
    this.entityClass = entityClass;
    this.emProvider = emProvider;
  }

  public QueryBuilder<T> createQuery(final String query) {
    EntityManager em = emProvider.get();
    Query q = em.createQuery(query);
    return new QueryBuilderImpl<T>(q);
  }
}
