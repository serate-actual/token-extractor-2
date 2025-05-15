import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class tokenextractor2 implements BurpExtension{
    @Override
    public void initialize(MontoyaApi montoyaApi) {
        montoyaApi.extension().setName("Token Extractor 2");
    }
}
