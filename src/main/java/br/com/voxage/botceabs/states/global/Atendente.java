package br.com.voxage.botceabs.states.global;

import static br.com.voxage.chat.botintegration.utils.AppLogger.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.com.voxage.botceabs.BotCEABS;
import br.com.voxage.botceabs.models.DadosFluxo;
import br.com.voxage.chat.botintegration.entities.BotMessage;
import br.com.voxage.vbot.BotState;
import br.com.voxage.vbot.BotStateFlow;
import br.com.voxage.vbot.BotStateInteractionType;

@SuppressWarnings("unused")
public class Atendente{
	@SuppressWarnings("serial")
	public static BotState load(BotCEABS bot) {
		return new BotState("/") {{
				setId("ATENDENTE");
				
				setBotStateInteractionType(BotStateInteractionType.NO_INPUT);
				
				setPreFunction(botState -> {
					DadosFluxo dadosFluxo = bot.getDadosFluxo();
					
					if(dadosFluxo.getOption() == "1") {
                        List<BotMessage> initialMessages = botState.getCustomMessageById("state_fecharos").get(0)
                            .getMessages().stream().map(rm -> rm.clone()).collect(Collectors.toList());

                        botState.setInitialMessages(initialMessages);
                    }else {
                        botState.setInitialMessages(new ArrayList<>());
                    }
					
					if(dadosFluxo.getOption() == "2") {
                        List<BotMessage> initialMessages = botState.getCustomMessageById("state_visitaimp").get(0)
                        	.getMessages().stream().map(rm -> rm.clone()).collect(Collectors.toList());

                        botState.setInitialMessages(initialMessages);
                    }else {
                        botState.setInitialMessages(new ArrayList<>());
                    }
					
					if(dadosFluxo.getOption() == "3") {
                        List<BotMessage> initialMessages = botState.getCustomMessageById("state_reagcliente").get(0)
                        	.getMessages().stream().map(rm -> rm.clone()).collect(Collectors.toList());

                        botState.setInitialMessages(initialMessages);
                    }else {
                        botState.setInitialMessages(new ArrayList<>());
                    }
					
					if(dadosFluxo.getOption() == "4") {
                        List<BotMessage> initialMessages = botState.getCustomMessageById("state_apoiotec").get(0)
                        	.getMessages().stream().map(rm -> rm.clone()).collect(Collectors.toList());

                        botState.setInitialMessages(initialMessages);;
                    }else {
                        botState.setInitialMessages(new ArrayList<>());
                    }
					
					if(dadosFluxo.getOption() == "5") {
                        List<BotMessage> initialMessages = botState.getCustomMessageById("state_outros").get(0)
                            .getMessages().stream().map(rm -> rm.clone()).collect(Collectors.toList());

                         botState.setInitialMessages(initialMessages);
                    }else {
                        botState.setInitialMessages(new ArrayList<>());
                    }
	                                         	
	                BotStateFlow state = new BotStateFlow();
	                state.flow = BotStateFlow.Flow.CONTINUE;
	                return state;
	            });
				
				setPosFunction((botState, inputResult) ->{
					BotStateFlow botStateFlow = new BotStateFlow();
					DadosFluxo dadosFluxo = bot.getDadosFluxo();
					botStateFlow.flow = BotStateFlow.Flow.TRANSFER;
					
					switch(dadosFluxo.getOption()) {
						case "1":
							bot.setBotNameToTransfer(BotCEABS.STATES.CEABS_OS);
							break;
						case "2":
							bot.setBotNameToTransfer(BotCEABS.STATES.CEABS_IMPRODUTIVA);
							break;
						case "3":
							bot.setBotNameToTransfer(BotCEABS.STATES.CEABS_REAGENDAMENTO);
							break;
						case "4":
							bot.setBotNameToTransfer(BotCEABS.STATES.CEABS_APOIO);
							break;
						case "5":
							bot.setBotNameToTransfer(BotCEABS.STATES.CEABS_OUTROS);
							break;
					}
					
					botStateFlow.navigationKey = "TERMINATE";
					//botStateFlow.flow = BotStateFlow.Flow.TERMINATE;
					
					return botStateFlow;
				});
				
				setNextNavigationMap(new HashMap<String, String>(){{
					put("TERMINATE", "/TERMINATE");
				}});
		}};
	}
}



					