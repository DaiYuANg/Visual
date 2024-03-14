package org.visual.local.store.lifecycle

import com.google.inject.persist.PersistService
import jakarta.inject.Inject

class JPAInitializer
@Inject
constructor(
    service: PersistService,
) {

  init {
    service.start()
  }
}
