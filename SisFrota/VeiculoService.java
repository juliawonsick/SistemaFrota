// TRABALHO EM DULPA: JÚLIA WONSICK RA: 1136562 E LAURA CEMIN IORA RA: 1131015
import java.util.ArrayList;
import java.util.List;

public class VeiculoService {
    private List<Veiculo> veiculosDB;

    public List<Veiculo> getVeiculosDB() {
        return veiculosDB;
    }

    public VeiculoService() {
        this.veiculosDB = new ArrayList<>();
    }
    
    public Veiculo save(Veiculo veiculo) throws Exception {
        if (veiculo == null) {
            throw new Exception("Objeto nulo");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new Exception("Campo Modelo não pode ser em branco");
        }
        if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty()) {
            throw new Exception("Campo Marca não pode ser em branco");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new Exception("Campo Placa não pode ser em branco");
        }

        // Verificar se já existe um veículo com a mesma placa
        for (Veiculo v : veiculosDB) {
            if (v.getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
                throw new Exception("Já existe um veículo cadastrado com a placa: " + veiculo.getPlaca());
            }
        }

        veiculosDB.add(veiculo);
        return veiculo;
    }

    public Veiculo pesquisarVeiculos(String placa) {
        for (Veiculo veiculo : veiculosDB) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public Veiculo removerVeiculo(String placa) {
        for (Veiculo veiculo : veiculosDB) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculosDB.remove(veiculo); // Removendo o veículo encontrado
                return veiculo;
            }
        }
        return null;
    }
}

