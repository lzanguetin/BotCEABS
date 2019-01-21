package br.com.voxage.botceabs;

import java.util.HashMap;
import java.util.Map;

import br.com.voxage.botceabs.models.DadosFluxo;
import br.com.voxage.chat.botintegration.ISearchEngine;
import br.com.voxage.chat.botintegration.ISearchEngineCredentials;
import br.com.voxage.chat.botintegration.TextSearchEngine;
import br.com.voxage.chat.botintegration.annotation.Bot;
import br.com.voxage.chat.botintegration.entities.BotImageType;
import br.com.voxage.vbot.BotContext;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateInteractionType;
import br.com.voxage.vbot.VBot;
import br.com.voxage.vbot.utils.ClassFinder;

@SuppressWarnings("serial")
@Bot(name="BotCEABS")
public class BotCEABS extends VBot {
	
	public static int NO_INPUT_TIMEOUT = 180000;
	
	private String lastState;
	private DadosFluxo dadosFluxo;
	
	 public interface STATES{
	    	String START = "start";
	    	String OS = "os";
	    	String PROP = "prop";
	    	String MARCA = "marca";
	    	String ANO = "ano";
	    	String COR = "cor";
	    	String PLACA = "placa"; 
	    	String CHASSI = "chassi";
	    	String PREST = "prest";
	    	String CPF = "cpf";
	    	String LOCAL = "local";
	    	String CONTATO = "contato";
	    	String MOTIVOIMP = "motivoimp";
	    	String MOTIVOREAG = "motivoreag";
	    	String ATENDENTE = "atendente";

	    	String FECHAROS = "fecharos";
	    	String VISITAIMP = "visitaimp";
	    	String REAGCLIENTE = "reagcliente";
	    	String APOIOTEC = "apoiotec";
	    	String NGP = "ngp";
	    	String TEC = "tec";
	    	String OUTROS = "outros";
	    	String OSPATH = "ospath";
	    	
			String CEABS_OS = "transfer_to_attendant(1)";
		    String CEABS_IMPRODUTIVA = "transfer_to_attendant(2)";
			String CEABS_REAGENDAMENTO = "transfer_to_attendant(3)";
		    String CEABS_APOIO = "transfer_to_attendant(4)";
			String CEABS_OUTROS = "transfer_to_attendant(5)";
	}
	 
	public BotCEABS() {
		this.dadosFluxo = new DadosFluxo();
	}
	 
	public Map<String, Object> getDefaultParameters() {
		return null;
	}

	public String getCustomBotName() {
		return "Bot CEABS";
	}

	public String getCustomImageName() {
		return "ceabs-logo.png";
	}

	public BotImageType getBotImageType() {
		return BotImageType.CUSTOM;
	}

	public boolean recordDialog() {
		return false;
	}

	public void writeTimeoutResult() {
	}

	@Override
	public String getDebugCommand(String arg0) {
		return null;
	}

	@Override
	protected TextSearchEngine getDefaultAutoCompleteSearchEngine() {
		return null;
	}

	@Override
	protected ISearchEngineCredentials getDefaultEngineCredentials() {
		return null;
	}

	@Override
	protected ISearchEngine getDefaultNLPSearchEngine() {
		return null;
	}

	@Override
	protected String getPackageName() {
		return "br.com.voxage.botceabs";
	}

	@Override
	public void loadStates() {
		BotContext botContext = new BotContext();
		Map<String, BotState> m = new HashMap<>();
		m.putAll(ClassFinder.loadAllStates("br.com.voxage.botceabs.states", BotCEABS.class, BotCEABS.this));
		botContext.setId("/");
		botContext.setContextNavigationMap(new HashMap<String, String>() {			
        });
		m.put("END", new BotState("/") {
            {
                setId("END");
                setBotStateInteractionType(BotStateInteractionType.TERMINATE);
            }
        });
		
		m.put("TERMINATE", new BotState("/") {
            {
                setId("TERMINATE");
                setBotStateInteractionType(BotStateInteractionType.TERMINATE);
            }
        });
		
		botContext.setStatesMap(m);
		super.contexts.put("/", botContext);
	}
	
    public void setLastState(String lastState) {
        this.lastState = lastState;
    }
    
    public String getLastState() {
        return lastState;
    }

	public DadosFluxo getDadosFluxo() {
		return this.dadosFluxo;
	}
}
	