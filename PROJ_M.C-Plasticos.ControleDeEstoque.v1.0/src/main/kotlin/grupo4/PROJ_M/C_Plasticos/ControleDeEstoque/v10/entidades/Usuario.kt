package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.usuarioEnum.tipoUsuarioEnum
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
data class Usuario(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_funcionario")
    var codigoFuncionario: Int? = null,

    @field:NotBlank
    @field:Size(min = 1, max = 15)
    var nome: String? = null,

    @field:Size(max = 8, message = "A senha deve ter no máximo 8 dígitos")
    var senha: String? = null,

    var dataCriacao: LocalDateTime = LocalDateTime.now(),
    var primeiroAcesso: Boolean = false,
    var tipoUsuario: tipoUsuarioEnum? = null,
    var ativo: Boolean? = true,
    var online: Boolean? = false
)
