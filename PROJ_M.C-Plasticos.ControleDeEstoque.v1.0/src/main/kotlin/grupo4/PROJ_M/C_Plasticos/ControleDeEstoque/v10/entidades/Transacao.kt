package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
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

    var categoria: categoriaEnum? = null,

    var tipoOperacao: tipoOperacaoEnum? = null,
    var peso: Double? = null,
    var valorTotal: Double? = null,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var data: LocalDateTime? = LocalDateTime.now()

)
