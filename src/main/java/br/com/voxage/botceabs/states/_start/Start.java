package br.com.voxage.botceabs.states._start;

import java.util.HashMap;
import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.botceabs.models.DadosFluxo;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Start{
	@SuppressWarnings("serial")
	public static BotState load(BotCEABS bot) {
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
				                botInputResult.setIntentName(BotCEABS.STATES.FECHAROS);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "2":
							try {
				            	dadosFluxo.setOption("2");
				                botInputResult.setIntentName(BotCEABS.STATES.VISITAIMP);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "3":
							try {
				            	dadosFluxo.setOption("3");
				                botInputResult.setIntentName(BotCEABS.STATES.REAGCLIENTE);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "4":
							try {
				            	dadosFluxo.setOption("4");
				                botInputResult.setIntentName(BotCEABS.STATES.APOIOTEC);
				            }
			                catch(Exception e) {
			                	botInputResult.setResult(BotInputResult.Result.ERROR);
			                }
							break;
						case "5":
							try {
				            	dadosFluxo.setOption("5");
				                botInputResult.setIntentName(BotCEABS.STATES.OUTROS);
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
					put(BotCEABS.STATES.FECHAROS, "#FECHAROS");
					put(BotCEABS.STATES.VISITAIMP, "#VISITAIMP");
					put(BotCEABS.STATES.REAGCLIENTE, "#REAGCLIENTE");
					put(BotCEABS.STATES.APOIOTEC, "#APOIOTEC");
					put(BotCEABS.STATES.OUTROS, "#OUTROS");				
                    put("MAX_INPUT_ERROR", "/TERMINATE");
                    put("MAX_NO_INPUT", "/TERMINATE");
				}});
		}};
	}
}



					