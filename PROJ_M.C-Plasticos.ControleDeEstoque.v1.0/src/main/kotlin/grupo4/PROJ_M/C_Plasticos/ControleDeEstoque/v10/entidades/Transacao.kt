package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Transacao(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @ManyToOne
    var fkUsuario: Usuario? = null,

    @ManyToOne
    var fkParceiroComercial: ParceiroComercial? = null,

    @ManyToOne
    var fkProduto: Produto? = null,

    @ManyToOne
    var fkCategoria: Categoria? = null,

    @ManyToOne
    var tipoOperacao: TipoOperacao? = null,

    var peso: Double? = null,
    var valorTotal: Double? = null,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var data: LocalDateTime? = LocalDateTime.now()

)
