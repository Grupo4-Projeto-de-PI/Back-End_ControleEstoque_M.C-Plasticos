package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


@Entity
data class ParceiroComercial(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @field:NotBlank var nome: String?,
    @field:Size(min = 11, max=11)  var telefone: String?,
    @ManyToOne
    @JoinColumn(name = "fk_tipo_comercial", referencedColumnName = "id")
    var fkTipoPessoa: TipoComercial? = null,

    @ManyToOne
    @JoinColumn(name = "fk_papel_comercial", referencedColumnName = "id")
    var fkPapelComercial: PapelComercial?
){

    constructor(): this(null, null, null, null, null)

}