package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.logTransacaoEnum.logTransacaoEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import jakarta.persistence.*
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class LogTransacao(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @field: PositiveOrZero
    var peso: Double? = null,

    var valorTotal: Double? = null,

    var tipoEdicao: logTransacaoEnum? = null,

    var tipoOperacao: tipoOperacaoEnum? = null,

    var dataLog: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    var fkTransacao: Transacao? = null,

    @ManyToOne
    var fkTransacaoProduto: Produto? = null
) {

    constructor() : this(null, null, null, null, null, LocalDateTime.now(), null, null)
}

