package br.com.voxage.botceabs.states._start;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import br.com.voxage.botceabs.BotCeabs;
import br.com.voxage.botceabs.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Start{
	public static BotState load(BotCeabs bot) {
		return new BotState("/") {{
				setId("START");
				
				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
						BotInputResult botInputResult = new BotInputResult();
						DadosFluxo dadosFluxo = bot.getDadosFluxo();
						botInputResult.setResult(BotInputResult.Result.OK);
												
						String userInput = userInputs.getConcatenatedInputs();
						
						switch(userInput) {
						case "1":
							try {
				            	dadosFluxo.setOption("1");
				                botInputResult.setIntentName(BotCeabs.STATES.FECHAROS);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "2":
							try {
				            	dadosFluxo.setOption("2");
				                botInputResult.setIntentName(BotCeabs.STATES.VISITAIMP);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "3":
							try {
				            	dadosFluxo.setOption("3");
				                botInputResult.setIntentName(BotCeabs.STATES.REAGCLIENTE);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "4":
							try {
				            	dadosFluxo.setOption("4");
				                botInputResult.setIntentName(BotCeabs.STATES.APOIOTEC);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "5":
							try {
				            	dadosFluxo.setOption("5");
				                botInputResult.setIntentName(BotCeabs.STATES.OUTROS);
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
					put(BotCeabs.STATES.FECHAROS, "#FECHAROS");
					put(BotCeabs.STATES.VISITAIMP, "#VISITAIMP");
					put(BotCeabs.STATES.REAGCLIENTE, "#REAGCLIENTE");
					put(BotCeabs.STATES.APOIOTEC, "#APOIOTEC");
					put(BotCeabs.STATES.OUTROS, "#OUTROS");				
                    put("MAX_INPUT_ERROR", "/TERMINATE");
                    put("MAX_NO_INPUT", "/TERMINATE");
				}});
		}};
	}
}



					