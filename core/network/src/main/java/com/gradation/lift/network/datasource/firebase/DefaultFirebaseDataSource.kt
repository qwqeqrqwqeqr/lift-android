package com.gradation.lift.network.datasource.firebase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class DefaultFirebaseDataSource @Inject constructor(
    private val firebaseAuth : FirebaseAuth,
) :
    FirebaseDataSource {


    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = firebaseAuth.currentUser != null

}