package com.erikschouten.cleanarchitecture.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import com.erikschouten.cleanarchitecture.dependency.Authenticator
import com.erikschouten.cleanarchitecture.Config

class AuthenticatorImpl(
    private val issuer: String,
    val audience: String,
    val realm: String,
    private val secret: Algorithm = Algorithm.HMAC256(uuid4().toString()),
) : Authenticator {
    val verifier = JWT
        .require(secret)
        .withAudience(audience)
        .withIssuer(issuer)
        .build()!!

    override fun generate(id: Uuid) = JWT
        .create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withSubject(id.toString())
        .sign(secret)!!

    constructor(config: Config) : this(config.jwtDomain, config.jwtAudience, config.jwtRealm)
}
