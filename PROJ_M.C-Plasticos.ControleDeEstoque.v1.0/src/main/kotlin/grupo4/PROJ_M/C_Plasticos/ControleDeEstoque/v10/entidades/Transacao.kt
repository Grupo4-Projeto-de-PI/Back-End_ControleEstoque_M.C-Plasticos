package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Transacao(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @ManyToOne
    var fkUsuario: Usuario? = null,

    @ManyToOne
    var fkAtorComercial: AtorComercial? = null,

    @ManyToOne
    var fkProduto: Produto? = null,

    @ManyToOne
    var fkCategoria: Categoria? = null,

    var tipoOperacao: String?,
    var peso: Double?,
    var valorTotal: Double?,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var data: LocalDateTime = LocalDateTime.now()

) {

    constructor() : this(null, null, null, null, null,
        null, null, null,)

}
