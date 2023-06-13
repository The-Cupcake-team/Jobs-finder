package com.cupcake.usecase

import javax.inject.Inject

class ValidateFieldUseCade @Inject constructor() {

     operator fun invoke(text: String) {
        if (text.isBlank() || text.isEmpty()) {
            throw Throwable("this field is required")
        }
    }
}