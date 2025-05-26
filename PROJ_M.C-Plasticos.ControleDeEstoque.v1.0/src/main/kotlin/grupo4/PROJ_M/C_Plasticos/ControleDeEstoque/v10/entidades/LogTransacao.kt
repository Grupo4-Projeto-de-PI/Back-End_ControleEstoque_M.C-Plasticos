package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class LogTransacao(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var data: LocalDateTime?,
    @field: PositiveOrZero var peso: BigDecimal?,
    var valorTotal: BigDecimal?,

    @Enumerated(EnumType.STRING)
    var tipoOperacao: TipoOperacao = TipoOperacao.CONCLUIDO,

    var dataLog: LocalDateTime,

    @ManyToOne
    var fkTransacao: Transacao? = null,

    @ManyToOne
    var fkTransacaoProduto: Produto? = null


){

    constructor() : this(null, null, null, null, TipoOperacao.CONCLUIDO, LocalDateTime.now(), null, null)

}

