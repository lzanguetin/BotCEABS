package br.com.voxage.botceabs.states.fechar_os;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Cor {
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("COR");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotCEABS.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs();
					
					botInputResult.setResult(BotInputResult.Result.OK);
					botInputResult.setIntentName(BotCEABS.STATES.PLACA);
	
					return botInputResult;
				});
				
				setPosFunction((botState, inputResult) ->{
                	BotStateFlow botStateFlow = new BotStateFlow();
                	botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                	botStateFlow.navigationKey = inputResult.getIntentName();
                	
                	return botStateFlow;
                });
                
                setNextNavigationMap(new HashMap<String, String>(){{
                	put(BotCEABS.STATES.PLACA, "#PLACA");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}