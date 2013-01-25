package com.pedrero.eclihand.ui.panel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamsPanelController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.table.GenericTable;
import com.pedrero.eclihand.utils.Displayer;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamsPanel extends EclihandMainPanel implements Initiable, Displayer {
	@Resource
	private MessageResolver messageResolver;

	@Resource
	private TeamsPanelController teamsPanelController;

	@Resource(name = "teamTable")
	private GenericTable<TeamDto> teamTable;

	@Resource
	private EclihandLayoutFactory eclihandLayoutFactory;

	private Layout layout;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5954828103989095039L;

	@Override
	public void init() {
		this.setCaption(messageResolver.getMessage("teams.panel.title"));
		if (layout == null) {
			layout = eclihandLayoutFactory.createCommonVerticalLayout();
			this.setContent(layout);
		}
		this.addComponent(teamTable);
		teamTable.init();
	}

	@Override
	public void display() {
		teamsPanelController.searchTeamsAndDisplay();
	}

	public GenericTable<TeamDto> getTeamsTable() {
		return teamTable;
	}

	public void refreshTeams(List<TeamDto> teams) {
		teamTable.removeAllDataObjects();
		teamTable.feed(teams);
	}
}
