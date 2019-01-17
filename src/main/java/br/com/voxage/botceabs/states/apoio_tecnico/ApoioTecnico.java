package br.com.voxage.botceabs.states.apoio_tecnico;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCeabs;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ApoioTecnico {
	public static BotState load(BotCeabs bot) {
		return new BotState("/") {{
				setId("APOIOTEC");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);

				setPreFunction(botState ->{
					bot.setLastState(BotCeabs.STATES.START);
					
					BotStateFlow botStateFlow = new BotStateFlow();
					botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
					
					return botStateFlow;
				});
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					botInputResult.setResult(BotInputResult.Result.OK);
											
					String userInput = userInputs.getConcatenatedInputs();
					
					switch(userInput) {
					case "1":
						botInputResult.setIntentName(BotCeabs.STATES.NGP);
						break;
					case "2":
						botInputResult.setIntentName(BotCeabs.STATES.TEC);
						break;
					default:
						botInputResult.setResult(BotInputResult.Result.ERROR);
					}						
					return botInputResult;
			});
				
				setPosFunction((botState, inputResult) ->{
                	BotStateFlow botStateFlow = new BotStateFlow();
                	botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                	botStateFlow.navigationKey = inputResult.getIntentName();
                	
                	return botStateFlow;
                });
                
                setNextNavigationMap(new HashMap<String, String>(){{
                	put(BotCeabs.STATES.NGP, "/NGP");
                	put(BotCeabs.STATES.TEC, "/TEC");
                	put("MAX", "/TERMINATE");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}