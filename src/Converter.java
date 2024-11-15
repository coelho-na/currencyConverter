import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converter {
    public double searchCurrency(String fromCode, String toCode){
         double conversionRate = 0;
        String apiKey = "67fa22dc4b9848672666967a";
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + fromCode + "/" + toCode;
        JsonObject jsonResponse;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            conversionRate = jsonResponse.get("conversion_rate").getAsDouble();
        } catch (Exception e) {
            System.out.println("Não foi possivel obter a taxa de câmbio, verifique a opção selecionada.");
        }


        return conversionRate;
    }
}

