package sushant.com.codetoart_assignment.ApiCall;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class BuilderClass {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    private BuilderClass() {

    }
    public static synchronized Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
