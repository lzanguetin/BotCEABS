package br.com.voxage.botceabs.states.apoio_tecnico;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ApoioTecnico {
	@SuppressWarnings("serial")
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("APOIOTEC");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);

				setPreFunction(botState ->{
					bot.setLastState(BotCEABS.STATES.START);
					
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
						botInputResult.setIntentName(BotCEABS.STATES.NGP);
						break;
					case "2":
						botInputResult.setIntentName(BotCEABS.STATES.TEC);
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
                	put(BotCEABS.STATES.NGP, "/NGP");
                	put(BotCEABS.STATES.TEC, "/TEC");
                	put("MAX_INPUT_ERROR", "/TERMINATE");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}