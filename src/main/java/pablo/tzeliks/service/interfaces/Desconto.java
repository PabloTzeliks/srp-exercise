package pablo.tzeliks.service.interfaces;

import pablo.tzeliks.model.Funcionario;

public interface Desconto {

    double calculaSalario(Funcionario funcionario);

    String getNome();
}