package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Service

class AtorComercialService {

    fun getTelefone(telefoneNovo: String): String {
        if (telefoneNovo.length != 11) {
            throw IllegalArgumentException("O telefone deve ter 11 algarismos")
        }
        return telefoneNovo
}

    fun getNome(nomeNovo: String): String {
        if (nomeNovo.isBlank()) {
            throw IllegalArgumentException("O nome não pode ser vazio")
        }
        return nomeNovo
    }


}