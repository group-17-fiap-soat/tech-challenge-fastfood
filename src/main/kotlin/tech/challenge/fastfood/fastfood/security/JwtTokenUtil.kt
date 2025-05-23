package com.seuapp.util

import com.nimbusds.jose.JWSVerifier
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import java.security.interfaces.RSAPublicKey
import java.util.*

object JwtTokenUtil {

    fun validateTokenAndResolveRole(
        token: String,
        lambdaSecretBase64: String,
        cognitoPublicKey: RSAPublicKey
    ): Pair<JWTClaimsSet, String>? {
        val claimsSet = parseToken(token) ?: return null
        val issuer = claimsSet.issuer

        return when {
            issuer.contains("cognito-idp") -> {
                if (verifyWithPublicKey(token, cognitoPublicKey)) {
                    claimsSet to "ADMIN"
                } else null
            }

            issuer == "auth.lambda" -> {
                if (verifyWithSecret(token, lambdaSecretBase64)) {
                    claimsSet to "CLIENT"
                } else null
            }

            else -> null
        }
    }

    private fun parseToken(token: String): JWTClaimsSet? {
        return try {
            val signedJWT = SignedJWT.parse(token)
            signedJWT.jwtClaimsSet
        } catch (e: Exception) {
            null
        }
    }

    private fun verifyWithSecret(token: String, secretBase64: String): Boolean {
        return try {
            val key = Base64.getDecoder().decode(secretBase64)
            val signedJWT = SignedJWT.parse(token)
            val verifier: JWSVerifier = MACVerifier(key)
            signedJWT.verify(verifier)
        } catch (e: Exception) {
            false
        }
    }

    private fun verifyWithPublicKey(token: String, publicKey: RSAPublicKey): Boolean {
        return try {
            val signedJWT = SignedJWT.parse(token)
            val verifier: JWSVerifier = RSASSAVerifier(publicKey)
            signedJWT.verify(verifier)
        } catch (e: Exception) {
            false
        }
    }
}
