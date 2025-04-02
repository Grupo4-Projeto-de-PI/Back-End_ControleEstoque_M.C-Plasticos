package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class Cliente(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int?,
    var nome: String?,
    // Tipo de cliente, empresa ou pessoa fíica
    var tipo: String?,
    // var identificador: String?,
    var telefone: Int?,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var dataCadastro: LocalDate = LocalDate.now()

){

    constructor(): this(null, null, null, null)

}
