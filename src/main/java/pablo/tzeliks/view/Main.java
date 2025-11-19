package pablo.tzeliks.view;

import pablo.tzeliks.model.Funcionario;
import pablo.tzeliks.model.Sindicato;
import pablo.tzeliks.service.SalarioService;
import pablo.tzeliks.service.impl.DescontoImposto;
import pablo.tzeliks.service.impl.DescontoPlanoSaude;
import pablo.tzeliks.service.interfaces.Desconto;
import pablo.tzeliks.service.interfaces.RegraStrategy;
import pablo.tzeliks.service.strategy.RegraFaixaBaixa;
import pablo.tzeliks.service.strategy.RegraIsento;
import pablo.tzeliks.view.helper.PrintHelper;

import java.util.List;
import java.util.Map;

public class Main {

    // Main simples apenas para testar funcionalidade de Strategy

    public static void main(String[] args) {

        SalarioService salarioService = new SalarioService();

        List<RegraStrategy> regrasDesconto = List.of(
                new RegraIsento(),
                new RegraFaixaBaixa(),
                new RegraFaixaBaixa());

        Desconto descontoPlanoSauda = new DescontoPlanoSaude();
        Desconto descontoImposto = new DescontoImposto(regrasDesconto);

        List<Desconto> tiposDescontos = List.of(
                descontoImposto,
                descontoPlanoSauda
        );

        // Criação Funcionários

        Funcionario f1 = new Funcionario("Anacleto", 1800, "Bobinagem", Sindicato.METALURGICO);
        Funcionario f2 = new Funcionario("Félix", 6000, "Torneiro Senior", Sindicato.METALURGICO);
        Funcionario f3 = new Funcionario("Foba", 3000, "Provador de Roupa", Sindicato.TEXTIL);

        List<String> descontos = salarioService.getDescontos();
        Map<String, Double> regrasF1 = salarioService.getRegras(f1);
        Map<String, Double> regrasF2 = salarioService.getRegras(f2);
        Map<String, Double> regrasF3 = salarioService.getRegras(f3);

        PrintHelper.imprimirDescontos(descontos);

        System.out.println("\n------------------------------------------\n");

        System.out.println(f1.toString());
        System.out.println();
        PrintHelper.imprimirRegras(regrasF1);

        System.out.println("\n------------------------------------------\n");
        System.out.println(f2.toString());
        System.out.println();
        PrintHelper.imprimirRegras(regrasF2);

        System.out.println("\n------------------------------------------\n");
        System.out.println(f3.toString());
        System.out.println();
        PrintHelper.imprimirRegras(regrasF3);
    }
}