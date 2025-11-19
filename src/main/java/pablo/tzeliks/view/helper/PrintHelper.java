package pablo.tzeliks.view.helper;

import pablo.tzeliks.service.interfaces.Desconto;

import java.util.List;
import java.util.Map;

public class PrintHelper {

    public static void imprimirDescontos(List<String> descontos) {

        int count = 0;

        for (String desconto : descontos) {

            ++count;
            System.out.printf("%d- Desconto: %s\n", count, desconto);
        }
    }

    public static void imprimirRegras(Map<String, Double> regras) {

        int count = 0;

        for (String regra : regras.keySet()) {

            ++count;
            System.out.printf("%d- | Regra: %s | Porcentagem: %.2f por cento\n", count, regra, regras.get(regra));
        }
    }
}