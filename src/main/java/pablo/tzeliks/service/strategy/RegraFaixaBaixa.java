package pablo.tzeliks.service.strategy;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.service.interfaces.RegraStrategy;

public class RegraFaixaBaixa implements RegraStrategy {

    @Override
    public boolean aplicavel(Funcionario funcionario) {
        return funcionario.getSalarioBase() < 2000;
    }

    @Override
    public double calcularDesconto(Funcionario funcionario) {
        return funcionario.getSalarioBase() * 15;
    }

    @Override
    public String getName() {
        return "Faixa Baixa";
    }

    @Override
    public double getPorcentagem() {
        return 15;
    }
}