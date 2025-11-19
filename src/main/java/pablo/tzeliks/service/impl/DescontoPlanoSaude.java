package pablo.tzeliks.service.impl;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.service.interfaces.Desconto;

public class DescontoPlanoSaude implements Desconto {

    private final double VALOR_PLANO_SAUDE = 200.0;


    @Override
    public double calculaSalario(Funcionario funcionario) {
        return funcionario.getSalarioBase() - VALOR_PLANO_SAUDE;
    }

    public String getNome() {
        return "Desconto Plano de Saúde";
    }
}