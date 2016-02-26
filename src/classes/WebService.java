package classes;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import org.json.simple.JSONObject;
	import org.json.simple.parser.JSONParser;
	
	public class WebService {
	 
		private final String USER_AGENT = "Mozilla/5.0";
	 
		// HTTP GET request
		public void sendGet(Filme movie) throws Exception {
                        
	 
			URL obj = new URL(movie.getUrl());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String resposta = response.toString();
			JSONObject json = (JSONObject)new JSONParser().parse(resposta);
					//print result
                        movie.setSinopse(json.get("Plot").toString());
                        movie.setPoster(json.get("Poster").toString());
                        
                        
		}
                
                public void sendImdbID(Filme movie, String imdbID) throws Exception {
                        
                        String link = "http://www.omdbapi.com/?i=ID&plot=full&r=json";
                        link = link.replace("ID", imdbID);
			URL obj = new URL(link);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String resposta = response.toString();
			JSONObject json = (JSONObject)new JSONParser().parse(resposta);
					//print result
                        movie.setSinopse(json.get("Plot").toString());
                        movie.setPoster(json.get("Poster").toString());
                        
                        
		}
	}
