package org.visual.local.store.provider

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.inject.Inject
import jakarta.inject.Provider
import jakarta.persistence.EntityManager

class JpaQueryFactoryProvider @Inject constructor(private val entityManager: EntityManager) :
    Provider<JPAQueryFactory> {

  override fun get(): JPAQueryFactory {
    return JPAQueryFactory(entityManager)
  }
}
