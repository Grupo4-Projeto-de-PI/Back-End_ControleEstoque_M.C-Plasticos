package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class LogPrecoProduto (
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @ManyToOne
    var fkProduto: Produto? = null,
    @field: PositiveOrZero var precoNovo: BigDecimal?,
    @field: PositiveOrZero var precoAntigo: BigDecimal?,
    var dataAlteracao: LocalDateTime

){
    constructor(precoAntigo: Any?, fkProduto: Produto, precoNovo: BigDecimal?, dataAlteracao: LocalDateTime) : this( null, fkProduto, precoNovo, precoAntigo as BigDecimal, dataAlteracao)
    constructor() : this(null, null, null, BigDecimal.ZERO, LocalDateTime.now()) {

    }
}