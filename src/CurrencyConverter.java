import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        DecimalFormat decimal = new DecimalFormat("00.00");
        Boolean running = true;
        do {
            HashMap<Integer, String> currencyCodes = new HashMap<>();

            currencyCodes.put(1, "BRL");
            currencyCodes.put(2, "USD");
            currencyCodes.put(3, "EUR");
            currencyCodes.put(4, "ARS");
            currencyCodes.put(5, "KRW");
            currencyCodes.put(6, "CAD");


            Integer from, to;
            String fromCode, toCode;
            double amount;

            Scanner reader = new Scanner(System.in);

            System.out.println("Bem Vindo(a) ao conversor de moedas!");
            ShowMenu showMenu = new ShowMenu();
            showMenu.selectFromCode();

            from = reader.nextInt();
            while (from < 1 || from > 6) {
                System.out.println("Por favor, selecione uma opção válida (1-6)");
                showMenu.selectFromCode();
                from = reader.nextInt();
            }
            fromCode = currencyCodes.get(from);

            showMenu.selectToCode();

            to = reader.nextInt();
            while (to < 1 || to > 6) {
               showMenu.selectToCode();
                to = reader.nextInt();
            }
            toCode = currencyCodes.get(to);

            System.out.println("Qual valor deseja converter?");
            amount = reader.nextFloat();

            Converter converter = new Converter();
            double taxaCambio = converter.searchCurrency(fromCode, toCode);

            double result = amount * taxaCambio;

            System.out.println("Valor " + decimal.format(amount) + " [" + fromCode +  "] " + "corresponde ao valor final de " + decimal.format(result) + " " + "[" + toCode + "]" + ".\n");

            System.out.println("Gostaria de converter novamente?");
            System.out.println("1:Sim \t Qualquer outro número: Não ");

            if(reader.nextInt() != 1){
                running = false;
            }

        }while (running);


        System.out.println("Obrigada por usar o conversor de moedas");
    }
}