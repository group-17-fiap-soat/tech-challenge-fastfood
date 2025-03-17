package tech.challenge.fastfood.fastfood.common.utils

object Validator {

    fun isValidCpf(cpf: String) = cpf.matches(Regex("^[0-9]{11}$"))

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return email.matches(emailRegex.toRegex())
    }
}