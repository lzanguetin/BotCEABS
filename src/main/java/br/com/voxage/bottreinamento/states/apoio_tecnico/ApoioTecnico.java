package br.com.voxage.bottreinamento.states.apoio_tecnico;

import java.util.HashMap;

import br.com.voxage.bottreinamento.BotTreinamento;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ApoioTecnico {
	public static BotState load(BotTreinamento bot) {
		return new BotState("/") {{
				setId("APOIOTEC");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);

				setPreFunction(botState ->{
					bot.setLastState(BotTreinamento.STATES.START);
					
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
						botInputResult.setIntentName(BotTreinamento.STATES.NGP);
						break;
					case "2":
						botInputResult.setIntentName(BotTreinamento.STATES.TEC);
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
                	put(BotTreinamento.STATES.NGP, "/NGP");
                	put(BotTreinamento.STATES.TEC, "/TEC");
                	put("MAX", "/TERMINATE");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}