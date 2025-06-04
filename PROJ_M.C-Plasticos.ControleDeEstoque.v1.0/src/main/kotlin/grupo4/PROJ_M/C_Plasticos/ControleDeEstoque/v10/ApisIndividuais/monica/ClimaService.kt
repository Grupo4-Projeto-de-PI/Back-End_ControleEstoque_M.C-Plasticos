package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica

import org.springframework.stereotype.Service

@Service
class ClimaService(private val repository: ClimaRepository) {

    fun salvar(clima: Clima): Clima = repository.save(clima)

    fun listar(): List<Clima> = repository.findAll()

    fun buscarPorId(id: Long): Clima? = repository.findById(id).orElse(null)

    fun atualizar(id: Long, novoClima: Clima): Clima? {
        val existente = repository.findById(id).orElse(null) ?: return null
        val atualizado = existente.copy(
            cidade = novoClima.cidade,
            descricao = novoClima.descricao,
            temperatura = novoClima.temperatura,
            umidade = novoClima.umidade,
            vento = novoClima.vento
        )
        return repository.save(atualizado)
    }

    fun deletar(id: Long) = repository.deleteById(id)
}