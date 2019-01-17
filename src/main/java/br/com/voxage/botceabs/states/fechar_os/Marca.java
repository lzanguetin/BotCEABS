package br.com.voxage.botceabs.states.fechar_os;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCeabs;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Marca {
	public static BotState load(BotCeabs bot) {
		return new BotState("/") {{
				setId("MARCA");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotCeabs.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs();
					
					botInputResult.setResult(BotInputResult.Result.OK);
					botInputResult.setIntentName(BotCeabs.STATES.ANO);
	
					return botInputResult;
				});
				
				setPosFunction((botState, inputResult) ->{
                	BotStateFlow botStateFlow = new BotStateFlow();
                	botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                	botStateFlow.navigationKey = inputResult.getIntentName();
                	
                	return botStateFlow;
                });
                
                setNextNavigationMap(new HashMap<String, String>(){{
                	put(BotCeabs.STATES.ANO, "#ANO");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}