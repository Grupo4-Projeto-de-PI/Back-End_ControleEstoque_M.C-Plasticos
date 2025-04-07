package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
data class Usuario (
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var codigoFuncionario: Int?,
    @field: NotBlank @field:Size(min = 1, max = 15) var nome: String? = null,
    @field: Min(10) @field: Max(99999999) var senha: Int? = null,
    var dataCriacao: String? = LocalDateTime.now().toString(),
    var primeiroAcesso: Boolean = false,
    var tipoUsuario: String? = null
) {
    constructor(): this(null, null, null)

}