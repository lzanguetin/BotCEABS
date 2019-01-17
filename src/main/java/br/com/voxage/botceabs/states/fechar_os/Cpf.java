package br.com.voxage.botceabs.states.fechar_os;

import java.util.HashMap;

import br.com.voxage.basicvalidators.CPFValidator;
import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Cpf{
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("CPF");
				
				setBotStateInteractionType(BotStateInteractionType.DIRECT_INPUT);
				setMaxInputTime(BotCEABS.NO_INPUT_TIMEOUT); 
				setMaxInputError(3);
				setMaxNoInput(3);
				
				setProcessDirectInputFunction((botState, userInputs) -> {
					BotInputResult botInputResult = new BotInputResult();
					botInputResult.setResult(BotInputResult.Result.OK);			
					String userInput = userInputs.getConcatenatedInputs().replaceAll("\\D+", "");;
					
					if((CPFValidator.isValidCPF(userInput)) == false) {
						botInputResult.setResult(BotInputResult.Result.ERROR);
					}else {
						botInputResult.setResult(BotInputResult.Result.OK);
						bot.getUserSession().put("NOME", userInputs.getConcatenatedInputs());
					}
					
					return botInputResult;
				});
				
				setPosFunction((botState, inputResult) ->{
                	BotStateFlow botStateFlow = new BotStateFlow();
                	botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                	botStateFlow.navigationKey = "ATENDENTE";
                	
                	return botStateFlow;
                });
                
                setNextNavigationMap(new HashMap<String, String>(){{
                	put("ATENDENTE", "#ATENDENTE");
                    put("MAX_INPUT_ERROR", "#ATENDENTE");
                    put("MAX_NO_INPUT", "/TERMINATE");
                }});
		}};
	}
}
