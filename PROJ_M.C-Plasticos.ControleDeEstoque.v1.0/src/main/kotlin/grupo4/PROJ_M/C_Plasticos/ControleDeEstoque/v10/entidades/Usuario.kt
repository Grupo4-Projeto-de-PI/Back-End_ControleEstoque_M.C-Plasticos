package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
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

    var dataCriacao: String? = LocalDateTime.now().toString(),

    var primeiroAcesso: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "tipo_usuario", referencedColumnName = "id")
    var tipoUsuario: TipoUsuario? = null,

    var ativo: Boolean? = true,

    var online: Boolean? = false
)
