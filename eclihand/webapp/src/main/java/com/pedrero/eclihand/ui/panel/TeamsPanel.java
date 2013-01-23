package com.pedrero.eclihand.ui.panel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.pedrero.eclihand.controller.panel.TeamsPanelController;
import com.pedrero.eclihand.model.dto.TeamDto;
import com.pedrero.eclihand.ui.table.entity.TeamTable;
import com.pedrero.eclihand.utils.Displayer;
import com.pedrero.eclihand.utils.Initiable;
import com.pedrero.eclihand.utils.text.MessageResolver;
import com.pedrero.eclihand.utils.ui.EclihandLayoutFactory;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TeamsPanel extends EclihandMainPanel implements Initiable, Displayer {
	@Resource
	private MessageResolver messageResolver;

	@Resource
	private TeamsPanelController teamsPanelController;

	@Resource(name = "playerTeamTable")
	private TeamTable teamsTable;

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
		this.addComponent(teamsTable);
		teamsTable.init();
	}

	@Override
	public void display() {
		teamsPanelController.searchTeamsAndDisplay();
	}

	public Table getTeamsTable() {
		return teamsTable;
	}

	public void refreshTeams(List<TeamDto> teams) {
		teamsTable.removeAllValidators();
		teamsTable.add(teams);
	}
}
