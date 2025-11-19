package pablo.tzeliks.service;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.service.impl.DescontoImposto;
import pablo.tzeliks.service.impl.DescontoPlanoSaude;
import pablo.tzeliks.service.interfaces.Desconto;
import pablo.tzeliks.service.interfaces.RegraStrategy;
import pablo.tzeliks.service.strategy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalarioService {

    List<RegraStrategy> regrasDesconto = List.of(
            new RegraIsento(),
            new RegraFaixaBaixa(),
            new RegraFaixaMedia(),
            new RegraFaixaAlta(),
            new RegraMetalurgico(),
            new RegraTextil());

    List<Desconto> tiposDescontos = List.of(
            new DescontoImposto(regrasDesconto),
            new DescontoPlanoSaude()
    );

    // Calculo

    public double calcularSalarioLiquido(Funcionario funcionario) {
        double totalDescontos = 0.0;

        for (Desconto desconto : tiposDescontos) {
            totalDescontos += desconto.calculaSalario(funcionario);
        }

        return funcionario.getSalarioBase() - totalDescontos;
    }

    public List<String> getDescontos() {

        List<String> descontos = new ArrayList<>();

        for (Desconto desconto : tiposDescontos) {
            descontos.add(desconto.getNome());
        }

        return descontos;
    }

    public Map<String, Double> getRegras(Funcionario funcionario) {

        Map<String, Double> regras = new HashMap<>();

        for (RegraStrategy regra : regrasDesconto) {

            if (regra.aplicavel(funcionario)) {
                regras.put(regra.getName(), regra.getPorcentagem());
            }
        }

        return regras;
    }
}