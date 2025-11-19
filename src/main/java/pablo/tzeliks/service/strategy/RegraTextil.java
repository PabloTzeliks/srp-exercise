package pablo.tzeliks.service.strategy;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.model.Sindicato;
import pablo.tzeliks.service.interfaces.RegraStrategy;

public class RegraTextil implements RegraStrategy {
    @Override
    public boolean aplicavel(Funcionario funcionario) {
        return funcionario.getSindicato() == Sindicato.TEXTIL;
    }

    @Override
    public double calcularDesconto(Funcionario funcionario) {
        return funcionario.getSalarioBase() * 0.04;
    }

    @Override
    public String getName() {
        return "Sindicato Têxtil";
    }

    @Override
    public double getPorcentagem() {
        return 4;
    }
}