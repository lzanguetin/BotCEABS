package br.com.voxage.botceabs.states.global;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.botceabs.models.DadosFluxo;
import br.com.voxage.botceabs.models.Validators;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Os {
	@SuppressWarnings("serial")
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("OS");

				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotCEABS.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					DadosFluxo dadosFluxo = bot.getDadosFluxo();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs();
					
					if((Validators.validaOs(userInput)) == true){
						dadosFluxo.setOs(userInput);
						botInputResult.setResult(BotInputResult.Result.OK);
					}else {
						botInputResult.setResult(BotInputResult.Result.ERROR);
					}
														
					switch(dadosFluxo.getOption()) {
					case "4":
						try {
			                botInputResult.setIntentName(BotCEABS.STATES.PREST);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					case "5":
						try {
			                botInputResult.setIntentName(BotCEABS.STATES.PREST);
			            }
		                catch(Exception e) {
		                	botInputResult.setResult(BotInputResult.Result.ERROR);
		                }
						break;
					default:
						botInputResult.setIntentName(BotCEABS.STATES.PROP);
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
                	put(BotCEABS.STATES.PROP, "#PROP");
                	put(BotCEABS.STATES.PREST, "#PREST");
                	put("MAX_INPUT_ERROR", "#OSPATH");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}
