package com.dpashko.fastrent.domain.onboarding.model

import com.dpashko.fastrent.domain.repository.Identity

/**
 * The model represents a flag that represents whether the user completed the Onboarding flow.
 */
@Identity(uuid = "cf15b8c6-de1b-4f6e-b9fb-9fefcff78cc2")
data class IsOnboardingCompleted(

    val isCompleted: Boolean
)
