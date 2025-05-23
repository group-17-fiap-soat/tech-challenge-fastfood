package tech.challenge.fastfood.fastfood.common.config.jwt

import com.nimbusds.jose.JWSVerifier
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import tech.challenge.fastfood.fastfood.common.enums.TokenRoleEnum
import java.security.interfaces.RSAPublicKey

object JwtTokenUtil {

    fun validateToken(
        token: String,
        lambdaSecretBase64: String,
    ): Pair<JWTClaimsSet, TokenRoleEnum>? {
        val signedJWT = parseToken(token) ?: return null
        val issuer = signedJWT.jwtClaimsSet.issuer

        return when {
            issuer.contains("cognito-idp") -> {
                if (verifyWithPublicKey(signedJWT, CognitoPublicKeyProvider.getPublicKey(token))) {
                    signedJWT.jwtClaimsSet to TokenRoleEnum.ADMIN
                } else null
            }

            issuer == "auth.lambda" -> {
                if (verifyWithSecret(signedJWT, lambdaSecretBase64)) {
                    signedJWT.jwtClaimsSet to TokenRoleEnum.CUSTOMER
                } else null
            }

            else -> null
        }
    }

    private fun parseToken(token: String): SignedJWT? {
        return try {
            SignedJWT.parse(token)
        } catch (e: Exception) {
            null
        }
    }

    private fun verifyWithSecret(signedJWT: SignedJWT, base64Secret: String): Boolean {
        return try {
            val verifier: JWSVerifier = MACVerifier(base64Secret)
            signedJWT.verify(verifier)
        } catch (e: Exception) {
            false
        }
    }

    private fun verifyWithPublicKey(signedJWT: SignedJWT, publicKey: RSAPublicKey): Boolean {
        return try {
            val verifier: JWSVerifier = RSASSAVerifier(publicKey)
            signedJWT.verify(verifier)
        } catch (e: Exception) {
            false
        }
    }
}