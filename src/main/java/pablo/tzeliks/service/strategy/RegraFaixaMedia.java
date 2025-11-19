package pablo.tzeliks.service.strategy;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.service.interfaces.RegraStrategy;

public class RegraFaixaMedia implements RegraStrategy {

    @Override
    public boolean aplicavel(Funcionario funcionario) {
        return funcionario.getSalarioBase() < 5000;
    }

    @Override
    public double calcularDesconto(Funcionario funcionario) {
        return funcionario.getSalarioBase() * 0.20;
    }

    @Override
    public String getName() {
        return "Faixa Média";
    }

    @Override
    public double getPorcentagem() {
        return 20;
    }
}
