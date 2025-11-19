package pablo.tzeliks.service.strategy;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.service.interfaces.RegraStrategy;

public class RegraFaixaAlta implements RegraStrategy {

    @Override
    public boolean aplicavel(Funcionario funcionario) {
        return funcionario.getSalarioBase() >= 5000;
    }

    @Override
    public double calcularDesconto(Funcionario funcionario) {
        return funcionario.getSalarioBase() * 0.25;
    }

    @Override
    public String getName() {
        return "Faixa Alta";
    }

    @Override
    public double getPorcentagem() {
        return 25;
    }
}