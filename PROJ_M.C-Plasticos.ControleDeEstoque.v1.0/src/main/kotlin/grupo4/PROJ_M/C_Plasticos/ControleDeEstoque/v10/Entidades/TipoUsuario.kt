package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.validation.constraints.Size
import jakarta.persistence.Id

@Entity
data class TipoUsuario(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    @field: Size(min = 1, max = 200)var tipo: String?

){
    constructor() : this(null, null)
}
