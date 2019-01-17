package br.com.voxage.bottreinamento.states.reag_cliente;

import java.util.HashMap;

import br.com.voxage.bottreinamento.BotTreinamento;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class MotivoReag {
	public static BotState load(BotTreinamento bot) {
		return new BotState("/") {{
				setId("MOTIVOREAG");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotTreinamento.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs();
					
					botInputResult.setResult(BotInputResult.Result.OK);
					botInputResult.setIntentName(BotTreinamento.STATES.PREST);
	
					return botInputResult;
				});
				
				setPosFunction((botState, inputResult) ->{
                	BotStateFlow botStateFlow = new BotStateFlow();
                	botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                	botStateFlow.navigationKey = inputResult.getIntentName();
                	
                	return botStateFlow;
                });
                
                setNextNavigationMap(new HashMap<String, String>(){{
                	put(BotTreinamento.STATES.PREST, "#PREST");
                    put("MAX_INPUT_ERROR", "#PREST");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}
