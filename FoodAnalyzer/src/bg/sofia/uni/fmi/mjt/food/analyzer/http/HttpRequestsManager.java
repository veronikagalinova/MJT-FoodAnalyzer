package bg.sofia.uni.fmi.mjt.food.analyzer.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductDetails;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.report.Report;

public final class HttpRequestsManager {

	private static final String API_URL = "https://api.nal.usda.gov/ndb";

	private static final String API_KEY = "8A0wDyAusd3yNtqfdRqfTg7ZpNLj01BuLsx7NEyZ";

	private static final String SEARCH_REQUEST = "/search/?q=";

	private static final String REPORT_REQUEST = "/V2/reports?ndbno=";

	private static final String API_PARAM = "&api_key=";

	public static ProductDetails getFoodDetails(String product) {
		HttpClient client = HttpClient.newHttpClient();
		StringBuffer requestBuilder = new StringBuffer();
		requestBuilder.append(API_URL).append(SEARCH_REQUEST).append(product).append(API_PARAM).append(API_KEY);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestBuilder.toString())).build();

		try {
			String jsonResponse = client.send(request, BodyHandlers.ofString()).body();
			Gson gson = new Gson();
			ProductDetails result = gson.fromJson(jsonResponse, new TypeToken<ProductDetails>() {
			}.getType());

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Report getFoodReport(String ndbno) {
		HttpClient client = HttpClient.newHttpClient();
		StringBuffer requestBuilder = new StringBuffer();
		requestBuilder.append(API_URL).append(REPORT_REQUEST).append(ndbno).append(API_PARAM).append(API_KEY);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestBuilder.toString())).build();

		try {
			String jsonResponse = client.send(request, BodyHandlers.ofString()).body();
			Gson gson = new Gson();
			Report report = gson.fromJson(jsonResponse, new TypeToken<Report>() {
			}.getType());

			return report;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
