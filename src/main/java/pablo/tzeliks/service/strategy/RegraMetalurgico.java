package pablo.tzeliks.service.strategy;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.model.Sindicato;
import pablo.tzeliks.service.interfaces.RegraStrategy;

public class RegraMetalurgico implements RegraStrategy {
    @Override
    public boolean aplicavel(Funcionario funcionario) {
        return funcionario.getSindicato() == Sindicato.METALURGICO;
    }

    @Override
    public double calcularDesconto(Funcionario funcionario) {
        return funcionario.getSalarioBase() * 0.025;
    }

    @Override
    public String getName() {
        return "Sindicato Metalúrgico";
    }

    @Override
    public double getPorcentagem() {
        return 2.5;
    }
}