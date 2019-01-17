package br.com.voxage.bottreinamento.states.fechar_os;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.com.voxage.bottreinamento.BotTreinamento;
import br.com.voxage.chat.botintegration.entities.BotMessage;
import br.com.voxage.vbot.BotInputResult;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

public class Atendente{
	public static BotState load(BotTreinamento bot) {
		return new BotState("/") {{
				setId("ATENDENTE");
				
				setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
				
				setPosFunction((botState, inputResult) ->{
					BotStateFlow botStateFlow = new BotStateFlow();
					botStateFlow.flow = BotStateFlow.Flow.CONTINUE;
					botStateFlow.navigationKey = "TERMINATE";
					
					return botStateFlow;
				});
				
				setNextNavigationMap(new HashMap<String, String>(){{
					put("TERMINATE", "/TERMINATE");
				}});
		}};
	}
}



					