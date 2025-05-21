package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


@Entity
data class AtorComercial(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @field:NotBlank var nome: String?,
    @field:Size(min = 11, max=11)  var telefone: String?,
    @ManyToOne
    @JoinColumn(name = "tipo")
    var fkTipoPessoa: TipoComercial? = null,

    //ponto de dúvida
    var fkPapelComercial: Int?
){

    constructor(): this(null, null, null, null, null)

}