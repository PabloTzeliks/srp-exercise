package pablo.tzeliks.service.strategy;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.service.interfaces.RegraStrategy;

public class RegraIsento implements RegraStrategy {

    @Override
    public boolean aplicavel(Funcionario funcionario) {
        return funcionario.getSalarioBase() < 1000;
    }

    @Override
    public double calcularDesconto(Funcionario funcionario) {
        return 0.0;
    }

    @Override
    public String getName() {
        return "Isento";
    }

    @Override
    public double getPorcentagem() {
        return 0;
    }
}