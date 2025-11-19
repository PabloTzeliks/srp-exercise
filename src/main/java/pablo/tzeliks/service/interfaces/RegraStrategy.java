package pablo.tzeliks.service.interfaces;

import pablo.tzeliks.model.Funcionario;

public interface RegraStrategy {

    boolean aplicavel(Funcionario funcionario);
    double calcularDesconto(Funcionario funcionario);

    String getName();
    double getPorcentagem();
}