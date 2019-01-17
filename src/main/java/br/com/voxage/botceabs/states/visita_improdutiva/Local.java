package br.com.voxage.botceabs.states.visita_improdutiva;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.botceabs.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Local {
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("LOCAL");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotCEABS.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					DadosFluxo dadosFluxo = bot.getDadosFluxo();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs();
					
					dadosFluxo.setLocal(userInput);
					
					botInputResult.setResult(BotInputResult.Result.OK);
					botInputResult.setIntentName(BotCEABS.STATES.CONTATO);
	
					return botInputResult;
				});
				
				setPosFunction((botState, inputResult) ->{
                	BotStateFlow botStateFlow = new BotStateFlow();
                	botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                	botStateFlow.navigationKey = inputResult.getIntentName();
                	
                	return botStateFlow;
                });
                
                setNextNavigationMap(new HashMap<String, String>(){{
                	put(BotCEABS.STATES.CONTATO, "#CONTATO");
                    put("MAX_INPUT_ERROR", "#CONTATO");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}