package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.monica

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica.Clima
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica.ClimaService
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.json.JSONObject
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clima")
class ClimaController (private val service: ClimaService){

    private val apiKey = "3cc2ca5b51ee68307eecb53d90bb1e01"

    @GetMapping("/{cidade}")
    fun obterClima(@PathVariable cidade: String): ResponseEntity<ClimaDto> {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=${cidade}&appid=${apiKey}&units=metric&lang=pt_br"

        val restTemplate = RestTemplate()
        val response = restTemplate.getForObject(url, String::class.java)

        return try {
            val json = JSONObject(response)

            val nomeCidade = json.getString("name")
            val descricao = json.getJSONArray("weather").getJSONObject(0).getString("description")
            val temperatura = json.getJSONObject("main").getDouble("temp")
            val umidade = json.getJSONObject("main").getInt("humidity")
            val vento = json.getJSONObject("wind").getDouble("speed")

            val clima = ClimaDto(nomeCidade, descricao, temperatura, umidade, vento)

            ResponseEntity.ok(clima)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }

    @PostMapping
    fun criar(@RequestBody dto: ClimaDto): ResponseEntity<Clima> {
        val clima = dto.toEntity()
        val salvo = service.salvar(clima)
        return ResponseEntity.ok(salvo)
    }

    @GetMapping
    fun listar(): ResponseEntity<List<Clima>> = ResponseEntity.ok(service.listar())

    @GetMapping("/{id}")
    fun buscar(@PathVariable id: Long): ResponseEntity<Clima> {
        val clima = service.buscarPorId(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(clima)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody dto: ClimaDto): ResponseEntity<Clima> {
        val atualizado = service.atualizar(id, dto.toEntity()) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(atualizado)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<Void> {
        service.deletar(id)
        return ResponseEntity.noContent().build()
    }

    fun Clima.toDto() = ClimaDto(cidade, descricao, temperatura, umidade, vento)
    fun ClimaDto.toEntity() = Clima(cidade = cidade, descricao = descricao, temperatura = temperatura, umidade = umidade, vento = vento)



}