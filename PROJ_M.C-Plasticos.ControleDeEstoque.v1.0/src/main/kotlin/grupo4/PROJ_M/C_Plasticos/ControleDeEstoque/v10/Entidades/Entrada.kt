package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.criteria.CriteriaBuilder.In
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Entity
data class Entrada(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCompra: Int?,
    var fkProduto: Int?,
    var fkCategoria: Int?,
    var peso: Double?,
    var valorTotal: Double?,
    var fkUsuario: Int?,
    var fkFornecedor: Int?,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var data: LocalDateTime = LocalDateTime.now()

) {

    constructor() : this(
        null, null, null, null, null,
        null, null
    )

}

