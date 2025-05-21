package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Size

@Entity
data class PapelComercial(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @field: Size(min = 1, max = 45)var tipo: String?
)
{
    constructor() : this(null, null)
}