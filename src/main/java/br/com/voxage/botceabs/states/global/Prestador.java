package br.com.voxage.botceabs.states.global;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.botceabs.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Prestador {
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("PREST");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotCEABS.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					DadosFluxo dadosFluxo = bot.getDadosFluxo();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs();
					
					dadosFluxo.setPrest(userInput);
					
					switch(dadosFluxo.getOption()) {
					case "4":
						try {
			                botInputResult.setIntentName(BotCEABS.STATES.ATENDENTE);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					case "5":
						try {
			                botInputResult.setIntentName(BotCEABS.STATES.ATENDENTE);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					default:
						botInputResult.setIntentName(BotCEABS.STATES.CPF);
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
                	put(BotCEABS.STATES.CPF, "#CPF");
                	put(BotCEABS.STATES.ATENDENTE, "#ATENDENTE");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}