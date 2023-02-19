package com.dpashko.fastrent.domain.onboarding

import com.dpashko.fastrent.domain.onboarding.model.IsOnboardingCompleted
import com.dpashko.fastrent.domain.repository.IdentityRepository
import javax.inject.Inject

/**
 * UseCase that check whether user passed Onboarding flow.
 */
class IsOnboardingCompletedUseCase @Inject constructor(
    private val identityRepository: IdentityRepository
) {

    /**
     * Asynchronously checks whether the user has completed the Onboarding flow.
     */
    suspend fun invoke(): Boolean {
        val persisted = identityRepository.get(IsOnboardingCompleted::class.java)
        return persisted?.isCompleted ?: false
    }
}
