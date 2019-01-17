package br.com.voxage.bottreinamento.states.fechar_os;

import java.util.HashMap;

import br.com.voxage.bottreinamento.BotTreinamento;
import br.com.voxage.bottreinamento.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Os {
	public static BotState load(BotTreinamento bot) {
		return new BotState("/") {{
				setId("OS");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotTreinamento.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					DadosFluxo dadosFluxo = bot.getDadosFluxo();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs();
					
					switch(dadosFluxo.getOption()) {
					case "4":
						try {
			                botInputResult.setIntentName(BotTreinamento.STATES.PREST);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					case "5":
						try {
			                botInputResult.setIntentName(BotTreinamento.STATES.PREST);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					default:
						botInputResult.setIntentName(BotTreinamento.STATES.PROP);
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
                	put(BotTreinamento.STATES.PROP, "#PROP");
                	put(BotTreinamento.STATES.PREST, "#PREST");
                    put("MAX_INPUT_ERROR", "#PROP");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}
