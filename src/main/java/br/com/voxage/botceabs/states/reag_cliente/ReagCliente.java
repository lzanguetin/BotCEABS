package br.com.voxage.botceabs.states.reag_cliente;

import java.util.HashMap;

import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class ReagCliente {
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("REAGCLIENTE");

				setBotStateInteractionType(BotStateInteractionType.NO_INPUT);

				setPreFunction(botState ->{
					bot.setLastState(BotCEABS.STATES.START);
					
					BotStateFlow botStateFlow = new BotStateFlow();
					botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
					
					return botStateFlow;
				});
				
				setPosFunction((botState, inputResult) ->{
                	BotStateFlow botStateFlow = new BotStateFlow();
                	botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
                	botStateFlow.navigationKey = "OS";
                	
                	return botStateFlow;
                });
                
                setNextNavigationMap(new HashMap<String, String>(){{
                	put("OS", "#OS");
                }});
		}};
	}
}
