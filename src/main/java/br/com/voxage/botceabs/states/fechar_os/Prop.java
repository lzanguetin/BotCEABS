package br.com.voxage.bottreinamento.states.fechar_os;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.bottreinamento.BotTreinamento;
import br.com.voxage.bottreinamento.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Prop {
	public static BotState load(BotTreinamento bot) {
		return new BotState("/") {{
				setId("PROP");

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
					case "1":
						try {
			                botInputResult.setIntentName(BotTreinamento.STATES.MARCA);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					case "2":
						try {
			                botInputResult.setIntentName(BotTreinamento.STATES.LOCAL);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					case "3":
						try {
			                botInputResult.setIntentName(BotTreinamento.STATES.MOTIVOREAG);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
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
                	put(BotTreinamento.STATES.MARCA, "#MARCA");
                	put(BotTreinamento.STATES.LOCAL, "#LOCAL");
                	put(BotTreinamento.STATES.MOTIVOREAG, "#MOTIVOREAG");
                    put("MAX_INPUT_ERROR", "#PROP");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}
