package pablo.tzeliks.service.impl;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.service.interfaces.Desconto;
import pablo.tzeliks.service.interfaces.RegraStrategy;

import java.util.List;

public class DescontoImposto implements Desconto {

    private List<RegraStrategy> listaDescontos;

    public DescontoImposto(List<RegraStrategy> listaDescontos) {
        this.listaDescontos = listaDescontos;
    }

    // Construindo Descontos
    @Override
    public double calculaSalario(Funcionario funcionario) {

        for (RegraStrategy descontoStrategy : listaDescontos) {
            if (descontoStrategy.aplicavel(funcionario)) {
                return descontoStrategy.calcularDesconto(funcionario);
            }
        }

        return 0.0;
    }

    public String getNome() {
        return "Desconto Imposto";
    }
}