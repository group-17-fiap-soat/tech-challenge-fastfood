package tech.challenge.fastfood.fastfood.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.math.BigInteger
import java.net.URL
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.RSAPublicKeySpec
import java.util.Base64

object CognitoPublicKeyProvider {
    private var cachedKey: RSAPublicKey? = null

    fun getPublicKey(token: String): RSAPublicKey {
        if (cachedKey != null) return cachedKey!!

        val jwksUrl = URL("https://cognito-idp.us-east-1.amazonaws.com/us-east-1_DXHcffMXE/.well-known/jwks.json")
        val mapper = jacksonObjectMapper()
        val jwks: Map<String, List<Map<String, String>>> = mapper.readValue(jwksUrl)


        val jwtHeader = com.nimbusds.jwt.SignedJWT.parse(token).header
        val kid = jwtHeader.keyID

        val keyData = jwks["keys"]?.firstOrNull { it["kid"] == kid }
            ?: throw IllegalStateException("Chave com kid $kid não encontrada no JWKS")

        val n = Base64.getUrlDecoder().decode(keyData["n"])
        val e = Base64.getUrlDecoder().decode(keyData["e"])

        val modulus = BigInteger(1, n)
        val exponent = BigInteger(1, e)

        val spec = RSAPublicKeySpec(modulus, exponent)
        val keyFactory = KeyFactory.getInstance("RSA")

        cachedKey = keyFactory.generatePublic(spec) as RSAPublicKey
        return cachedKey!!
    }
}
